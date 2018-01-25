package matera.systems.cursoferias2018.api.domain.response;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;

import java.util.UUID;

public class UsuarioResponse {

    private UUID uuid;

    private String nome;

    private String login;

    private String email;

    private String perfil;

    private String urlPhoto;

    public UsuarioResponse() {}

    public UsuarioResponse(UsuarioEntity usuario) {
        this.uuid = usuario.getUuid();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.email = usuario.getEmail();
        this.perfil = usuario.getPerfil();
        this.urlPhoto = usuario.getUrlPhoto();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
