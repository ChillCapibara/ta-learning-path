package com.szymon.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

import java.io.InputStream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonUtils {

    public static JsonNode loadJsonFromResources(String filePath) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream input = cl.getResourceAsStream(filePath);
        return new ObjectMapper().readTree(input);
    }

    public static JsonNode convertResponseIntoJson(Response response) throws Exception {
        return new ObjectMapper().readTree(response.getBody().asString());
    }

    public static ObjectNode getPayloadWithFieldFromResource(String folderPath, String fieldName, Object valueForField) throws Exception {
        ObjectNode base = (ObjectNode) loadJsonFromResources(folderPath);
        base.set(fieldName, new ObjectMapper().valueToTree(valueForField));
        return base;
    }

    public static void schemaValidator(Response response, String schemaFilePath) {
        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schemaFilePath));
    }
}
