package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiClient {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public static Response registerUser(String email, String password, String name) {
        RestAssured.baseURI = BASE_URL;
        UserRegistrationRequest request = new UserRegistrationRequest(email, password, name);
        return given()
                .contentType("application/json")
                .body(request)
                .post("/api/auth/register");
    }

    public static Response loginUser(String email, String password) {
        RestAssured.baseURI = BASE_URL;
        UserLoginRequest request = new UserLoginRequest(email, password);
        return given()
                .contentType("application/json")
                .body(request)
                .post("/api/auth/login");
    }

    public static Response logoutUser(String refreshToken) {
        RestAssured.baseURI = BASE_URL;
        LogoutRequest request = new LogoutRequest(refreshToken);
        return given()
                .contentType("application/json")
                .body(request)
                .post("/api/auth/logout");
    }

    public static Response deleteUser(String accessToken) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .delete("/api/auth/user");
    }
}