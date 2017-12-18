package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.repository.UsuarioRepositoryStub;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

public class UsuariosResourceIT {

    static final String USUARIOS_URL = "http://localhost:8080/usuarios";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String LOCATION_HEADER = "location";
    static final int NO_CONTENT_HTTP_STATUS_CODE = 204;
    static final int CREATED_HTTP_STATUS_CODE = 201;
    static final int OK_HTTP_STATUS_CODE = 200;
    static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    static final String LOCATION_PATTERN = "/usuarios/" + UUID_REGEX;

    @Test
    public void criarUsuario() {

        CriarUsuarioRequest createRequest = new CriarUsuarioRequest();
        createRequest.setNome("John Doe");
        createRequest.setLogin("john.doe");
        createRequest.setEmail("john.doe@email.com");
        createRequest.setPerfil("ADMINISTRADOR");
        createRequest.setUrlPhoto("http://pictures.pic/johndoe");

        Response response =
            RestAssured
                .given()
                    .body(createRequest)
                    .header(CONTENT_TYPE_HEADER, "application/json")
                .when()
                    .post(USUARIOS_URL)
                    .thenReturn();

        Assert.assertEquals(CREATED_HTTP_STATUS_CODE, response.getStatusCode());

        String location = response.getHeader(LOCATION_HEADER);
        Assert.assertTrue(location.matches(LOCATION_PATTERN));
    }

    @Test
    public void buscaTodoUsuarios() {

        Response response =
            RestAssured
                .given()
                    .header("Accept", "application/json")
                    .get(USUARIOS_URL)
                .thenReturn();

        UsuarioResponse[] usuarios = response.getBody().as(UsuarioResponse[].class);

        Assert.assertThat(Arrays.asList(usuarios), Matchers.not(Matchers.empty()));
        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void buscarUsuarioPorId() {

        Response response =
            RestAssured
                .given()
                    .header("Accept", "application/json")
                    .get(USUARIOS_URL + "/" + UsuarioRepositoryStub.USUARIO_2.toString())
                .thenReturn();

        UsuarioResponse usuario = response.getBody().as(UsuarioResponse.class);

        Assert.assertEquals(UsuarioRepositoryStub.USUARIO_2, usuario.getUuid());
        Assert.assertEquals("Usuario Dois", usuario.getNome());
        Assert.assertEquals("usuario_2", usuario.getLogin());
        Assert.assertEquals("usuario_2@domain.com", usuario.getEmail());
        Assert.assertEquals("ADMINISTRADOR", usuario.getPerfil());
        Assert.assertEquals("http://bucket/usuario/2/perfil.png", usuario.getUrlPhoto());
        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());

    }

    @Test
    public void atualizaUsuario() {

        AtualizarUsuarioRequest atualizarUsuarioRequest = new AtualizarUsuarioRequest();
        atualizarUsuarioRequest.setNome("Nome Atualizado");

        Response response =
                RestAssured
                    .given()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(atualizarUsuarioRequest)
                        .put(USUARIOS_URL + "/" + UsuarioRepositoryStub.USUARIO_3.toString())
                    .thenReturn();

        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void deleteUsuario() {

        Response response =
                RestAssured
                    .given()
                        .header("Accept", "application/json")
                        .delete(USUARIOS_URL + "/" + UsuarioRepositoryStub.USUARIO_1.toString())
                    .thenReturn();

        Assert.assertEquals(NO_CONTENT_HTTP_STATUS_CODE, response.getStatusCode());
    }

}
