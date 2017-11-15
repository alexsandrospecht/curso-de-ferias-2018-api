package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.Application;
import matera.systems.cursoferias2018.api.domain.request.UsuarioLoginRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Import(Application.class)
@ActiveProfiles(profiles = "stub")
public class HealthCheckResourceIT {

    @LocalServerPort
    private int portNumber;

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
