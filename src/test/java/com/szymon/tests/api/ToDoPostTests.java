//package com.szymon.tests.api;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.szymon.common.utils.JsonUtils;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class ToDoPostTests extends BaseApiTest{
//
//    @Test
//    public void toDoCanBeCreated() throws Exception {
//        JsonNode requestBody = JsonUtils.loadJsonFromResources("api/requests/toDoCreate.json");
//        Response response = JsonUtils.post("/todos/add", requestBody);
//
//        Assert.assertEquals(response.statusCode(), 201, "Invalid status code");
//
//        Assert.assertTrue(response.jsonPath().get("id") instanceof Long);
//        Assert.assertTrue(response.jsonPath().getInt("id") > 0, "Id not assigned");
//
//        Assert.assertTrue(response.jsonPath().get("completed") instanceof Boolean);
//        Assert.assertFalse(response.jsonPath().getBoolean("completed"), "Invalid completion status");
//
//        Assert.assertTrue(response.jsonPath().get("userId") instanceof Integer);
//        Assert.assertEquals(response.jsonPath().getInt("userId"), 99, "Invalid userId");
//    }
//
//
//    @DataProvider(name = "invalidUserIdFields")
//    public Object[][] invalidBodyUserIdField() {
//        return new Object[][]{
//                {"lorem"},
//                {true},
//                {"lorem !@#$%^&*()_+|}{\n\t'\";,./<>?"},
//                {"\n"},
//                {null}
//        };
//    }
//
//
//    @Test(dataProvider = "invalidUserIdFields")
//    public void invalidUserIdBody(Object val) throws Exception {
//        ObjectNode payload = JsonUtils
//                .getPayloadWithFieldFromResource("api/requests/toDoCreate.json", "userId", val);
//        Response response = JsonUtils.post("/todos/add", payload);
//
//        Assert.assertEquals(response.statusCode(), 400, "Invalid status code");
//        Assert.assertEquals(response.jsonPath().getString("errors[0].message"), "userId must be an integer");
//    }
//
//}