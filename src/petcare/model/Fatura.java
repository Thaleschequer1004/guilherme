package petcare.model;

import java.time.LocalDate;

public class Fatura {
    private int id;
    private Tutor tutor;
    private double valor;
    private String dataEmissao;
    private String dataVencimento;
    private String status;
    private String descricaoServico;
    private Notificador notificador;

    public Fatura(int id, Tutor tutor, double valor, String descricaoServico, Notificador notificador) {
        if (valor < 0)
            throw new IllegalArgumentException("Valor invalido");
        this.id = id;
        this.tutor = tutor;
        this.valor = valor;
        this.descricaoServico = descricaoServico;
        this.notificador = notificador;
        this.status = "PENDENTE";
        this.dataEmissao = LocalDate.now().toString();
        this.dataVencimento = LocalDate.now().plusDays(7).toString();
        if (tutor != null)
            tutor.adicionarFatura(this);
    }

    public void emitir() {
        if (notificador != null)
            notificador.enviarFatura(this);
    }

    public String gerarBoleto() {
        return "BOLETO-SIP-" + id;
    }

    public String gerarLinkPagamento() {
        return "https://pagamento.petcare/fatura/" + id;
    }

    public void registrarPagamento() {
        status = "PAGO";
    }

    public boolean confirmarPagOnline() {
        status = "PAGO";
        return true;
    }

    public boolean isPendente() {
        return "PENDENTE".equals(status);
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void exibir() {
        System.out.println(String.format("[FATURA] Valor: R$%.2f | Status: %s", valor, status));
    }
}
