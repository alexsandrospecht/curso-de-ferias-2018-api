package matera.systems.cursoferias2018.api.resources;

import matera.systems.cursoferias2018.api.services.FrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(path = "/frequencia")
public class FrequenciaRS {

    @Autowired
    private FrequenciaService service;

    @PutMapping(value = "{idDisciplina}/{idAluno}")
    public ResponseEntity<Void> incluirPresenca(
            @PathVariable String idDisciplina,
            @PathVariable  String idAluno,
            @RequestParam(required = false) String data
    ) throws Exception {

        UUID disciplina = UUID.fromString(idDisciplina);
        UUID aluno = UUID.fromString(idAluno);
        Date dataPresenca = getDataPresenca(data);

        service.incluirPresenca(disciplina, aluno, dataPresenca);

        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(value = "{idDisciplina}/{idAluno}")
    public ResponseEntity<Void> deletarPresenca(
            @PathVariable String idDisciplina,
            @PathVariable  String idAluno,
            @RequestParam(required = false) String data
    ) throws Exception {

        UUID disciplina = UUID.fromString(idDisciplina);
        UUID aluno = UUID.fromString(idAluno);
        Date dataPresenca = getDataPresenca(data);

        service.removerPresenca(disciplina, aluno, dataPresenca);

        return ResponseEntity.status(204).build();
    }

    private Date getDataPresenca(String data) throws Exception {
        Date dataPresenca = Calendar.getInstance().getTime();
        if (data != null && !data.isEmpty()) {
            dataPresenca = readDataFromString(data);
        }
        return dataPresenca;
    }

    private Date readDataFromString(String data) throws Exception {
        return new SimpleDateFormat("YYYY-MM-DD").parse(data);
    }

}
