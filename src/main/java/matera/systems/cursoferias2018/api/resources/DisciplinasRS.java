package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.services.DisciplinaService;
import matera.systems.cursoferias2018.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/disciplinas")
public class DisciplinasRS {

    @Autowired
    private DisciplinaService service;

    @GetMapping(
            value = "{disciplinaID}",
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<DisciplinaResponse> findByID(@PathVariable String disciplinaID) {

        final Optional<DisciplinaResponse> usuario = service.findByID(UUID.fromString(disciplinaID));
        if (usuario.isPresent()) {
            return ResponseEntity.status(200).body(usuario.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(
            value = "{disciplinaID}",
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<Void> update(@PathVariable String disciplinaID, @RequestBody AtualizarDisciplinaRequest request) {

        service.atualizar(UUID.fromString(disciplinaID), request);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping(value = "{disciplinaID}")
    public ResponseEntity<Void> delete(@PathVariable String disciplinaID) {

        service.deletar(UUID.fromString(disciplinaID));
        return ResponseEntity.status(204).build();
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<DisciplinaResponse>> all() {

        final List<DisciplinaResponse> disciplinas = service.getDisciplinas();
        return ResponseEntity.status(200).body(disciplinas);
    }
    @PostMapping(
            consumes = { "application/json", "application/xml" }
    )
    public ResponseEntity<String> create(@RequestBody CriarDisciplinaRequest request) {

        final UUID created = service.criar(request);
        return ResponseEntity.status(201)
                .header("location", "/disciplinas/" + created)
                .build();
    }
    @GetMapping(
            value = "{disciplinaID}/alunos",
            produces = {"application/json", "application/xml"}
    )
    public ResponseEntity<List<UsuarioResponse>> findAlunosPorDisciplinaID(@PathVariable UUID disciplinaID) {

        List<UsuarioResponse> usuarios = service.findUsuariosByDisciplinaID(disciplinaID);
        if (usuarios.size() > 0) {
            return ResponseEntity.status(200).body(usuarios);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
