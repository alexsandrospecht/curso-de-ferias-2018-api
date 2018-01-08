package matera.systems.cursoferias2018.api.domain.entity;

import java.text.SimpleDateFormat;
import java.util.*;

public class FrequenciaEntity {

    private UUID usuarioId;
    private UUID disciplinaId;
    private Set<String> presencas = new HashSet<>();

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(UUID disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Set<String> getPresencas() {
        return Collections.unmodifiableSet(presencas);
    }

    public void addPresenca(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        String dataFormatada = sdf.format(date);
        presencas.add(dataFormatada);
    }

    public void removePresenca(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        String dataFormatada = sdf.format(date);
        if (presencas.contains(dataFormatada)) {
            presencas.remove(dataFormatada);
        }
    }

    public void setPresencas(Set<String> presencas) {
        this.presencas = presencas;
    }

}
