package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("stub")
public class DisciplinasRepositoryStub implements DisciplinasRepository {

    private static final Map<UUID, DisciplinaEntity> data = new HashMap<>();

    public static final UUID DISCIPLINA_1 = UUID.fromString("3480ed0e-2c8d-4a69-a8ed-7a2f136c4c20");
    public static final UUID DISCIPLINA_2 = UUID.fromString("bc51c8bb-bad3-47e4-af0c-7f467148f23d");
    public static final UUID DISCIPLINA_3 = UUID.fromString("4a8975d1-9e37-4872-bd35-1050707484f8");

    static {
        {
            DisciplinaEntity entity = new DisciplinaEntity();
            entity.setId(DISCIPLINA_1);
            entity.setDataInicio("01/01/2018");
            entity.setDataTermino("01/01/2019");
            entity.setDescricao("DISCIPLINA 1");
            entity.setSegmento("Backend");
            entity.setUrlLogo("http://bucket/usuario/1/perfil.png");
            data.put(DISCIPLINA_1, entity);
        }

        {
            DisciplinaEntity entity = new DisciplinaEntity();
            entity.setId(DISCIPLINA_2);
            entity.setDataInicio("02/02/2018");
            entity.setDataTermino("02/02/2019");
            entity.setDescricao("DISCIPLINA 2");
            entity.setSegmento("Backend");
            entity.setUsuarios(Arrays.asList(new UsuarioEntity(), new UsuarioEntity()));
            entity.setUrlLogo("http://bucket/usuario/1/perfil.png");
            data.put(DISCIPLINA_2, entity);
        }

        {
            DisciplinaEntity entity = new DisciplinaEntity();
            entity.setId(DISCIPLINA_3);
            entity.setDataInicio("03/03/2018");
            entity.setDataTermino("03/03/2019");
            entity.setDescricao("DISCIPLINA 3");
            entity.setSegmento("Backend");
            entity.setUrlLogo("http://bucket/usuario/1/perfil.png");
            data.put(DISCIPLINA_3, entity);
        }
    }

    @Override
    public UUID criar(DisciplinaEntity disciplina) {
        UUID uuid = UUID.randomUUID();
        disciplina.setId(uuid);
        data.put(uuid, disciplina);
        return uuid;
    }

    @Override
    public void deletar(UUID uuid) {
        data.remove(uuid);
    }

    @Override
    public List<DisciplinaEntity> listar() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<DisciplinaEntity> findByID(UUID uuid) {
        return Optional.ofNullable(data.getOrDefault(uuid, null));
    }

    @Override
    public void atualizar(DisciplinaEntity update) {
        data.put(update.getId(), update);
    }

}
