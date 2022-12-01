package com.lvo23.spec;

import static com.lvo23.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Request {

    private static final RequestSpecification spec = with().filter(withCustomTemplates())
            .baseUri("https://reqres.in").contentType(ContentType.JSON).log().all();

    public static RequestSpecification spec() {

        return spec;
    }
}
