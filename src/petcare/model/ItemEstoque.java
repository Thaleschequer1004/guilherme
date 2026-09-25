package petcare.model;

import java.time.LocalDate;

public class ItemEstoque {
    private int id;
    private String nome;
    private int quantidade;
    private int quantidadeMinima;
    private String lote;
    private String validade;
    private boolean controlado;
    private String responsavelRetirada;
    private String categoria;

    public ItemEstoque(int id, String nome, int quantidade, int quantidadeMinima, String lote) {
        if (quantidade < 0 || quantidadeMinima < 0)
            throw new IllegalArgumentException("Quantidades invalidas");
        this.id = id;
        this.nome = require(nome, "nome");
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.lote = lote;
        this.categoria = "Material";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public boolean isControlado() {
        return controlado;
    }

    public void setControlado(boolean controlado) {
        this.controlado = controlado;
        validateControlled();
    }

    public void setValidade(String validade) {
        this.validade = validade;
        validateControlled();
    }

    public void setResponsavelRetirada(String responsavel) {
        this.responsavelRetirada = responsavel;
        validateControlled();
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void darEntrada(int qtd, String responsavel) {
        if (qtd <= 0)
            throw new IllegalArgumentException("Entrada deve ser positiva");
        quantidade += qtd;
        if (responsavel != null)
            responsavelRetirada = responsavel;
    }

    public boolean darSaida(int qtd, String responsavel) {
        if (qtd <= 0 || qtd > quantidade)
            return false;
        if (controlado && (lote == null || validade == null || responsavel == null || responsavel.isBlank()))
            return false;
        quantidade -= qtd;
        responsavelRetirada = responsavel;
        return true;
    }

    public boolean reservar(int qtd) {
        return darSaida(qtd, "RESERVA_PROCEDIMENTO");
    }

    public boolean isAbaixoMinimo() {
        return quantidade < quantidadeMinima;
    }

    public boolean isVencido() {
        return validade != null && LocalDate.parse(validade).isBefore(LocalDate.now());
    }

    public String getLote() {
        return lote;
    }

    public String getValidade() {
        return validade;
    }

    public String getResponsavelRetirada() {
        return responsavelRetirada;
    }

    public void exibir() {
        System.out.println("[ESTOQUE] " + nome + " | Qtd: " + quantidade + " | Minimo: " + quantidadeMinima + " -> "
                + (isAbaixoMinimo() ? "ALERTA" : "OK"));
    }

    private void validateControlled() {
        if (controlado && (lote == null || validade == null || responsavelRetirada == null))
            throw new IllegalArgumentException("Medicamento controlado exige lote, validade e responsavel");
    }

    private static String require(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(field + " obrigatorio");
        return value;
    }
}
