package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("stub")
public class UsuarioRepositoryStub implements UsuarioRepository {

    public static final UUID USUARIO_1 = UUID.fromString("3480ed0e-2c8d-4a69-a8ed-7a2f136c4c20");
    public static final UUID USUARIO_2 = UUID.fromString("bc51c8bb-bad3-47e4-af0c-7f467148f23d");
    public static final UUID USUARIO_3 = UUID.fromString("4a8975d1-9e37-4872-bd35-1050707484f8");

    private static final Map<UUID, UsuarioEntity> db = new HashMap<>();

    static {
        {
            UsuarioEntity entity = new UsuarioEntity();
            entity.setUuid(USUARIO_1);
            entity.setEmail("usuario_1@domain.com");
            entity.setLogin("usuario_1");
            entity.setNome("Usuario Um");
            entity.setPerfil("USUARIO");
            entity.setUrlPhoto("http://bucket/usuario/1/perfil.png");
            db.put(USUARIO_1, entity);
        }

        {
            UsuarioEntity entity = new UsuarioEntity();
            entity.setUuid(USUARIO_2);
            entity.setEmail("usuario_2@domain.com");
            entity.setLogin("usuario_2");
            entity.setNome("Usuario Dois");
            entity.setPerfil("ADMINISTRADOR");
            entity.setUrlPhoto("http://bucket/usuario/2/perfil.png");
            db.put(USUARIO_2, entity);
        }

        {
            UsuarioEntity entity = new UsuarioEntity();
            entity.setUuid(USUARIO_3);
            entity.setEmail("usuario_3@domain.com");
            entity.setLogin("usuario_3");
            entity.setNome("Usuario Tres");
            entity.setPerfil("USUARIO");
            entity.setUrlPhoto("http://bucket/usuario/3/perfil.png");
            db.put(USUARIO_3, entity);
        }
    }

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
