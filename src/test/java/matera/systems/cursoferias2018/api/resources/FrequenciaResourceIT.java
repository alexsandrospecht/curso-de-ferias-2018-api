package matera.systems.cursoferias2018.api.resources;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.repository.DisciplinasRepositoryStub;
import matera.systems.cursoferias2018.api.repository.UsuarioRepositoryStub;

public class FrequenciaResourceIT {

    static final String FREQUENCIA_URL = "/frequencia";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final int NO_CONTENT_HTTP_STATUS_CODE = 204;

    @Test
    public void adicionaPresenca() {

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header(CONTENT_TYPE_HEADER, "application/json")
                    .when()
                        .put(FREQUENCIA_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_1.toString()
                                + "/"  +  UsuarioRepositoryStub.USUARIO_1.toString())
                    .thenReturn();

        Assert.assertEquals(NO_CONTENT_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void removePresenca() {

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header(CONTENT_TYPE_HEADER, "application/json")
                    .when()
                        .delete(FREQUENCIA_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_1.toString()
                                + "/"  +  UsuarioRepositoryStub.USUARIO_1.toString())
                    .thenReturn();

        Assert.assertEquals(NO_CONTENT_HTTP_STATUS_CODE, response.getStatusCode());
    }

    private Header getAuthorizationHeader() {

        String clientBasicAuthCredentials =
                Base64.getEncoder().encodeToString("angular:alunos".getBytes());

        Response response = RestAssured.given()
                .header(new Header("Authorization", "Basic " + clientBasicAuthCredentials))
                .queryParam("username", "usuario")
                .queryParam("password", "password")
                .queryParam("grant_type", "password")
                .when()
                .post("/oauth/token")
                .then().extract().response();

        String token = response.getBody().jsonPath().getString("access_token");

        return new Header("Authorization", "bearer " + token);
    }
}
