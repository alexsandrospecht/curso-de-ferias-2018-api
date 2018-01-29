package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuariosRS {

    @Autowired
    private UsuarioService service;

    @PostMapping(
            consumes = { "application/json", "application/xml" }
    )
    public ResponseEntity<String> create(@RequestBody CriarUsuarioRequest request) {

        final UUID createdUser = service.criar(request);
        return ResponseEntity.status(201)
                .header("location", "/usuarios/" + createdUser)
                .build();
    }

    @DeleteMapping(value = "{usuarioID}")
    public ResponseEntity<Void> delete(@PathVariable  String usuarioID) {

        service.deletar(UUID.fromString(usuarioID));
        return ResponseEntity.status(204).build();
    }

    @PutMapping(
            value = "{usuarioID}",
            consumes = { "application/json", "application/xml" }
    )
    public ResponseEntity<Void> update(@PathVariable String usuarioID, @RequestBody AtualizarUsuarioRequest request) {

        service.atualizar(UUID.fromString(usuarioID), request);
        return ResponseEntity.status(200).build();
    }

    @GetMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<List<UsuarioResponse>> all() {

        final List<UsuarioResponse> usuarios = service.getUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping(
            value = "{usuarioID}",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<UsuarioResponse> findByID(@PathVariable String usuarioID) {

        final Optional<UsuarioResponse> usuario = service.findUsuarioByID(UUID.fromString(usuarioID));
        if (usuario.isPresent()) {
            return ResponseEntity.status(200).body(usuario.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}
