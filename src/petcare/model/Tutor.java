package petcare.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tutor extends Usuario {
    private String cpf;
    private String telefone;
    private String endereco;
    private final List<Animal> animais;
    private final List<Fatura> faturas;

    public Tutor(int id, String nome, String email, String cpf, String telefone) {
        super(id, nome, email, "tutor123", "TUTOR");
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = "Nao informado";
        this.animais = new ArrayList<>();
        this.faturas = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Animal> getAnimais() {
        return Collections.unmodifiableList(animais);
    }

    public void adicionarAnimal(Animal animal) {
        if (animal == null)
            throw new IllegalArgumentException("Animal obrigatorio");
        if (!animais.contains(animal))
            animais.add(animal);
    }

    public void adicionarFatura(Fatura fatura) {
        if (fatura != null)
            faturas.add(fatura);
    }

    public void verHistoricoAnimal(int id) {
        Animal a = animais.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);
        if (a != null)
            a.getHistorico().exibir();
    }

    public List<Fatura> verFaturas() {
        return Collections.unmodifiableList(faturas);
    }

    public Animal buscarAnimal(String nome) {
        return animais.stream().filter(a -> a.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    @Override
    public void exibir() {
        System.out.println("[TUTOR] " + getNome() + " | CPF: " + cpf);
    }
}
