package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;

import java.util.Collection;
import java.util.UUID;

public interface UsuarioRepository {

    UUID create(UsuarioEntity usuario);
    boolean delete(UUID id);
    void update(String id, UsuarioEntity request);
    Collection<UsuarioEntity> list();
    UsuarioEntity getUsuarioByID(UUID id);

}
