package com.szymon.basics.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.szymon.common.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ToDoPutTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Test
    public void toDoCanBeUpdated() throws Exception {
        JsonNode body = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoPut.json");
        Response response = JsonUtils.put("/todos/1", body);

        JsonNode expectedResponse = JsonUtils.loadJsonFromResources("api/dummy/responses/expectedResponsePutToDo.json");
        Assert.assertEquals(response.statusCode(), 200, "Invalid status code");
        Assert.assertEquals(JsonUtils.convertResponseIntoJson(response), expectedResponse, "Unexpected response");
    }

    @Test
    public void updateToDo200ResponseMatchesSchema() throws Exception {
        JsonNode body = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoPut.json");
        Response response = JsonUtils.put("/todos/1", body);
        JsonUtils.schemaValidator(response, "api/dummy/schemas/updateToDo200Schema.json");
    }

}
