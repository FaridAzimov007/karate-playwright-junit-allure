package api.auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import config.PropertiesLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class BaseRestAuth {

    public static String authToken;

    public static String getAuthToken() {
        if (authToken == null) {
            initialize();
        }
        return authToken;
    }

    private synchronized static void initialize() {
        String userLogin = PropertiesLoader.getProperties("user-email");
        String userPass = PropertiesLoader.getProperties("user-pass");
        String clientId = PropertiesLoader.getProperties("client_id");
        String clientSecret = PropertiesLoader.getProperties("client_secret");

        authenticate(userLogin, userPass, clientId, clientSecret);
    }

    private static void authenticate(String userEmail, String password, String clientId, String clientSecret) {
        String authUrl = PropertiesLoader.getProperties("auth-url");

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("email", userEmail);
        requestBody.addProperty("password", password);
        requestBody.addProperty("client_id", clientId);
        requestBody.addProperty("client_secret", clientSecret);

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post(authUrl); // Вызов метода POST

        if (response.getStatusCode() != 200) {
            log.error("Authentication failed: {} - {}", response.getStatusCode(), response.getStatusLine());
            throw new RuntimeException("Authentication failed!");
        }


        JsonObject responseBody = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
        authToken = responseBody.get("token").getAsString();
        log.info("Authentication successful. Token acquired: {}", authToken);


    }

}
