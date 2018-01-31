package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;

public interface FrequenciaRepository {

    void adicionaFrequencia(FrequenciaEntity entity);

    FrequenciaEntity removeFrequencia(FrequenciaEntity entity);

}
