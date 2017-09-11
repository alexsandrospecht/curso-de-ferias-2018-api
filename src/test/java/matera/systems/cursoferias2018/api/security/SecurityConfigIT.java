package matera.systems.cursoferias2018.api.security;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.domain.request.UsuarioLoginRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioLoginResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class SecurityConfigIT {

    private static final String LOGIN_URL = "http://localhost:8080/login";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final int OK_HTTP_STATUS_CODE = 200;
    private static final int UNAUTHORIZED_HTTP_STATUS_CODE = 401;
    private static final String USUARIOS_URL = "http://localhost:8080/usuarios";
    private static final String X_ACCESS_TOKEN = "{7208cc23-f2f1-443f-b048-b5f6b7bde2e5}";

    @Test
    public void efetuaLogin() {

        UsuarioLoginRequest usuarioLoginRequest= new UsuarioLoginRequest();
        usuarioLoginRequest.setNome("john.doe");
        usuarioLoginRequest.setSenha("senh@");

        Response response =
            RestAssured
                .given()
                    .header("Accept", APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                    .body(usuarioLoginRequest)
                    .post(LOGIN_URL)
                .thenReturn();

        UsuarioLoginResponse usuarioLoginResponse = response.getBody().as(UsuarioLoginResponse.class);
        UsuarioResponse usuario = usuarioLoginResponse.getUsuario();

        Assert.assertEquals(X_ACCESS_TOKEN, usuarioLoginResponse.getToken());
        Assert.assertEquals("John Doe", usuario.getNome());
        Assert.assertEquals("john.doe", usuario.getLogin());
        Assert.assertEquals("john.doe@email.com", usuario.getEmail());
        Assert.assertEquals("ADMINISTRADOR", usuario.getPerfil());

        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void efetuaLoginCredenciaisInvalidas() {

        UsuarioLoginRequest usuarioLoginRequest= new UsuarioLoginRequest();
        usuarioLoginRequest.setNome("john.doe");

        Response response =
                RestAssured
                    .given()
                        .header("Accept", APPLICATION_JSON)
                        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                        .body(usuarioLoginRequest)
                        .post(LOGIN_URL)
                    .thenReturn();

        Assert.assertEquals(UNAUTHORIZED_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void acessaServicoSemToken() {

        Response response =
                RestAssured
                        .given()
                        .header("Accept", APPLICATION_JSON)
                        .get(USUARIOS_URL)
                        .thenReturn();

        Assert.assertEquals(UNAUTHORIZED_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void acessaServicoComToken() {

        Response response =
                RestAssured
                    .given()
                        .header("Accept", APPLICATION_JSON)
                        .header("X-Access-Key", X_ACCESS_TOKEN)
                        .get(USUARIOS_URL)
                    .thenReturn();

        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

}
