package com.szymon.basics.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.szymon.common.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ToDoPutTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void toDoCanBeUpdated() throws Exception {
        JsonNode body = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoPut.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
            .when()
                .put("/todos/1")
            .then()
                .extract().response();

        JsonNode expectedResponse = JsonUtils.loadJsonFromResources("api/dummy/responses/expectedResponsePutToDo.json");
        Assert.assertEquals(response.statusCode(), 200, "Invalid status code");
        Assert.assertEquals(JsonUtils.convertResponseIntoJson(response), expectedResponse, "Unexpected response");
    }

}
