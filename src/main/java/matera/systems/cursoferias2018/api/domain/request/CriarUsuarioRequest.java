package matera.systems.cursoferias2018.api.domain.request;

public class CriarUsuarioRequest {
    private String nome;
    private String login;
    private String email;
    private String perfil;
    private String urlPhoto;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
