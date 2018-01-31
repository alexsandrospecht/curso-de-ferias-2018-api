package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.entity.FrequenciaEntity;
import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.repository.DisciplinaRepository;
import matera.systems.cursoferias2018.api.repository.FrequenciaRepository;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public void adicionaFrequencia(UUID disciplinaID, UUID usuarioID, String data) {
        FrequenciaEntity entity = montaFrequencia(usuarioID, disciplinaID, data);
        repository.adicionaFrequencia(entity);
    }

    public void removeFrequencia(UUID disciplinaID, UUID usuarioID, String data) {
        repository.removeFrequencia(montaFrequencia(usuarioID, disciplinaID, data));
    }

    private FrequenciaEntity montaFrequencia(UUID usuarioID, UUID disciplinaID, String data) {
        final Optional<UsuarioEntity> usuario = usuarioRepository.findByID(usuarioID);
        final Optional<DisciplinaEntity> disciplina = disciplinaRepository.findByID(disciplinaID);

        if (!usuario.isPresent() || !disciplina.isPresent()) {
            throw new IllegalArgumentException("Parametros inválidos");
        }

        if (Strings.isNullOrEmpty(data)) {
            data = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
        }

        return new FrequenciaEntity(usuario.get(), disciplina.get(), data);
    }

}
