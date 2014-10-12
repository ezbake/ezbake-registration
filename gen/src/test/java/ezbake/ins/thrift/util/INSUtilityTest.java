package ezbake.ins.thrift.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class INSUtilityTest {
    @Test
    public void testSplitURIPrefix() throws Exception {
        String prefix = INSUtility.getUriPrefix("NEWS://myFeed/myUniqueId/can_have/slashes.xml");
        assertEquals("NEWS://myFeed/", prefix);
    }

    @Test
    public void testSplitURIPrefixDash() throws Exception {
        String prefix = INSUtility.getUriPrefix("\n" +
                "SOCIAL://dev-twitter/tweet/398872121918902272");
        assertEquals("SOCIAL://dev-twitter/", prefix);
    }

    @Test
    public void testSplitURIPrefixDot() throws Exception {
        String prefix = INSUtility.getUriPrefix("\n" +
                "SOCIAL://dev.twitter/tweet/398872121918902272");
        assertEquals("SOCIAL://dev.twitter/", prefix);
    }

    @Test
    public void testSplitURIPrefixOfPrefix() throws Exception {
        String prefix = INSUtility.getUriPrefix("\n" +
                "SOCIAL://dev-twitter");
        assertEquals("SOCIAL://dev-twitter/", prefix);
    }

    @Test
    public void testSplitURIPrefixRandom() throws Exception {
        String prefix = INSUtility.getUriPrefix("\n" +
                "SOCIAL://some-THING.1234/test/123-23.test");
        assertEquals("SOCIAL://some-THING.1234/", prefix);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitURIPrefixInvalid() throws Exception {
        INSUtility.getUriPrefix("myId/test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitURIPrefixInvalid2() throws Exception {
        System.out.println(INSUtility.getUriPrefix("CATEGORY://myIdtests+"));
    }
}
