package com.lvo23.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Request {

    private static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .build();

    public static RequestSpecification spec() {
        return spec;
    }
}
