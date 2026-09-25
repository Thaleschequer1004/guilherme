package petcare.model;

import java.time.LocalDate;

public class Vacina extends RegistroClinico {
    private String nomeVacina;
    private String dataAplicacao;
    private String dataReforco;
    private String lote;
    private String fabricante;

    public Vacina(int id, String data, String descricao, Veterinario veterinario, String nomeVacina) {
        super(id, data, descricao, veterinario);
        this.nomeVacina = nomeVacina;
        this.dataAplicacao = data;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public String getDataReforco() {
        return dataReforco;
    }

    public void setDataReforco(String dt) {
        ensureEditable();
        this.dataReforco = dt;
    }

    public boolean precisaReforco() {
        return dataReforco != null && LocalDate.parse(dataReforco).isBefore(LocalDate.now());
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        ensureEditable();
        this.lote = lote;
    }

    public void setFabricante(String fabricante) {
        ensureEditable();
        this.fabricante = fabricante;
    }

    @Override
    public void exibir() {
        System.out.println("[VACINA] " + nomeVacina + " | Reforco: " + dataReforco);
    }
}
