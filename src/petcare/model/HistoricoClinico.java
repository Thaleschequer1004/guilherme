package petcare.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoClinico {
    private int idAnimal;
    private final List<Consulta> consultas;
    private final List<Vacina> vacinas;
    private final List<Cirurgia> cirurgias;
    private final List<Exame> exames;
    private final List<Tratamento> tratamentos;
    private boolean finalizado;

    public HistoricoClinico(int idAnimal) {
        this.idAnimal = idAnimal;
        consultas = new ArrayList<>();
        vacinas = new ArrayList<>();
        cirurgias = new ArrayList<>();
        exames = new ArrayList<>();
        tratamentos = new ArrayList<>();
    }

    public void adicionarConsulta(Consulta c) {
        ensureEditable();
        consultas.add(c);
    }

    public void adicionarVacina(Vacina v) {
        ensureEditable();
        vacinas.add(v);
    }

    public void adicionarCirurgia(Cirurgia c) {
        ensureEditable();
        cirurgias.add(c);
    }

    public void adicionarExame(Exame e) {
        ensureEditable();
        exames.add(e);
    }

    public void adicionarTratamento(Tratamento t) {
        ensureEditable();
        tratamentos.add(t);
    }

    public void finalizar() {
        finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public List<Consulta> getConsultas() {
        return Collections.unmodifiableList(consultas);
    }

    public List<Vacina> getVacinas() {
        return Collections.unmodifiableList(vacinas);
    }

    public List<Cirurgia> getCirurgias() {
        return Collections.unmodifiableList(cirurgias);
    }

    public List<Exame> getExames() {
        return Collections.unmodifiableList(exames);
    }

    public void exibir() {
        System.out.println("[HISTORICO] Consultas: " + consultas.size() + " | Vacinas: " + vacinas.size()
                + " | Cirurgias: " + cirurgias.size());
    }

    private void ensureEditable() {
        if (finalizado)
            throw new IllegalStateException("Historico clinico finalizado e bloqueado");
    }
}
