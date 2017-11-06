package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.entity.DisciplinaEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarDisciplinaRequest;
import matera.systems.cursoferias2018.api.domain.response.DisciplinaResponse;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    /**
     * Criar nova disciplina
     *
     * @param request
     * @return UUID da disciplina criada
     */
    public UUID criar(CriarDisciplinaRequest request) {

        DisciplinaEntity entity = new DisciplinaEntity();

        entity.setId(UUID.randomUUID());
        entity.setDescricao(request.getDescricao());
        entity.setUsuarios(request.getUsuarios());
        entity.setDataInicio(request.getDataInicio());
        entity.setDataTermino(request.getDataTermino());
        entity.setSegmento(request.getSegmento());
        entity.setUrlLogo(request.getUrlLogo());

        return repository.criar(entity);
    }

    /**
     * Deletar disciplina
     *
     * @param disciplinaID UUID da disciplina a ser deletado
     */
    public void deletar(UUID disciplinaID) {

        repository.deletar(disciplinaID);
    }

    /**
     * Atualizar informações da Disciplina
     *
     * @param disciplinaID UUID da disciplina a ser atualizado
     * @param request
     */
    public void atualizar(UUID disciplinaID, AtualizarDisciplinaRequest request) {

        Optional<DisciplinaEntity> original = repository.findByID(disciplinaID);
        if (original.isPresent()) {

            final DisciplinaEntity update = original.get();

            if (request.getDescricao() != null) {
                update.setDescricao(request.getDescricao());
            }

            if (request.getUsuarios() != null) {
                update.setUsuarios(request.getUsuarios());
            }

            if (request.getDataInicio() != null) {
                update.setDataInicio(request.getDataInicio());
            }

            if (request.getDataTermino() != null) {
                update.setDataTermino(request.getDataTermino());
            }

            if (request.getSegmento() != null) {
                update.setSegmento(request.getSegmento());
            }

            if (request.getUrlLogo() != null) {
                update.setUrlLogo(request.getUrlLogo());
            }

            repository.atualizar(update);

        } else {
            throw new RuntimeException("Disciplina não Encontrada");
        }
    }

    /**
     * Listar todas disciplinas
     *
     * @return
     */
    public List<DisciplinaResponse> getDisciplinas() {

        return repository.listar().parallelStream().map(toResponse).collect(Collectors.toList());
    }

    /**
     * Buscar Disciplina por ID
     *
     * @param id
     * @return
     */
    public Optional<DisciplinaResponse> findByID(UUID id) {

        return repository.findByID(id).map(toResponse);
    }

    public List<UsuarioResponse> findUsuariosByDisciplinaID(UUID id){
        return repository.findUsuariosByDisciplinaID(id);
    }

    private Function<DisciplinaEntity, DisciplinaResponse> toResponse = (entity) -> {
        DisciplinaResponse response = new DisciplinaResponse();
        response.setId(entity.getId());
        response.setDescricao(entity.getDescricao());
        response.setUsuarios(entity.getUsuarios());
        response.setDataInicio(entity.getDataInicio());
        response.setDataTermino(entity.getDataTermino());
        response.setUrlLogo(entity.getUrlLogo());
        return response;
    };
}
