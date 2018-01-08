package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.Application;
import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.DisciplinaRepository;
import matera.systems.cursoferias2018.api.repository.DisciplinasRepositoryStub;
import matera.systems.cursoferias2018.api.repository.UsuarioRepositoryStub;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Base64;

public class FrequenciaResourceIT {

    static final String FREQUENCIA_URL = "/frequencia";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String LOCATION_HEADER = "location";
    static final int CREATED_HTTP_STATUS_CODE = 201;
    static final int NO_CONTENT_HTTP_STATUS_CODE = 204;

    private int portNumber = 8080;

    @Test
    public void adicionaPresenca() {

        Response response =
                RestAssured
                    .given()
                        .port(portNumber)
                        .header(getAuthorizationHeader())
                        .header(CONTENT_TYPE_HEADER, "application/json")
                    .when()
                        .post(FREQUENCIA_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_1.toString()
                                + "/"  +  UsuarioRepositoryStub.USUARIO_1.toString())
                    .thenReturn();

        Assert.assertEquals(CREATED_HTTP_STATUS_CODE, response.getStatusCode());
    }

    public void removePresenca() {

        Response response =
                RestAssured
                    .given()
                        .port(portNumber)
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
