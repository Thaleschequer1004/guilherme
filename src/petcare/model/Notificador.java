package petcare.model;

import java.util.List;

public class Notificador {
    private String canal;
    private String destinatario;
    private boolean ativo;

    public Notificador(String canal, String destinatario) {
        setCanal(canal);
        this.destinatario = requireText(destinatario, "destinatario");
        this.ativo = true;
    }

    public String getCanal() { return canal; }

    public void setCanal(String canal) {
        String valor = requireText(canal, "canal").toUpperCase();
        if (!valor.equals("EMAIL") && !valor.equals("SMS") && !valor.equals("APP")) {
            throw new IllegalArgumentException("Canal deve ser EMAIL, SMS ou APP");
        }
        this.canal = valor;
    }

    public void enviarConfirmacao(Agendamento agendamento) { enviarAlerta(destinatario, "Agendamento confirmado: " + agendamento.getTipo()); }
    public void enviarCancelamento(Agendamento agendamento) { enviarAlerta(destinatario, "Agendamento cancelado: " + agendamento.getTipo()); }
    public void enviarReagendamento(Agendamento agendamento) { enviarAlerta(destinatario, "Agendamento reagendado para " + agendamento.getDataHora()); }
    public void enviarLembreteVacina(Animal animal, String data) { enviarAlerta(destinatario, "Vacina de " + animal.getNome() + " em " + data); }
    public void enviarAlertaEstoque(ItemEstoque item) { enviarAlerta(destinatario, "Estoque baixo: " + item.getNome()); }
    public void enviarFatura(Fatura fatura) { enviarAlerta(destinatario, "Fatura emitida no valor de R$" + String.format("%.2f", fatura.getValor())); }
    public void enviarAlerta(String destinatario, String mensagem) { if (ativo) System.out.println("[NOTIFICADOR] " + canal + " -> " + destinatario + ": " + mensagem); }
    public void enviarCampanha(List<String> lista, String mensagem) { for (String contato : lista) enviarAlerta(contato, mensagem); }

    private static String requireText(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " obrigatorio");
        return valor;
    }
}
