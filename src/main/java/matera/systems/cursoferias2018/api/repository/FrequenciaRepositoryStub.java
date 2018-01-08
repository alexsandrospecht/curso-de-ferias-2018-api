package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("stub")
public class FrequenciaRepositoryStub implements FrequenciaRepository {

    private Map<EntryKey, FrequenciaEntity> frequencias = new HashMap<>();


    @Override
    public void incluir(UUID disciplina, UUID aluno, Date data) {
        final EntryKey key = new EntryKey(disciplina, aluno);
        if (frequencias.containsKey(key)) {
            FrequenciaEntity entity = frequencias.get(key);
            entity.addPresenca(data);
            frequencias.put(new EntryKey(disciplina, aluno), entity);
        } else {
            FrequenciaEntity entity = new FrequenciaEntity();
            entity.setDisciplinaId(disciplina);
            entity.setUsuarioId(aluno);
            frequencias.put(new EntryKey(disciplina, aluno), entity);
        }
    }

    @Override
    public void remover(UUID disciplina, UUID aluno, Date data) {
        final EntryKey key = new EntryKey(disciplina, aluno);
        if (frequencias.containsKey(key)) {
            FrequenciaEntity entity = frequencias.get(key);
            entity.removePresenca(data);
        }
    }

    private class EntryKey {

        private final UUID disciplina, aluno;

        EntryKey(UUID disciplina, UUID aluno) {
            this.disciplina = disciplina;
            this.aluno = aluno;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EntryKey entryKey = (EntryKey) o;
            return Objects.equals(disciplina, entryKey.disciplina) &&
                    Objects.equals(aluno, entryKey.aluno);
        }

        @Override
        public int hashCode() {
            return Objects.hash(disciplina, aluno);
        }
    }

}
