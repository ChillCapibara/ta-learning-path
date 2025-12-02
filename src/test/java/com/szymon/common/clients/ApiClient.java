package com.szymon.common.clients;

import io.restassured.response.Response;

public interface ApiClient {
    Response get(String path);
    Response post(String path, Object body);
    Response put(String path, Object body);
    Response delete(String path);
}