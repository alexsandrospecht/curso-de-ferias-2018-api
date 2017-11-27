package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    /**
     * Criar novo usuário
     *
     * @param request
     * @return UUID do usuário criado
     */
    public UUID criar(CriarUsuarioRequest request) {


        UsuarioEntity entity = new UsuarioEntity();

        entity.setUuid(UUID.randomUUID());
        entity.setEmail(request.getEmail());
        entity.setNome(request.getNome());
        entity.setPerfil(request.getPerfil());
        entity.setLogin(request.getLogin());
        entity.setSenha(request.getSenha());
        entity.setUrlPhoto(request.getUrlPhoto());

        return repository.criar(entity);
    }

    /**
     * Deletar usuario
     *
     * @param usuarioID UUID do usuário a ser deletado
     */
    public void deletar(UUID usuarioID) {

        repository.deletar(usuarioID);
    }

    /**
     * Atualizar informações do usuário
     *
     * @param usuarioID UUID do usuário a ser atualizado
     * @param request
     */
    public void atualizar(UUID usuarioID, AtualizarUsuarioRequest request) {

        Optional<UsuarioEntity> original = repository.findByID(usuarioID);
        if (original.isPresent()) {

            final UsuarioEntity update = original.get();

            if (request.getSenha() != null) {
                update.setSenha(request.getSenha());
            }

            if (request.getLogin() != null) {
                update.setLogin(request.getLogin());
            }

            if (request.getNome() != null) {
                update.setNome(request.getNome());
            }

            if (request.getEmail() != null) {
                update.setEmail(request.getEmail());
            }

            if (request.getPerfil() != null) {
                update.setPerfil(request.getPerfil());
            }

            if (request.getUrlPhoto() != null) {
                update.setUrlPhoto(request.getUrlPhoto());
            }

            repository.atualizar(update);

        } else {
            throw new RuntimeException("Usuário não Encontrado");
        }
    }

    /**
     * Listar todos usuários
     *
     * @return
     */
    public List<UsuarioResponse> getUsuarios() {

        return repository.listar().parallelStream().map(toResponse).collect(Collectors.toList());
    }

    /**
     * Buscar usuário por ID
     *
     * @param id
     * @return
     */
    public Optional<UsuarioResponse> findUsuarioByID(UUID id) {

        return repository.findByID(id).map(toResponse);
    }


    private Function<UsuarioEntity, UsuarioResponse> toResponse = (entity) -> {
        UsuarioResponse response = new UsuarioResponse();
        response.setUuid(entity.getUuid());
        response.setNome(entity.getNome());
        response.setLogin(entity.getLogin());
        response.setEmail(entity.getEmail());
        response.setPerfil(entity.getPerfil());
        response.setUrlPhoto(entity.getUrlPhoto());
        return response;
    };

}
