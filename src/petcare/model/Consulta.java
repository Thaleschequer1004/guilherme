package petcare.model;

public class Consulta extends RegistroClinico {
    private String motivo;
    private String prescricao;
    private String dataRetorno;

    public Consulta(int id, String data, String descricao, Veterinario veterinario, String motivo) {
        super(id, data, descricao, veterinario);
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setPrescricao(String rx) {
        ensureEditable();
        this.prescricao = rx;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setDataRetorno(String dt) {
        ensureEditable();
        this.dataRetorno = dt;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    @Override
    public void exibir() {
        System.out.println("[CONSULTA] " + getDescricao() + " | Motivo: " + motivo);
    }
}
