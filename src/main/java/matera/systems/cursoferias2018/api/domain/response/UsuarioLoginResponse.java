package matera.systems.cursoferias2018.api.domain.response;

public class UsuarioLoginResponse {

    private UsuarioResponse usuario;
    private String token;

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
