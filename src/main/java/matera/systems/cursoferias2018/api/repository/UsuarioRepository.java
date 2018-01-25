package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;

import java.util.Collection;
import java.util.UUID;

public interface UsuarioRepository {

    UUID create(UsuarioEntity usuario);
    boolean delete(UUID id);
    void update(String id, AtualizarUsuarioRequest request);
    Collection<UsuarioEntity> list();
    UsuarioResponse getUsuarioByID(String id);

}
