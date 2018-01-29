package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.DateRange;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.RelatorioResponse;
import matera.systems.cursoferias2018.api.domain.response.RelatorioResponseEntry;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.exceptions.AlunoNotFound;
import matera.systems.cursoferias2018.api.exceptions.DisciplinaNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RelatorioService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private FrequenciaService frequenciaService;

    public RelatorioResponse frequenciaByDisciplina(UUID disciplinaID, DateRange range) throws Exception {

        RelatorioResponse relatorio = new RelatorioResponse();

        List<RelatorioResponseEntry> entries = new ArrayList<>();
        for (UsuarioResponse aluno : disciplinaService.findUsuariosByDisciplinaID(disciplinaID)) {

            final Optional<DisciplinaResponse> disciplina = disciplinaService.findByID(disciplinaID);
            if (disciplina.isPresent()) {

                String dataInicio = disciplina.get().getDataInicio();
                String dataFim = disciplina.get().getDataTermino();

                SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
                int totalAulas = Integer.parseInt(sdf.format(dataFim)) - Integer.parseInt(sdf.format(dataInicio));

                List<String> presencas = frequenciaService.findFrequenciaByAlunoId(aluno.getUuid());
                RelatorioResponseEntry entry = new RelatorioResponseEntry();

                entry.setUsuario(aluno);
                entry.setPresenca(presencas);
                entry.setFrequencia(totalAulas - presencas.size());

            } else {
                throw new DisciplinaNotFound();
            }

        }

        return relatorio;
    }

    public RelatorioResponse frequenciaByDisciplinaAndAluno(UUID disciplinaID, UUID alunoID, DateRange range) {

        RelatorioResponse relatorio = new RelatorioResponse();

        List<RelatorioResponseEntry> entries = new ArrayList<>();
        Optional<UsuarioResponse> aluno =
                disciplinaService.findUsuariosByDisciplinaID(disciplinaID).stream().filter(_aluno -> _aluno.getUuid().equals(alunoID)).findFirst();

        if (aluno.isPresent()) {

            final Optional<DisciplinaResponse> disciplina = disciplinaService.findByID(disciplinaID);

            if (disciplina.isPresent()) {

                String dataInicio = disciplina.get().getDataInicio();
                String dataFim = disciplina.get().getDataTermino();

                SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
                int totalAulas = Integer.parseInt(sdf.format(dataFim)) - Integer.parseInt(sdf.format(dataInicio));

                List<String> presencas = frequenciaService.findFrequenciaByAlunoId(aluno.get().getUuid());
                RelatorioResponseEntry entry = new RelatorioResponseEntry();

                entry.setUsuario(aluno.get());
                entry.setPresenca(presencas);
                entry.setFrequencia(totalAulas - presencas.size());

            } else {
                throw new DisciplinaNotFound();
            }

        } else {
            throw new AlunoNotFound();
        }

        return relatorio;
    }

}
