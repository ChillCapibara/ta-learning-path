package framework.data.readers;

import java.util.Map;

public final class JsonCaseValidator {

    private JsonCaseValidator() {}

    public static String requiredString(Map<String, Object> m, String key) {
        Object v = m.get(key);
        if (v == null) {
            throw new IllegalArgumentException("JSON case missing required key: " + key + " in " + m);
        }
        if (!(v instanceof String s) || s.isBlank()) {
            throw new IllegalArgumentException("JSON case key is not a non-blank string: " + key + " in " + m);
        }
        return s;
    }
}
