package framework.data.readers;


import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class JsonReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonReader() {}

    public static List<Map<String, Object>> readListOfMaps(String resourcePath) {
        try (InputStream is = ResourceReader.open(resourcePath)) {
            return MAPPER.readValue(is, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new IllegalStateException("Failed reading JSON: " + resourcePath, e);
        }
    }
}
