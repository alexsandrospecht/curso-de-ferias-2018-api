package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioResource {

    private static final String USER_PATH = "http://localhost:8080/usuarios/";

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(consumes = { "application/json"}, produces = { "application/json"})
    public ResponseEntity<?> create(@RequestBody CriarUsuarioRequest request) {
        final UUID criar = usuarioService.create(request);

        return ResponseEntity.created(URI.create(USER_PATH + criar.toString())).build();
    }

    @GetMapping(produces = { "application/json" })
    public ResponseEntity<List> findAll() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<UsuarioResponse> findByID(@PathVariable String id) {
        final UsuarioResponse user = usuarioService.getUsuarioByID(id);

        if (nonNull(user)) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}" )
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<?> update(@RequestBody AtualizarUsuarioRequest request, @PathVariable String id) {
        usuarioService.update(id, request);

        return ResponseEntity.ok().build();
    }

}
