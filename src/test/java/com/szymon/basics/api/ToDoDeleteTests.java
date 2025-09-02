package com.szymon.basics.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.szymon.common.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToDoDeleteTests extends BaseApiTest{

    private String id;

    @BeforeMethod
    public void createToDoForDeletion() throws Exception {
        JsonNode requestBody = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoCreate.json");
        Response response = JsonUtils.post("/todos/add", requestBody);
        long tempId = response.jsonPath().getLong("id");
        id = String.valueOf(tempId);
    }

    @Test
    public void toDoCanBeDeleted() {
        Response response = RestAssured.delete("/todos/" + id);
        Assert.assertEquals(response.statusCode(), 204, "Invalid status code");
    }

    @Test
    public void deleteToDo200ResponseMatchesSchema() {
        Response response = RestAssured.delete("/todos/" + id);
        JsonUtils.schemaValidator(response, "api/dummy/schemas/deleteToDo200Schema.json");
    }

}
