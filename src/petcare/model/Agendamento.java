package petcare.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agendamento {
    private static final List<Agendamento> AGENDA = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private int id;
    private String dataHora;
    private String tipo;
    private String status;
    private Animal animal;
    private Veterinario veterinario;
    private String sala;
    private Notificador notificador;
    private final List<String> historico;

    public Agendamento(int id, String dataHora, String tipo, Animal animal, Veterinario veterinario,
            Notificador notificador) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipo = tipo.toUpperCase();
        this.status = "AGENDADO";
        this.animal = animal;
        this.veterinario = veterinario;
        this.notificador = notificador;
        this.sala = "Sala 1";
        this.historico = new ArrayList<>();
    }

    public boolean agendar() {
        if (!validarHorario() || !validarVeterinario() || !reservarRecursos())
            return false;
        synchronized (AGENDA) {
            if (AGENDA.stream().anyMatch(this::conflita))
                return false;
            AGENDA.add(this);
        }
        historico.add("Agendado em " + dataHora);
        notificarTutor();
        return true;
    }

    public void cancelar(String motivo) {
        if (!"AGENDADO".equals(status))
            throw new IllegalStateException("Agendamento nao esta ativo");
        status = "CANCELADO";
        historico.add("Cancelado: " + motivo);
        notificador.enviarCancelamento(this);
    }

    public void reagendar(String novaData) {
        String anterior = dataHora;
        dataHora = novaData;
        if (!validarHorario()) {
            dataHora = anterior;
            throw new IllegalArgumentException("Horario fora do expediente");
        }
        historico.add("Reagendado para " + novaData);
        notificador.enviarReagendamento(this);
    }

    public boolean validarHorario() {
        LocalDateTime data = LocalDateTime.parse(dataHora, FORMATTER);
        LocalTime hora = data.toLocalTime();
        return !hora.isBefore(LocalTime.of(8, 0)) && !hora.isAfter(LocalTime.of(18, 0));
    }

    public boolean validarVeterinario() {
        return veterinario != null && veterinario.isDisponivel();
    }

    public boolean reservarRecursos() {
        return animal != null && veterinario != null && sala != null
                && (tipo.equals("CONSULTA") || tipo.equals("EXAME") || tipo.equals("CIRURGIA"));
    }

    public String getStatus() {
        return status;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public List<String> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void notificarTutor() {
        notificador.enviarConfirmacao(this);
    }

    public void exibir() {
        System.out.println("[AGENDAMENTO] Tipo: " + tipo + " | Status: " + status);
    }

    private boolean conflita(Agendamento outro) {
        return outro != this && outro.status.equals("AGENDADO") && outro.veterinario == veterinario
                && outro.dataHora.equals(dataHora);
    }
}
