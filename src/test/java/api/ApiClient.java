package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public static Response registerUser(String email, String password, String name) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"name\":\"" + name + "\"}")
                .post("/api/auth/register");
    }

    public static Response loginUser(String email, String password) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .post("/api/auth/login");
    }

    public static Response logoutUser(String refreshToken) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType("application/json")
                .body("{\"token\":\"" + refreshToken + "\"}")
                .post("/api/auth/logout");
    }

    public static Response deleteUser(String accessToken) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .delete("/api/auth/user");
    }
}