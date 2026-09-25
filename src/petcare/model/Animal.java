package petcare.model;

import java.time.LocalDate;
import java.time.Period;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private String dataNascimento;
    private double peso;
    private Tutor tutor;
    private final HistoricoClinico historico;

    public Animal(int id, String nome, String especie, String raca, Tutor tutor) {
        this.id = id;
        this.nome = require(nome, "nome");
        this.especie = require(especie, "especie");
        this.raca = require(raca, "raca");
        this.tutor = tutor;
        this.dataNascimento = LocalDate.now().minusYears(3).toString();
        this.historico = new HistoricoClinico(id);
        if (tutor != null)
            tutor.adicionarAnimal(this);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public HistoricoClinico getHistorico() {
        return historico;
    }

    public void setPeso(double peso) {
        if (peso <= 0)
            throw new IllegalArgumentException("Peso deve ser maior que zero");
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public int calcularIdade() {
        return Period.between(LocalDate.parse(dataNascimento), LocalDate.now()).getYears();
    }

    public void exibir() {
        System.out.println("[ANIMAL] " + nome + " | " + raca + " | Tutor: " + (tutor == null ? "-" : tutor.getNome()));
    }

    private static String require(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(field + " obrigatorio");
        return value;
    }
}
