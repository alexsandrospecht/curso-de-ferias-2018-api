package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriaDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.DisciplinasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DisciplinasService {

    @Autowired
    private DisciplinasRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public UUID criar(CriaDisciplinaRequest request) {
        DisciplinaEntity entity = new DisciplinaEntity();
        entity.setDescricao(request.getDescricao());
        entity.setUrlLogo(request.getUrlLogo());
        entity.setSegmento(request.getSegmento());
        entity.setDataInicio(request.getDataInicio());
        entity.setDataTermino(request.getDataTermino());

        return repository.criar(entity);
    }

    public Optional<DisciplinaResponse> findDisciplinaByID(UUID id) {
        return repository.findByID(id).map(toResponse);
    }

    public List<UsuarioResponse> findAlunosByDisciplinaId(UUID id) {
        final Optional<DisciplinaEntity> opt = repository.findByID(id);

        if (opt.isPresent()) {
            return opt.get().getUsuarios().stream().map(usuarioService.toResponse).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Disciplina não existe");
        }
    }

    public List<DisciplinaResponse> findAll() {
        return repository.listar().stream().map(toResponse).collect(Collectors.toList());
    }

    public void remover(UUID id) {
        repository.deletar(id);
    }

    public void atualizar(UUID id, AtualizarDisciplinaRequest request) {
        final Optional<DisciplinaEntity> disciplina = repository.findByID(id);

        if (disciplina.isPresent()) {
            final DisciplinaEntity disciplinaEntity = disciplina.get();

            if (Objects.nonNull(request.getDescricao())) {
                disciplinaEntity.setDescricao(request.getDescricao());
            }

            repository.atualizar(disciplinaEntity);
        } else {
            throw new RuntimeException("Disciplina não encontrada");
        }
    }

    private Function<DisciplinaEntity, DisciplinaResponse> toResponse = (entity) -> {
        final DisciplinaResponse response = new DisciplinaResponse();
        response.setId(entity.getId());
        response.setDataInicio(entity.getDataInicio());
        response.setDataTermino(entity.getDataTermino());
        response.setDescricao(entity.getDescricao());
        response.setSegmento(entity.getSegmento());
        response.setUrlLogo(entity.getUrlLogo());

        return response;
    };
}
