package petcare.model;

public class RegistroClinico {
    private int id;
    private String data;
    private String descricao;
    private Veterinario veterinario;
    private String laudoAnexo;
    private boolean finalizado;

    public RegistroClinico(int id, String data, String descricao, Veterinario veterinario) {
        this.id = id;
        this.data = require(data, "data");
        this.descricao = require(descricao, "descricao");
        this.veterinario = veterinario;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void finalizar() {
        finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void anexarLaudo(String path) {
        ensureEditable();
        laudoAnexo = require(path, "laudo");
    }

    public String getLaudo() {
        return laudoAnexo;
    }

    public void exibir() {
        System.out.println("[REGISTRO] " + id + " | " + data + " | " + descricao);
    }

    protected void ensureEditable() {
        if (finalizado)
            throw new IllegalStateException("Registro clinico finalizado e bloqueado");
    }

    private static String require(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(field + " obrigatorio");
        return value;
    }
}
