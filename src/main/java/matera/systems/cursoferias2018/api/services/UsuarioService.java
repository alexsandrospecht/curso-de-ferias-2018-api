package matera.systems.cursoferias2018.api.services;

import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {


    /**
     * Criar novo usuário
     *
     * @param request
     * @return UUID do usuário criado
     */
    public UUID criar(CriarUsuarioRequest request) {

        return UUID.randomUUID();
    }

    /**
     * Deletar usuario
     *
     * @param usuarioID UUID do usuário a ser deletado
     */
    public void deletar(UUID usuarioID) {
        throw new UnsupportedOperationException("Not implemented yet");
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
        List<UsuarioResponse> usuarios = new ArrayList<UsuarioResponse>();
        return usuarios;
    }

    /**
     * Buscar usuário por ID
     *
     * @param id
     * @return
     */
    public UsuarioResponse findUsuarioByID(UUID id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
