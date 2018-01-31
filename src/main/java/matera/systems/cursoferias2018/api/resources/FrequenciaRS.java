package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.services.FrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/frequencia")
public class FrequenciaRS {

    @Autowired
    private FrequenciaService frequenciaService;

    @PutMapping(path = "/{disciplina}/{aluno}")
    public ResponseEntity<Void> adicionaFrequencia(@PathVariable(required = true) String disciplina,
                                                   @PathVariable(required = true) String aluno,
                                                   @RequestParam(required = false) String data) {
        frequenciaService.adicionaFrequencia(UUID.fromString(disciplina), UUID.fromString(aluno), data);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{disciplina}/{aluno}")
    public ResponseEntity<Void> removeFrequencia(@PathVariable(required = true) String disciplina,
                                                 @PathVariable(required = true) String aluno,
                                                 @RequestParam(required = false) String data) {
        frequenciaService.removeFrequencia(UUID.fromString(disciplina), UUID.fromString(aluno), data);

        return ResponseEntity.noContent().build();
    }

}
