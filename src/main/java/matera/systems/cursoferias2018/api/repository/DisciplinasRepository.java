package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DisciplinasRepository {

    UUID criar(DisciplinaEntity disciplina);

    void deletar(UUID uuid);

    List<DisciplinaEntity> listar();

    Optional<DisciplinaEntity> findByID(UUID uuid);

    void atualizar(DisciplinaEntity update);

}
