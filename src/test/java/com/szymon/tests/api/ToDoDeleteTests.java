package com.szymon.tests.api;
import com.szymon.api.ToDoApiClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.szymon.common.utils.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToDoDeleteTests extends BaseApiTest{

    private long id;
    private final ToDoApiClient client = new ToDoApiClient();

    @BeforeMethod
    public void createToDoForDeletion() throws Exception {
        JsonNode requestBody = JsonUtils.loadJsonFromResources("api/requests/toDoCreate.json");
        Response response = client.create(requestBody);
        response.prettyPrint();
//        id = response.jsonPath().get("id");
    }

    @Test
    public void toDoCanBeDeleted() {
        Assert.assertEquals(client.delete(1L).statusCode(), 204, "Invalid status code");
    }

    @Test
    public void deleteToDo200ResponseMatchesSchema() {
        Response response = RestAssured.delete("/todos/" + id);
        JsonUtils.schemaValidator(response, "api/schemas/deleteToDo200Schema.json");
    }

}
