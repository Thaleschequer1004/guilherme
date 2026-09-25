package petcare.model;

public class Relatorio {
    private static int sequencia;
    private int id;
    private int mes;
    private int ano;
    private int totalAtendimentos;
    private double faturamentoTotal;
    private double totalDespesas;
    private double taxaRetorno;
    private double tempMedioAtend;
    private String procedMaisRealizado;

    public Relatorio(int mes, int ano) {
        if (mes < 1 || mes > 12)
            throw new IllegalArgumentException("Mes invalido");
        this.id = ++sequencia;
        this.mes = mes;
        this.ano = ano;
        this.procedMaisRealizado = "Consulta";
    }

    public void gerar() {
        totalAtendimentos = 0;
        faturamentoTotal = 0;
        totalDespesas = 0;
        taxaRetorno = 0;
        tempMedioAtend = 0;
    }

    public String getEstatisticas() {
        return String.format("Atendimentos: %d | Faturamento: R$%.2f | Retorno: %.1f%%", totalAtendimentos,
                faturamentoTotal, taxaRetorno);
    }

    public double calcularFaturamento() {
        return faturamentoTotal;
    }

    public double calcularTaxaRetorno() {
        return taxaRetorno;
    }

    public double calcularTempMedio() {
        return tempMedioAtend;
    }

    public String getProcedMaisRealizado() {
        return procedMaisRealizado;
    }

    public void exportar() {
        System.out.println("Relatorio exportado: " + mes + "/" + ano);
    }

    public void exibir() {
        System.out.println("[RELATORIO] " + mes + "/" + ano + " | " + getEstatisticas());
    }
}
