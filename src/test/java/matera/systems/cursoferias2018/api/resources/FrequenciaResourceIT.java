package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.Application;
import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Import(Application.class)
@ActiveProfiles(profiles = "stub")
public class FrequenciaResourceIT {

    static final String FREQUENCIA_URL = "/frequencia";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String LOCATION_HEADER = "location";
    static final int NO_CONTENT_HTTP_STATUS_CODE = 204;
    static final int CREATED_HTTP_STATUS_CODE = 201;
    static final int OK_HTTP_STATUS_CODE = 200;
    static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    static final String UUID_DISCIPLINA_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

    @LocalServerPort
    private int portNumber;

}
