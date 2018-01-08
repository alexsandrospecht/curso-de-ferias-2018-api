package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.repository.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository repository;

    public void incluirPresenca(UUID disicplina, UUID aluno, Date data) {

        repository.incluir(aluno, disicplina, data);
    }

    public void removerPresenca(UUID disicplina, UUID aluno, Date data) {

        repository.remover(aluno, disicplina, data);
    }

}
