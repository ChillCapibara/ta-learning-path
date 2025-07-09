package com.szymon.basics.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.szymon.common.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ToDoGetTests {

private final String baseURI = "https://dummyjson.com";

    @Test
    public void verifyStatusCode(){
        RestAssured
                .get(baseURI + "/todos/1")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void verifyResponseBody() throws Exception {
        Response response = RestAssured.get(baseURI + "/todos/1");
        JsonNode actualResults = JsonUtils.convertResponseIntoJson(response);
        JsonNode expectedResponse = JsonUtils.loadJsonFromResources("api/dummy/expectedResponseGetToDo.json");

        Assert.assertEquals(actualResults, expectedResponse);
    }

    @Test
    public void verifyIdempotenceOfResponse() throws Exception {
        Response response1 = RestAssured.get(baseURI + "/todos/1");
        JsonNode response1Json = JsonUtils.convertResponseIntoJson(response1);

        Response response2 = RestAssured.get(baseURI + "/todos/1");
        JsonNode response2Json = JsonUtils.convertResponseIntoJson(response2);

        Assert.assertEquals(response1Json, response2Json);

    }


}
