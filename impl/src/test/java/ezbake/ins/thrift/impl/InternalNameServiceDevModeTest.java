package ezbake.ins.thrift.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ezbake.configuration.ClasspathConfigurationLoader;
import ezbake.configuration.EzConfiguration;
import ezbake.ins.thrift.gen.*;
import ezbake.ins.thrift.util.INSUtility;
import ezbake.thrift.ThriftTestUtils;
import org.junit.*;
import ezbake.thrift.ThriftClientPool;
import ezbake.thrift.ThriftServerPool;

import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * This class is to test the few methods that are available in dev mode
 */
public class InternalNameServiceDevModeTest {
    private static ThriftClientPool pool;
    private static ThriftServerPool servers;
    private static final String TestAppId = "1010010";
    private InternalNameService.Client client;

    @BeforeClass
    public static void init() throws Exception {
        EzConfiguration configuration = new EzConfiguration(new ClasspathConfigurationLoader());
        //Put the INS into dev mode - no backend database, only supports a few calls
        Properties properties = configuration.getProperties();
        properties.setProperty(InternalNameServiceHandler.INSMode, "true");
        servers = new ThriftServerPool(properties, 15000);
        servers.startCommonService(new InternalNameServiceHandler(), InternalNameServiceConstants.SERVICE_NAME, "ins");

        pool = new ThriftClientPool(properties);

    }

    @AfterClass
    public static void cleanup() {
        //Shutdown our client connections and the services
        pool.close();
        servers.shutdown();
    }


    @Before
    public void beforeTest() throws Exception {
        client = pool.getClient(InternalNameServiceConstants.SERVICE_NAME, InternalNameService.Client.class);
    }

    @After
    public void afterTest() throws Exception {
        pool.returnToPool(client);
    }

    @Test
    public void testGetUriPrefix() throws Exception {
        assertEquals(INSUtility.buildUriPrefix(InternalNameServiceHandler.DevModePrefix, "MyCategory"),
            client.getURIPrefix(TestAppId, "MyCategory"));
    }

    @Test
    public void testGetTopic() throws Exception {
        Set<String> expected = Sets.newHashSet("TestFeedTopic1", "TestFeedTopic2");
        assertEquals(expected, client.getTopicsForFeed(TestAppId, "TestFeed"));
    }

    @Test(expected = Exception.class)
    public void testUnsupportedMethod() throws Exception{
        client.getPipelineFeeds();
    }

    @Test
    public void testGetIntents() throws Exception {
        AppService a1 = new AppService("MyFirstApp", "MyIntentService1");
        AppService a2 = new AppService("MySecondApp", "MyIntentService2");
        Set<AppService> expectedApps = Sets.newHashSet(a1, a2);
        Set<AppService> actualApps = client.appsThatSupportIntent("myIntent");
        assertEquals(expectedApps, actualApps);
    }

    @Test
    public void testGetWebAppsForURI() throws Exception {
        Set<WebApplicationLink> expectedLinks = Sets.newHashSet(
                buildWebApp("www.firstapp.com", "MyFirstApp", false),
                buildWebApp("www.secondapp.com", "MySecondApp", true));
        Set<WebApplicationLink> actualLinks = client.getWebAppsForUri("NEWS://CNN");
        assertEquals(expectedLinks, actualLinks);
    }

    @Test
    public void testGetChloeApps() throws Exception {
        Set<WebApplicationLink> expectedLinks = Sets.newHashSet(
                buildWebApp("www.firstapp.com", "MyFirstApp", false),
                buildWebApp("www.secondapp.com", "MySecondApp", true));
        Set<WebApplicationLink> actualLinks = client.getChloeWebApps();
        assertEquals(expectedLinks, actualLinks);
    }

    @Test
    public void testGetJobRegistrations() throws Exception {
        JobRegistration job1 = new JobRegistration();
        job1.setFeedName("CNN");
        job1.setJobName("cnnBatch");
        Set<JobRegistration> expectedJobs = Sets.newHashSet(job1);
        Set<JobRegistration> actualJobs = client.getJobRegistrations("12345",
                ThriftTestUtils.generateTestSecurityToken("ins", "ins",
                Lists.newArrayList("U")));
        assertEquals(expectedJobs, actualJobs);
    }

    private WebApplicationLink buildWebApp(String url, String name, boolean includePrefix) {
        WebApplicationLink link = new WebApplicationLink();
        link.setWebUrl(url);
        link.setAppName(name);
        link.setIncludePrefix(includePrefix);
        return link;
    }

}
