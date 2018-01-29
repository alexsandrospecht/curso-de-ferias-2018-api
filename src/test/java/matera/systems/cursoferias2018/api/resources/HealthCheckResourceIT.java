package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Base64;

/**
 * Created by rochapaulo on 04/09/17.
 */
public class HealthCheckResourceIT {

    @Test
    public void run() {

        RestAssured
            .given()
                .header(getAuthorizationHeader())
                .get("http://localhost:8080/health")
            .then()
                .statusCode(200);
    }

    private Header getAuthorizationHeader() {

        String clientBasicAuthCredentials =
                Base64.getEncoder().encodeToString("angular:alunos".getBytes());

        Response response = RestAssured.given().
                header(new Header("Authorization", "Basic " + clientBasicAuthCredentials))
                .queryParam("username", "admin")
                .queryParam("password", "admin")
                .queryParam("grant_type", "password")
                .when()
                .post("http://localhost:8080/oauth/token")
                .then().extract().response();

        String token = response.getBody().jsonPath().getString("access_token");

        return new Header("Authorization", "bearer " + token);
    }

}
