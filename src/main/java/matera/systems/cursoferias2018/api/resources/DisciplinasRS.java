package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriaDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.services.DisciplinasService;
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
    private DisciplinasService service;

    @GetMapping(
            value = "{id}",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<DisciplinaResponse> findByID(@PathVariable String id) {

        final Optional<DisciplinaResponse> disciplina = service.findDisciplinaByID(UUID.fromString(id));
        if (disciplina.isPresent()) {
            return ResponseEntity.status(200).body(disciplina.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<List<DisciplinaResponse>> findAll() {
        List<DisciplinaResponse> all = service.findAll();
        return ResponseEntity.status(200).body(all);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable  String id) {
        service.remover(UUID.fromString(id));
        return ResponseEntity.status(204).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> atualizar(@RequestBody AtualizarDisciplinaRequest request, @PathVariable String id) {
        service.atualizar(UUID.fromString(id), request);

        return ResponseEntity.status(200).build();
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody CriaDisciplinaRequest request) {
        final UUID created = service.criar(request);
        return ResponseEntity.status(201)
                .header("location", "/disciplinas/" + created)
                .build();
    }

    @GetMapping( path = "/{id}/alunos",
            produces = { "application/json", "application/xml" }
    )
    public ResponseEntity<List<UsuarioResponse>> findAlunosByDisciplinaId(@PathVariable String id) {
        final List<UsuarioResponse> alunos = service.findAlunosByDisciplinaId(UUID.fromString(id));
        return ResponseEntity.status(200).body(alunos);
    }

}
