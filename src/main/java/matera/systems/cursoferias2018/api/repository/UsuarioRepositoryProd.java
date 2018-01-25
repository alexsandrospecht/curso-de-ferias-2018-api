package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@Profile("prod")
public class UsuarioRepositoryProd implements UsuarioRepository {

    private static final Map<UUID, UsuarioEntity> db = new HashMap<>();

    @Override
    public UUID create(UsuarioEntity usuario) {
        db.put(usuario.getUuid(), usuario);
        return usuario.getUuid();
    }

    @Override
    public boolean delete(UUID id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void update(String id, UsuarioEntity entity) {
        final UUID uuid = UUID.fromString(id);
        db.put(uuid, entity);
    }

    @Override
    public Collection<UsuarioEntity> list() {
        return db.values();
    }

    @Override
    public UsuarioEntity getUsuarioByID(UUID id) {
        return db.get(id);
    }

}
