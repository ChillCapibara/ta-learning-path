//package com.szymon.tests.api;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.szymon.common.utils.JsonUtils;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class ToDoGetTests extends BaseApiTest{
//
//    @Test
//    public void verifyStatusCode() {
//        Response response = JsonUtils.get("/todos/1");
//        response.then().assertThat().statusCode(200);
//    }
//
//    @Test
//    public void verifyResponseBody() throws Exception {
//        Response response = JsonUtils.get("/todos/1");
//        JsonNode actualResults = JsonUtils.convertResponseIntoJson(response);
//        JsonNode expectedResponse = JsonUtils.loadJsonFromResources("api/responses/expectedResponseGetToDo.json");
//
//        Assert.assertEquals(actualResults, expectedResponse);
//    }
//
//    @Test
//    public void verifyIdempotenceOfResponse() throws Exception {
//        Response response1 = JsonUtils.get("/todos/1");
//        JsonNode response1Json = JsonUtils.convertResponseIntoJson(response1);
//
//        Response response2 = JsonUtils.get("/todos/1");
//        JsonNode response2Json = JsonUtils.convertResponseIntoJson(response2);
//
//        Assert.assertEquals(response1Json, response2Json);
//
//    }
//
//    @Test
//    public void response200MatchesSchema(){
//        Response response = JsonUtils.get("/todos/1");
//        JsonUtils.schemaValidator(response, "api/schemas/toDo200Schema.json");
//    }
//
//}
