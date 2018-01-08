package matera.systems.cursoferias2018.api.repository;

import java.util.Date;
import java.util.UUID;

public interface FrequenciaRepository {

    void incluir(UUID disciplina, UUID aluno, Date data);

    void remover(UUID disciplina, UUID aluno, Date data);
}
