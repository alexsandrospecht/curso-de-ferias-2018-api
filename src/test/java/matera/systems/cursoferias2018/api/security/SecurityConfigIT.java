package matera.systems.cursoferias2018.api.security;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.domain.request.UsuarioLoginRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

public class SecurityConfigIT {

    private static final String URL_OAUTH = "/oauth/token";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final int OK_HTTP_STATUS_CODE = 200;
    private static final int UNAUTHORIZED_HTTP_STATUS_CODE = 401;
    private static final int BAD_REQUEST_HTTP_STATUS_CODE = 400;
    private static final String USUARIOS_URL = "/usuarios";

    private int portNumber = 8080;

    @Test
    public void efetuaLoginCredenciaisInvalidas() {

        UsuarioLoginRequest usuarioLoginRequest= new UsuarioLoginRequest();
        usuarioLoginRequest.setNome("john.doe");
        usuarioLoginRequest.setSenha("unknown");

        Response response = doLogin(usuarioLoginRequest);

        String errorDescription = response.getBody().jsonPath().getString("error_description");

        Assert.assertEquals(BAD_REQUEST_HTTP_STATUS_CODE, response.getStatusCode());
        Assert.assertEquals(errorDescription, "Bad credentials");
    }

    @Test
    public void acessaServicoAutenticacao() {

        Response response =
                RestAssured
                        .given()
                        .port(portNumber)
                        .header("Accept", APPLICATION_JSON)
                        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                        .get(USUARIOS_URL)
                        .thenReturn();

        Assert.assertEquals(UNAUTHORIZED_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void autenticaComSucesso() {

        UsuarioLoginRequest usuarioLoginRequest= new UsuarioLoginRequest();
        usuarioLoginRequest.setNome("usuario");
        usuarioLoginRequest.setSenha("password");

        Response response = doLogin(usuarioLoginRequest);

        String acessToken = response.getBody().jsonPath().getString("access_token");

        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
        Assert.assertNotNull(acessToken);
    }

    private Response doLogin(UsuarioLoginRequest usuarioLoginRequest) {

        String clientBasicAuthCredentials =
                Base64.getEncoder().encodeToString("angular:alunos".getBytes());

        Response response = RestAssured.given()
                .port(portNumber)
                .header(new Header("Authorization", "Basic " + clientBasicAuthCredentials))
                .queryParam("username", usuarioLoginRequest.getNome())
                .queryParam("password", usuarioLoginRequest.getSenha())
                .queryParam("grant_type", "password")
                .when()
                .post(URL_OAUTH)
                .then().extract().response();

        return response;
    }
}
