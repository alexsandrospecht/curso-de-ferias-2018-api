package matera.systems.cursoferias2018.api.domain.entity;

import java.util.Objects;

public class FrequenciaEntity {

    private final UsuarioEntity usuario;

    private final DisciplinaEntity disciplina;

    private final String data;

    public FrequenciaEntity(UsuarioEntity usuario, DisciplinaEntity disciplina, String data) {
        this.usuario = usuario;
        this.disciplina = disciplina;
        this.data = data;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public DisciplinaEntity getDisciplina() {
        return disciplina;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequenciaEntity that = (FrequenciaEntity) o;
        return Objects.equals(usuario, that.usuario) &&
                Objects.equals(disciplina, that.disciplina) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, disciplina, data);
    }
}
