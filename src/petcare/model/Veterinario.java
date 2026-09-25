package petcare.model;

import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Usuario {
    private String crmv;
    private String especialidade;
    private boolean disponivel;

    public Veterinario(int id, String nome, String email, String crmv, String especialidade) {
        super(id, nome, email, "vet1234", "VET");
        this.crmv = require(crmv, "CRMV");
        this.especialidade = require(especialidade, "especialidade");
        this.disponivel = true;
    }

    public String getCrmv() {
        return crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void registrarLaudo(int id, String texto) {
        require(texto, "laudo");
    }

    public void emitirPrescricao(int id, String receita) {
        require(receita, "prescricao");
    }

    public List<String> consultarAgenda() {
        return new ArrayList<>();
    }

    @Override
    public void exibir() {
        System.out.println("[VETERINARIO] " + getNome() + " | CRMV: " + crmv);
    }

    private static String require(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(field + " obrigatorio");
        return value;
    }
}
