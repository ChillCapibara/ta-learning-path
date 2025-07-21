package com.szymon.basics.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.szymon.common.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ToDoPostTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }


    @Test
    public void toDoCanBeCreated() throws Exception {
        JsonNode requestBody = JsonUtils.loadJsonFromResources("api/dummy/requests/toDoCreate.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/todos/add")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 201, "Invalid status code");

        Assert.assertTrue(response.jsonPath().get("id") instanceof Integer);
        Assert.assertTrue(response.jsonPath().getInt("id") > 0, "Id not assigned");

        Assert.assertTrue(response.jsonPath().get("completed") instanceof String);
        Assert.assertEquals(response.jsonPath().getString("completed"), "false", "Invalid completion status");

        Assert.assertTrue(response.jsonPath().get("userId") instanceof Integer);
        Assert.assertEquals(response.jsonPath().getInt("userId"), 99, "Invalid userId");
    }


    @DataProvider(name = "invalidUserIdFields")
    public Object[][] invalidBodyUserIdField() {
        return new Object[][]{
                {"lorem"},
                {true},
                {"lorem !@#$%^&*()_+|}{\n\t'\";,./<>?"}
        };
    }


    @Test(dataProvider = "invalidUserIdFields")
    public void invalidUserIdBody(Object val) throws Exception {
        ObjectNode payload = (ObjectNode) JsonUtils.loadJsonFromResources("api/dummy/requests/toDoCreate.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode userIdNode = mapper.valueToTree(val);
        payload.set("userId", userIdNode);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .post("/todos/add")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400, "Invalid status code");
        Assert.assertEquals(response.jsonPath().getString("message"), String.format("Invalid user id '%s'", val));
    }


    @Test
    public void nullUserIdField() throws Exception {
        ObjectNode payload = (ObjectNode) JsonUtils.loadJsonFromResources("api/dummy/requests/toDoCreate.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode userIdNode = mapper.valueToTree(null);
        payload.set("userId", userIdNode);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .post("/todos/add")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400, "Invalid status code");
        Assert.assertEquals(response.jsonPath().getString("message"), "User id is required");
    }


    @Test
    public void nonExistingUser() throws Exception {
        ObjectNode payload = (ObjectNode) JsonUtils.loadJsonFromResources("api/dummy/requests/toDoCreate.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode userIdNode = mapper.valueToTree("\n");
        payload.set("userId", userIdNode);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .post("/todos/add")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 404, "Invalid status code");
        Assert.assertEquals(response.jsonPath().getString("message"), "User with id '\n' not found");
    }
}