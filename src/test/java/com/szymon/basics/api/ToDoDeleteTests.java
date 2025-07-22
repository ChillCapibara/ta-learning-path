package com.szymon.basics.api;

import com.szymon.common.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ToDoDeleteTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Test
    public void toDoCanBeDeleted() {
        Response response = JsonUtils.delete("/todos/1");

        String now = ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String actualTime = response.jsonPath().getString("deletedOn");
        String actualTrimmedZoneDateTime = ZonedDateTime.parse(actualTime).
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        Assert.assertEquals(response.statusCode(), 200, "Invalid status code");
        Assert.assertEquals(response.jsonPath().getInt("id"), 1, "Id not matched");
        Assert.assertEquals(response.jsonPath().getString("todo"), "Do something nice for someone you care about", "Wrong ToDo text");
        Assert.assertFalse(response.jsonPath().getBoolean("completed"), "Invalid Completion state");
        Assert.assertEquals(response.jsonPath().getInt("userId"), 152, "Wrong user");
        Assert.assertTrue(response.jsonPath().getBoolean("isDeleted"), "Invalid deletion state");
        Assert.assertEquals(actualTrimmedZoneDateTime, now, "Deletion time is not matching the expected one");
    }

    @Test
    public void deleteToDo200ResponseMatchesSchema() {
        Response response = JsonUtils.delete("/todos/1");
        JsonUtils.schemaValidator(response, "api/dummy/schemas/deleteToDo200Schema.json");
    }

}
