package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.domain.request.CreateUserRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class UsuariosResourceIT {

    static final String USUARIOS_URL = "http://localhost:8080/usuarios";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String LOCATION_HEADER = "location";
    static final int CREATED_HTTP_STATUS_CODE = 201;
    static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    static final String LOCATION_PATTERN = USUARIOS_URL + "/" + UUID_REGEX;

    @Test
    public void criarUsuario() {

        CreateUserRequest createRequest = new CreateUserRequest();
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
        // TODO
    }

    @Test
    public void buscarUsuarioPorId() {

        Response response =
            RestAssured
                .given()
                    .header("Accept", "application/json")
                    .get(USUARIOS_URL + "/usuario/idusuario")
                .thenReturn();

        UsuarioResponse usuario = response.getBody().as(UsuarioResponse.class);
        Assert.assertEquals("Paulo Almeida", usuario.getNome());
        Assert.assertEquals("rochapaulo", usuario.getLogin());
        Assert.assertEquals("paulo.almeida@matera.com", usuario.getEmail());
        Assert.assertEquals("ADMINISTRADOR", usuario.getPerfil());
        Assert.assertEquals("https://s.gravatar.com/avatar/27b57f4f9580f95c4cbe78bb6d3ec893?s=80", usuario.getUrlPhoto());

    }

    @Test
    public void atualizaUsuario() {
        // TODO
    }

    @Test
    public void deleteUsuario() {
        // TODO
    }
    
}
