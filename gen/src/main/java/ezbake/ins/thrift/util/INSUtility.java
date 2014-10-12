package ezbake.ins.thrift.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class INSUtility {
    private static final Pattern UriPattern = Pattern.compile("(\\w+://[A-Za-z0-9.-]+)(/|$)");

    public static String buildUriPrefix(String category, String categoryKey) {
        return category + "://" + categoryKey + "/";
    }

    public static String getUriPrefix(String uri) throws IllegalArgumentException {
        Matcher matcher = UriPattern.matcher(uri);
        if (matcher.find()) {
            String match = matcher.group(1);
            if (!match.endsWith("/")) {
                match = match + "/";
            }
            return match;
        } else {
            throw new IllegalArgumentException("Invalid uri");
        }
    }
}
