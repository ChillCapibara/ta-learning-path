package framework.navigation;

import framework.config.Config;

public final class Urls {
    private Urls() {}

    private static String base() {
        return Config.get("base.url");
    }

    //normalise path
    public static String join(String... parts) {
        if (parts == null || parts.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (p == null || p.isEmpty()) continue;
            String cleaned = p;
            if (sb.isEmpty()) {
                cleaned = cleaned.replaceAll("/+$", ""); // trim trailing on first
            } else {
                cleaned = cleaned.replaceAll("^/+", ""); // trim leading
            }
            sb.append(cleaned);
            if (!cleaned.endsWith("/")) sb.append("/");
        }
        String out = sb.toString();
        if (out.endsWith("/")) out = out.substring(0, out.length() - 1);
        return out;
    }

    public static String baseUrl() {
        return base();
    }
}
