package com.szymon.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.InputStream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonUtils {

    public static JsonNode loadJsonFromResources(String filePath) throws Exception {
        InputStream input = JsonUtils.class.getClassLoader().getResourceAsStream(filePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(input);
    }

    public static JsonNode convertResponseIntoJson(Response response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(response.getBody().asString());
    }

    public static ObjectNode getPayloadWithFieldFromResource(String folderPath, String fieldName, Object valueForField) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode base = (ObjectNode) loadJsonFromResources(folderPath);
        base.set(fieldName, mapper.valueToTree(valueForField));
        return base;
    }

    public static void schemaValidator(Response response, String schemaFilePath) {
        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schemaFilePath));
    }

    public static Response get(String endpoint, JsonNode requestBody){
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .get(endpoint)
                .then().extract().response();
    }

    public static Response get(String endpoint){
        return given()
                .when()
                .get(endpoint)
                .then().extract().response();
    }

    public static Response post(String endpoint, JsonNode requestBody){
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then().extract().response();
   }

    public static Response put(String endpoint, JsonNode requestBody){
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then().extract().response();
    }

    public static Response delete(String endpoint, JsonNode requestBody){
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .delete(endpoint)
                .then().extract().response();
    }

    public static Response delete(String endpoint){
        return  when()
                .delete(endpoint)
                .then().extract().response();
    }

}
