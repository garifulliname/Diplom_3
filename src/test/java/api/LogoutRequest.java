package api;

public class LogoutRequest {
    private String token;

    public LogoutRequest(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}