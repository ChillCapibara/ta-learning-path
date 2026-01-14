package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextParsers {

    private static final Pattern FIRST_INT = Pattern.compile("(\\d+)");

    private TextParsers() {}

    /**
     * Extracts the first integer found in the given text.
     * Example: "1 Item₹450.00" -> 1
     */
    public static int firstInt(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        String trimmed = text.trim();
        Matcher m = FIRST_INT.matcher(trimmed);
        if (!m.find()) {
            throw new IllegalArgumentException("No integer found in text: '" + text + "'");
        }
        return Integer.parseInt(m.group(1));
    }

}
