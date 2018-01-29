package matera.systems.cursoferias2018.api.domain.response;

import java.util.List;

public class RelatorioResponse{

    public List<RelatorioResponseEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RelatorioResponseEntry> entries) {
        this.entries = entries;
    }

    private List<RelatorioResponseEntry> entries;

}
