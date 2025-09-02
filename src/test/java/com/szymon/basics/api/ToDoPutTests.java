package com.szymon.basics.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.szymon.common.JsonUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ToDoPutTests extends BaseApiTest{

    @Test
    public void toDoCanBeUpdated() throws Exception {
        JsonNode body = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoPut.json");
        Response response = JsonUtils.put("/todos/2", body);

        JsonNode expectedResponse = JsonUtils.loadJsonFromResources("api/dummy/responses/expectedResponsePutToDo.json");
        Assert.assertEquals(response.statusCode(), 200, "Invalid status code");
        Assert.assertEquals(JsonUtils.convertResponseIntoJson(response), expectedResponse, "Unexpected response");
    }

    @Test
    public void updateToDo200ResponseMatchesSchema() throws Exception {
        JsonNode body = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoPut.json");
        Response response = JsonUtils.put("/todos/2", body);
        JsonUtils.schemaValidator(response, "api/dummy/schemas/updateToDo200Schema.json");
    }

}
