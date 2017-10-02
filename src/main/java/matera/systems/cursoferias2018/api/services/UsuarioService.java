package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    /**
     * Criar novo usuário
     *
     * @param request
     * @return UUID do usuário criado
     */
    public UUID criar(CriarUsuarioRequest request) {

        UsuarioEntity entity = new UsuarioEntity();
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Listar todos usuários
     *
     * @return
     */
    public List<UsuarioResponse> getUsuarios() {

        return repository.listar().parallelStream().map(mapFunction).collect(Collectors.toList());
    }

    /**
     * Buscar usuário por ID
     *
     * @param id
     * @return
     */
    public Optional<UsuarioResponse> findUsuarioByID(UUID id) {
        return repository.findByID(id).map(mapFunction);
    }

    private Function<UsuarioEntity, UsuarioResponse> mapFunction = (entity) -> {
        UsuarioResponse response = new UsuarioResponse();

        return response;
    };

    public Optional<UsuarioResponse> findUsuarioByLogin(String usuarioLogin) {
        return repository.findByUsuario(usuarioLogin).map(mapFunction);
    }
}
