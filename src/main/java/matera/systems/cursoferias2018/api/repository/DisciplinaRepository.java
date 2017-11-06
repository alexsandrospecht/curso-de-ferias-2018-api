package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DisciplinaRepository {

    Optional<DisciplinaEntity> findByID(UUID id);

    void atualizar(DisciplinaEntity update);

    void deletar(UUID id);

    List<UsuarioResponse> findUsuariosByDisciplinaID(UUID id);

    List<DisciplinaEntity> listar();

    UUID criar(DisciplinaEntity disciplina);
}
