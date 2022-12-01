package com.lvo23.tests;

import com.lvo23.model.User;
import com.lvo23.spec.Request;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {

    private final User user = new User();

    @Test
    @DisplayName("Получить юзера с id=2")
    void getSingleUserWithId2() {
        int id = 2;

        given()
                .spec(Request.spec()).
                when().
                get("/api/users/{id}", id).
                then()
                .statusCode(200)
                .body("data.id", is(id));
    }

    @Test
    @DisplayName("Проверить email у юзера с id=7")
    void checkEmailInListUserWithId7() {
        int pageNumber = 2;

        given()
                .spec(Request.spec()).
                when().
                get("/api/users?page={pageNumber}", pageNumber).
                then()
                .statusCode(200)
                .body("data.findAll {it.id == 7 }.email", hasItem("michael.lawson@reqres.in"));
    }

    @Test
    @DisplayName("Получить несуществующего юзера")
    void getUnknownUser() {
        int id = 32;

        given()
                .spec(Request.spec()).
                when().
                get("/api/users/{id}", id).
                then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Позитив – создать юзера")
    void createUserSuccess() {
        user.setName("TestName");
        user.setJob("TestJob");

        given()
                .spec(Request.spec()).
                body(user).
                when().
                post("/api/users").
                then()
                .statusCode(201)
                .body("name", is(user.getName()))
                .body("job", is(user.getJob()));
    }

    @Test
    @DisplayName("Проверяем что ответ соответствует json-schema")
    void checkJsonSchemaUser() {

        int id = 2;

        given()
                .spec(Request.spec()).
                when().
                get("/api/users/{id}", id).
                then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}
