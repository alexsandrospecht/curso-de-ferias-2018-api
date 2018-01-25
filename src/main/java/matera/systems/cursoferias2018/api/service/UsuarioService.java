package matera.systems.cursoferias2018.api.service;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> getUsuarios() {
        return usuarioRepository.list().stream().map(u -> new UsuarioResponse(u)).collect(Collectors.toList());
    }

    public UUID create(CriarUsuarioRequest request) {
        final UUID id = UUID.randomUUID();

        final UsuarioEntity entity = new UsuarioEntity();
        entity.setEmail(request.getEmail());
        entity.setLogin(request.getLogin());
        entity.setNome(request.getNome());
        entity.setPerfil(request.getPerfil());
        entity.setUrlPhoto(request.getUrlPhoto());
        entity.setUuid(id);

        return usuarioRepository.create(entity);
    }

    public boolean delete(String id) {
        final UUID uuid = UUID.fromString(id);
        return usuarioRepository.delete(uuid);
    }

    public void update(String id, AtualizarUsuarioRequest request) {
        usuarioRepository.update(id, request);
    }

    public UsuarioResponse getUsuarioByID(String id) {
        return usuarioRepository.getUsuarioByID(id);
    }
}
