package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriaDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.DisciplinasRepositoryStub;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Base64;

public class DisciplinasResourceIT {

    static final String DISCIPLINAS_URL = "http://localhost:8080/disciplinas";
    static final String ALUNOS = "alunos";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String LOCATION_HEADER = "location";
    static final int NO_CONTENT_HTTP_STATUS_CODE = 204;
    static final int CREATED_HTTP_STATUS_CODE = 201;
    static final int OK_HTTP_STATUS_CODE = 200;
    static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    static final String LOCATION_PATTERN = "/disciplinas/" + UUID_REGEX;

    @Test
    public void buscarDisciplinaPorId() {

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header("Accept", "application/json")
                    .get(DISCIPLINAS_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_2.toString())
                        .thenReturn();

        DisciplinaResponse disciplina = response.getBody().as(DisciplinaResponse.class);
        Assert.assertNotNull(disciplina);
        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void atualizaDisciplina() {

        AtualizarDisciplinaRequest atualizarUsuarioRequest = new AtualizarDisciplinaRequest();
        atualizarUsuarioRequest.setDescricao("Descricao");

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(atualizarUsuarioRequest)
                    .put(DISCIPLINAS_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_3.toString())
                        .thenReturn();

        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void removeDisciplina() {

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header(getAuthorizationHeader())
                        .header("Accept", "application/json")
                    .delete(DISCIPLINAS_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_1.toString())
                        .thenReturn();

        Assert.assertEquals(NO_CONTENT_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void buscarAlunosDaDisciplinaPorId() {

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header("Accept", "application/json")
                    .get(DISCIPLINAS_URL + "/" + DisciplinasRepositoryStub.DISCIPLINA_2.toString() + "/" + ALUNOS)
                        .thenReturn();

        UsuarioResponse[] usuarios = response.getBody().as(UsuarioResponse[].class);

        Assert.assertThat(Arrays.asList(usuarios), Matchers.not(Matchers.empty()));
        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void buscarTodasDisciplinas() {

        Response response =
                RestAssured
                    .given()
                        .header(getAuthorizationHeader())
                        .header("Accept", "application/json")
                    .get(DISCIPLINAS_URL)
                        .thenReturn();

        DisciplinaResponse[] disciplinas = response.getBody().as(DisciplinaResponse[].class);

        Assert.assertThat(Arrays.asList(disciplinas), Matchers.not(Matchers.empty()));
        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void criarDisciplina() {

        CriaDisciplinaRequest createRequest = new CriaDisciplinaRequest();
        createRequest.setDescricao("Back-end Java");
        createRequest.setDataInicio("29/01/2018");
        createRequest.setDataTermino("29/01/2018");
        createRequest.setSegmento("Backend");
        createRequest.setUrlLogo("http://pictures.pic/course1");

        Response response =
                RestAssured
                        .given()
                        .body(createRequest)
                        .header(getAuthorizationHeader())
                        .header(CONTENT_TYPE_HEADER, "application/json")
                        .when()
                        .post(DISCIPLINAS_URL)
                        .thenReturn();

        Assert.assertEquals(CREATED_HTTP_STATUS_CODE, response.getStatusCode());

        String location = response.getHeader(LOCATION_HEADER);
        Assert.assertTrue(location.matches(LOCATION_PATTERN));
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
