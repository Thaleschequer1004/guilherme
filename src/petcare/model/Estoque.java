package petcare.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Estoque {
    private final List<ItemEstoque> itens;
    private final Notificador notificador;

    public Estoque(Notificador notificador) {
        this.itens = new ArrayList<>();
        this.notificador = notificador;
    }

    public void adicionarItem(ItemEstoque item) {
        if (item == null)
            throw new IllegalArgumentException("Item obrigatorio");
        itens.add(item);
    }

    public void registrarEntrada(int id, int qtd, String responsavel) {
        ItemEstoque item = buscarPorId(id);
        item.darEntrada(qtd, responsavel);
    }

    public boolean registrarSaida(int id, int qtd, String responsavel) {
        ItemEstoque item = buscarPorId(id);
        boolean ok = item.darSaida(qtd, responsavel);
        if (ok)
            verificarAlerta(item);
        return ok;
    }

    public boolean reservarParaProced(Agendamento agendamento) {
        return agendamento != null && agendamento.reservarRecursos();
    }

    public void verificarAlertas() {
        for (ItemEstoque item : itens)
            verificarAlerta(item);
    }

    private void verificarAlerta(ItemEstoque item) {
        if (item.isAbaixoMinimo() && notificador != null)
            notificador.enviarAlertaEstoque(item);
    }

    public ItemEstoque buscarItem(String nome) {
        return itens.stream().filter(i -> i.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    public List<ItemEstoque> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public List<ItemEstoque> rastrearControlados() {
        return itens.stream().filter(ItemEstoque::isControlado).collect(Collectors.toUnmodifiableList());
    }

    public void alertarVencimentos() {
        itens.stream().filter(ItemEstoque::isVencido)
                .forEach(item -> notificador.enviarAlerta("estoque", "Item vencido: " + item.getNome()));
    }

    public void gerarRelatorioRastreab() {
        rastrearControlados().forEach(ItemEstoque::exibir);
    }

    public void exibir() {
        itens.forEach(ItemEstoque::exibir);
    }

    private ItemEstoque buscarPorId(int id) {
        return itens.stream().filter(i -> i.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item nao encontrado"));
    }
}
