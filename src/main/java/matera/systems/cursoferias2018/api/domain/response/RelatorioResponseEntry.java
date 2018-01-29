package matera.systems.cursoferias2018.api.domain.response;

import java.util.List;

public class RelatorioResponseEntry {

    private UsuarioResponse usuario;
    private List<String> presenca;
    private Integer frequencia;

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }

    public List<String> getPresenca() {
        return presenca;
    }

    public void setPresenca(List<String> presenca) {
        this.presenca = presenca;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

}
