package com.temav.tests;

import com.temav.config.CredentialsConfig;
import com.temav.models.LombokModel;
import com.temav.models.Resource;
import com.temav.models.User;
import com.temav.specs.Specs;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiTests extends TestBase {

    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @Test
    @Tag("api")
    @DisplayName("User list test")
    @Owner("temav")
    void userListTest() {
        Response response =
                given()
                        .spec(Specs.requestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().response();

        assertThat(response).isNotNull();
        assertThat(matchesJsonSchemaInClasspath("schemas/user_list_response_schema.json"));
        assertEquals(12, (Integer) response.path("total"));
        assertEquals(2, (Integer) response.path("page"));
    }

    @Test
    @Tag("api")
    @DisplayName("Single user test")
    @Owner("temav")
    void singleUserTest() {
        LombokModel response =
                given()
                        .spec(Specs.requestSpec)
                        .when()
                        .get("/users/10")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().as(LombokModel.class);
        Integer expectedId = 10;
        String expectedFirstName = "Byron";
        String expectedLastName = "Fields";

        assertThat(matchesJsonSchemaInClasspath("schemas/single_user_response_schema.json"));
        assertEquals(expectedId, response.getUser().getId());
        assertEquals(expectedFirstName, response.getUser().getFirstName());
        assertEquals(expectedLastName, response.getUser().getLastName());
    }

    @Test
    @Tag("api")
    @DisplayName("Single user not found test")
    @Owner("temav")
    void singleUserNotFoundTest() {
        given()
                .spec(Specs.requestSpec)
                .when()
                .get("/users/13")
                .then()
                .spec(Specs.responseSpec404);
    }

    @Test
    @Tag("api")
    @DisplayName("Resources list test")
    @Owner("temav")
    void resourceListTest() {
        Response response =
                given()
                        .spec(Specs.requestSpec)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().response();
        Integer expectedTotal = 12;

        assertThat(response).isNotNull();
        assertThat(matchesJsonSchemaInClasspath("schemas/resource_list_response_schema.json"));
        assertEquals(expectedTotal, response.path("total"));
    }

    @Test
    @Tag("api")
    @DisplayName("Single resources test")
    @Owner("temav")
    void singleResourceTest() {
        Response response =
                given()
                        .spec(Specs.requestSpec)
                        .when().get("/unknown/4")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().response();
        String expectedName = "aqua sky";
        Integer expectedYear = 2003;
        String expectedColor = "#7BC4C4";
        String expectedPantoneValue = "14-4811";

        assertThat(matchesJsonSchemaInClasspath("schemas/single_resource_response_schema.json"));
        assertEquals(expectedName, response.path("data.name"));
        assertEquals(expectedYear, response.path("data.year"));
        assertEquals(expectedColor, response.path("data.color"));
        assertEquals(expectedPantoneValue, response.path("data.pantone_value"));
    }

    @Test
    @Tag("api")
    @DisplayName("Single resources not found test")
    @Owner("temav")
    void singleResourceNotFoundTest() {
        given()
                .spec(Specs.requestSpec)
                .when()
                .get("/unknown/23")
                .then()
                .spec(Specs.responseSpec404);
    }

    @Test
    @Tag("api")
    @DisplayName("Create user test")
    @Owner("temav")
    void createResourceTest() {
        Resource resource = new Resource();

        resource.setName("orange sun");
        resource.setYear(2022);
        resource.setColor("#FF5800");
        resource.setPantoneValue("16-1257");
        Resource responseResource =
                given()
                        .spec(Specs.requestSpec)
                        .body(resource)
                        .when()
                        .post("/unknown")
                        .then()
                        .spec(Specs.responseSpec201)
                        .extract().as(Resource.class);

        assertNotEquals(responseResource.getId(), null);
        assertEquals(resource.getName(), responseResource.getName());
        assertEquals(resource.getYear(), responseResource.getYear());
        assertEquals(resource.getColor(), responseResource.getColor());
        assertEquals(resource.getPantoneValue(), responseResource.getPantoneValue());
    }

    @Test
    @Tag("api")
    @DisplayName("Create user test")
    @Owner("temav")
    void createUserTest() {
        User user = new User();

        user.setFirstName(config.createUserName());
        user.setJob(config.createUserJob());
        User responseUser =
                given()
                        .spec(Specs.requestSpec)
                        .body(user)
                        .when()
                        .post("/user")
                        .then()
                        .spec(Specs.responseSpec201)
                        .extract().as(User.class);

        assertNotEquals(responseUser.getId(), null);
        assertEquals(user.getFirstName(), responseUser.getFirstName());
        assertEquals(user.getJob(), responseUser.getJob());
    }

    @Test
    @Tag("api")
    @DisplayName("Update user test with PUT request")
    @Owner("temav")
    void updateUserPutTest() {
        User user = new User();

        user.setFirstName(config.createUserName());
        user.setJob(config.updateUserJob());
        User responseUser =
                given()
                        .spec(Specs.requestSpec)
                        .body(user)
                        .when()
                        .put("/user/2")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().as(User.class);

        assertEquals(user.getJob(), responseUser.getJob());
    }

    @Test
    @Tag("api")
    @DisplayName("Update user test with PATCH request")
    @Owner("temav")
    void updateUserPatchTest() {
        User user = new User();

        user.setFirstName(config.createUserName());
        user.setJob(config.updateUserJob());
        User responseUser =
                given()
                        .spec(Specs.requestSpec)
                        .body(user)
                        .when()
                        .patch("/user/2")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().as(User.class);

        assertEquals(user.getJob(), responseUser.getJob());
    }

    @Test
    @Tag("api")
    @DisplayName("Delete user test")
    @Owner("temav")
    void deleteUserTest() {
        given()
                .spec(Specs.requestSpec)
                .when()
                .delete("/user/2")
                .then()
                .spec(Specs.responseSpec204);
    }

    @Test
    @Tag("api")
    @DisplayName("Successful register user test")
    @Owner("temav")
    void registerSuccessfulTest() {
        User user = new User();

        user.setEmail(config.registerUserEmail());
        user.setPassword(config.registerUserPassword());
        User responseUser =
                given()
                        .spec(Specs.requestSpec)
                        .body(user)
                        .when()
                        .post("/register")
                        .then()
                        .spec(Specs.responseSpec200)
                        .extract().as(User.class);

        assertThat(responseUser.getId()).isNotNull();
        assertThat(responseUser.getToken()).isNotNull();
    }
    @Test
    @Tag("api")
    @DisplayName("Unsuccessful register user test")
    @Owner("temav")
    void registerUnsuccessfulTest() {
        User user = new User();

        user.setEmail("sydney@fife");
        User responseUser =
                given()
                        .spec(Specs.requestSpec)
                        .body(user)
                        .when()
                        .post("/register")
                        .then()
                        .spec(Specs.responseSpec400)
                        .extract().as(User.class);
        String expectedError = "Missing password";

        assertEquals(expectedError, responseUser.getError());
    }

    @Test
    @Tag("api")
    @DisplayName("Successful login user test")
    @Owner("temav")
    void loginSuccessfulTest() {
        User user = new User();

        user.setEmail(config.registerUserEmail());
        user.setPassword(config.loginUserPassword());
        User responseUser = given()
                .spec(Specs.requestSpec)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(Specs.responseSpec200)
                .extract().as(User.class);

        assertThat(responseUser.getToken()).isNotNull();

    }
    @Test
    @Tag("api")
    @DisplayName("Successful login user test")
    @Owner("temav")
    void loginUnsuccessfulTest() {
        User user = new User();

        user.setEmail("peter@klaven");
        User responseUser = given()
                .spec(Specs.requestSpec)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(Specs.responseSpec400)
                .extract().as(User.class);
        String expectedError = "Missing password";

        assertEquals(expectedError, responseUser.getError());
    }
}
