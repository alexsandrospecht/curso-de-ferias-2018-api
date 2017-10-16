package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    UUID criar(UsuarioEntity usuario);

    void deletar(UUID uuid);

    List<UsuarioEntity> listar();

    Optional<UsuarioEntity> findByID(UUID uuid);

    void atualizar(UsuarioEntity update);

}
