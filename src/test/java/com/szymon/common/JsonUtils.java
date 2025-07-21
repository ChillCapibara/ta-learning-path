package com.szymon.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

import java.io.InputStream;

public class JsonUtils {

    public static JsonNode loadJsonFromResources(String filePath) throws Exception{
        InputStream input = JsonUtils.class.getClassLoader().getResourceAsStream(filePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(input);
    }

    public static JsonNode convertResponseIntoJson(Response response) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response.getBody().asString());
    }

    public static ObjectNode getPayloadWithFieldFromResource(String folderPath, String fieldName, Object valueForField) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode base = (ObjectNode) loadJsonFromResources(folderPath);
        base.set(fieldName, mapper.valueToTree(valueForField));
        return base;
    }

}
