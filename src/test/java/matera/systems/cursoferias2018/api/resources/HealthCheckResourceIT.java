package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import org.junit.Test;

public class HealthCheckResourceIT {

    @Test
    public void run() {

        RestAssured
            .given()
                .get("http://localhost:8080/health")
            .then()
                .statusCode(200);
    }
}
