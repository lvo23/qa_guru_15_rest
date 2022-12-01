package com.lvo23.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

/**
 * @author Vlad Litvinov
 */
public class CustomApiListener {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {

        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }

}
