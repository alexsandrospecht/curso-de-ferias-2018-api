package matera.systems.cursoferias2018.api.domain.request;

public class UsuarioLoginRequest {

    private String nome;
    private String senha;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
