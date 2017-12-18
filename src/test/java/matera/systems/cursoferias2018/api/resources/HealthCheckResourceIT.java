package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Base64;

public class HealthCheckResourceIT {

    private int portNumber = 8080;

    @Test
    public void run() {

        RestAssured
            .given()
                .port(portNumber)
                .header(getAuthorizationHeader())
                .get("/health")
            .then()
                .statusCode(200);
    }

    private Header getAuthorizationHeader() {

        String clientBasicAuthCredentials =
                Base64.getEncoder().encodeToString("angular:alunos".getBytes());

        Response response = RestAssured.given()
                    .port(portNumber)
                    .header(new Header("Authorization", "Basic " + clientBasicAuthCredentials))
                    .queryParam("username", "admin")
                    .queryParam("password", "admin")
                    .queryParam("grant_type", "password")
                .when()
                    .post("/oauth/token")
                .then().extract().response();

        String token = response.getBody().jsonPath().getString("access_token");

        return new Header("Authorization", "bearer " + token);
    }

}
