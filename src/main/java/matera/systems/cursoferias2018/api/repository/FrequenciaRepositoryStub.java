package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("stub")
public class FrequenciaRepositoryStub implements FrequenciaRepository {

    private Map<Integer, FrequenciaEntity> frequencias = new HashMap<Integer, FrequenciaEntity>();

    @Override
    public void adicionaFrequencia(FrequenciaEntity entity) {
        frequencias.put(entity.hashCode(), entity);
    }

    @Override
    public FrequenciaEntity removeFrequencia(FrequenciaEntity entity) {
        return frequencias.remove(entity.hashCode());
    }
}
