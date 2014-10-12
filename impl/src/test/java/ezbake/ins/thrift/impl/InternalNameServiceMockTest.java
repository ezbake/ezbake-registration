package ezbake.ins.thrift.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import ezbake.base.thrift.EzSecurityToken;
import ezbake.ins.thrift.gen.*;
import ezbake.security.client.EzSecurityTokenWrapper;
import ezbake.security.client.EzbakeSecurityClient;
import ezbake.security.thrift.RegistrationStatus;
import ezbake.intents.IntentType;
import ezbake.thrift.ThriftClientPool;
import org.easymock.EasyMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(EasyMockRunner.class)
public class InternalNameServiceMockTest {

    @Test
    public void testGetTopicsForFeed() throws Exception {
		Application app = getTestApplication();
		FeedPipeline feed = app.getFeedPipelines().iterator().next();
		Set<String> expected = Sets.newHashSet();
		for(BroadcastTopic topic : feed.getBroadcastTopics()) {
			expected.add(topic.getName());
		}

        EzSecurityToken securityTokenMock = createMock(EzSecurityToken.class);
		InternalNameServiceHandler tested = createMockBuilder(InternalNameServiceHandler.class)
                .addMockedMethod("getApplicationRegistrationStatus")
                .addMockedMethod("getApplication", String.class, EzSecurityToken.class)
                .addMockedMethod("getSecurityToken")
                .createMock();
        expect(tested.getApplicationRegistrationStatus(app.getId(), securityTokenMock)).andReturn(RegistrationStatus.ACTIVE);
		expect(tested.getApplication(app.getId(), securityTokenMock)).andReturn(app);
        expect(tested.getSecurityToken()).andReturn(securityTokenMock);

		replay(tested);

        Set<String> actual = tested.getTopicsForFeed(app.getId(), feed.getFeedName());

        //Not a very good test if there's nothing, so let's make sure this feed has a broadcast topic
        assertTrue(actual.size() > 0);
        //Now assert it returned the right thing
        assertEquals(expected, actual);
    }

    @Test
    public void testGetListeningTopicsForFeed() throws Exception {
        Application app = getTestApplication();
        ListenerPipeline feed = app.getListenerPipelines().iterator().next();
        Set<String> expected = feed.getListeningTopics();

        InternalNameServiceHandler tested = createMockBuilder(InternalNameServiceHandler.class)
                .addMockedMethod("getApplicationRegistrationStatus")
                .addMockedMethod("getApplication", String.class, EzSecurityToken.class)
                .addMockedMethod("validateToken")
                .createMock();
        EzSecurityToken securityTokenMock = createMock(EzSecurityToken.class);

        expect(tested.validateToken(securityTokenMock)).andReturn(true);
		expect(tested.getApplicationRegistrationStatus(app.getId(), securityTokenMock)).andReturn(RegistrationStatus.ACTIVE);
		expect(tested.getApplication(app.getId(), securityTokenMock)).andReturn(app);
		replay(tested);

        Set<String> actual = tested.getListeningTopicsForFeed(app.getId(), feed.getFeedName(), securityTokenMock);

        //Not a very good test if there's nothing, so let's make sure this feed has a broadcast topic
        assertTrue(actual.size() > 0);
        //Now assert it returned the right thing
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetJobRegistrations() throws Exception {
        Application app = getTestApplication();
        Set<JobRegistration> expected = new HashSet<>();
        for(JobRegistration registration : app.getJobRegistrations()) {
            expected.add(new JobRegistration(registration).setUriPrefix("NEWS://CNN/"));
        }

        EzbakeSecurityClient mockClient = createMockBuilder(EzbakeSecurityClient.class)
                .addMockedMethod("fetchDerivedTokenForApp", EzSecurityToken.class, String.class)
                .createMock();

        ThriftClientPool mockPool = createMock(ThriftClientPool.class);
        InternalNameServiceHandler tested = createMockBuilder(InternalNameServiceHandler.class)
                .addMockedMethod("getApplicationRegistrationStatus")
                .addMockedMethod("getApplication", String.class, EzSecurityToken.class)
                .addMockedMethod("getApplications")
                .addMockedMethod("validateToken")
                .addMockedMethod("getSecurityToken")
                .withConstructor(mockClient, mockPool, "")
                .createMock();
        EzSecurityTokenWrapper securityTokenMock = createMock(EzSecurityTokenWrapper.class);

        expect(tested.validateToken(securityTokenMock)).andReturn(true);
        expect(tested.getApplicationRegistrationStatus(app.getId(), securityTokenMock)).andReturn(RegistrationStatus.ACTIVE);
        expect(tested.getApplication(app.getId(), securityTokenMock)).andReturn(app);
        expect(tested.getApplications(tested.getTermQuery(InternalNameServiceHandler.FeedPipeLinesFeedName,
                        "CNN"), securityTokenMock)).andReturn(Sets.newHashSet(app)).once();
        expect(tested.getSecurityToken()).andReturn(securityTokenMock).once();
        expect(mockClient.fetchDerivedTokenForApp(securityTokenMock, "")).andReturn(securityTokenMock).once();
		replay(tested, mockClient);

        Set<JobRegistration> actual = tested.getJobRegistrations(app.getId(), securityTokenMock);
        assertTrue(actual.size() > 0);
        assertEquals(expected, actual);
        verify(tested, mockClient);
    }

    private Application getTestApplication() {
        Application a = new Application();
        a.setAppName("My App");
        a.setPoc("Unit Tester");
        a.setAllowedUsers(Sets.newHashSet("CN=Test One, OU=People, O=Name Service, C=US",
                "CN=Two Test, OU=People, O=Name Service, C=US"));

        HashMap<String, String> categories = Maps.newHashMap();
        categories.put("CNN", "NEWS");
        categories.put("Twitter", "SOCIAL");
        a.setCategories(categories);
        a.setId("1234");
        a.setAuthorizations(Sets.newHashSet("U", "FOUO"));

        FeedPipeline feed = new FeedPipeline();
        feed.setFeedName("CNN");
        BroadcastTopic feedTopic = new BroadcastTopic();
        feedTopic.setDescription("News Feed");
        feedTopic.setName("cnn");
        feedTopic.setThriftDefinition("a thrift idl is expected here");
        feed.setBroadcastTopics(Sets.newHashSet(feedTopic));
        feed.setDescription("My CNN Feed");
        feed.setExportingSystem("CNN.com");
        feed.setType("Streaming");
        feed.setDataType("XML");
        a.setFeedPipelines(Sets.newHashSet(feed));

        WebApplication webApp = new WebApplication();
        webApp.setIsChloeEnabled(true);
        Map<String, WebApplicationLink> urnMap = new HashMap<>();
        WebApplicationLink link = new WebApplicationLink();
        link.setWebUrl("https://apps.some.domain.com/cnn?id={id}");
        urnMap.put("NEWS://CNN/", link);
        webApp.setUrnMap(urnMap);
        a.setWebApp(webApp);

        ListenerPipeline listener = new ListenerPipeline();
        BroadcastTopic broadcastTopic = new BroadcastTopic();
        broadcastTopic.setDescription("CNN Normalized");
        broadcastTopic.setName("CNN-Norm");
        broadcastTopic.setThriftDefinition("a thrift idl is expected here");
        listener.setBroadcastTopics(Sets.newHashSet(broadcastTopic));
        listener.setDescription("Indexing pipeline for CNN Data");
        listener.setFeedName("CNN-Index");
        listener.setListeningTopics(Sets.newHashSet("cnn-breaking", "cnn-us"));
        a.setListenerPipelines(Sets.newHashSet(listener));

        Map<String, String> intentServiceMap = Maps.newHashMap();
        intentServiceMap.put(IntentType.ACTIVITY.name(), "Service1");
        intentServiceMap.put(IntentType.IMAGE.name(), "Service2");
        a.setIntentServiceMap(intentServiceMap);

        Set<JobRegistration> jobRegistrations = Sets.newHashSet();
        JobRegistration jobRegistration = new JobRegistration();
        jobRegistration.setJobName("Amino Acid");
        jobRegistration.setFeedName("CNN");
        jobRegistrations.add(jobRegistration);
        a.setJobRegistrations(jobRegistrations);

        return a;
    }
}
