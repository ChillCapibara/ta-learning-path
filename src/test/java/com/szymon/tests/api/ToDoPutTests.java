//package com.szymon.tests.api;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.szymon.common.utils.JsonUtils;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//
//public class ToDoPutTests extends BaseApiTest{
//
//    private long toDoId;
//
//    @BeforeClass
//    public void setupToDoForUpdate() throws Exception {
//        JsonNode requestBody = JsonUtils.loadJsonFromResources("api/requests/toDoCreate.json");
//        Response response = JsonUtils.post("/todos/add", requestBody);
//        toDoId = response.jsonPath().getLong("id");
//    }
//
//    @Test
//    public void toDoCanBeUpdated() throws Exception {
//        JsonNode body = JsonUtils.loadJsonFromResources("api/requests/toDoPut.json");
//        Response response = JsonUtils.put("/todos/2", body);
//
//        JsonNode expectedResponse = JsonUtils.loadJsonFromResources("api/responses/expectedResponsePutToDo.json");
//        Assert.assertEquals(response.statusCode(), 200, "Invalid status code");
//        Assert.assertEquals(JsonUtils.convertResponseIntoJson(response), expectedResponse, "Unexpected response");
//    }
//
//    @Test
//    public void updateJustCreatedToDo() throws Exception {
//        JsonNode body = JsonUtils.loadJsonFromResources("api/requests/toDoPut.json");
//
//
//    }
//
//    @Test
//    public void updateToDo200ResponseMatchesSchema() throws Exception {
//        JsonNode body = JsonUtils.loadJsonFromResources("api/requests/toDoPut.json");
//        Response response = JsonUtils.put("/todos/2", body);
//        JsonUtils.schemaValidator(response, "api/schemas/updateToDo200Schema.json");
//    }
//
//}
