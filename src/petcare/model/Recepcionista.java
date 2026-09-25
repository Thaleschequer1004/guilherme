package petcare.model;

public class Recepcionista extends Usuario {
    private String ramal;
    private String turno;

    public Recepcionista(int id, String nome, String email, String ramal) {
        super(id, nome, email, "recep123", "RECEP");
        this.ramal = ramal;
        this.turno = "08h-18h";
    }

    public void cadastrarTutor(Tutor tutor) {
        if (tutor == null)
            throw new IllegalArgumentException("Tutor obrigatorio");
    }

    public void cadastrarAnimal(Animal animal) {
        if (animal == null)
            throw new IllegalArgumentException("Animal obrigatorio");
    }

    public boolean agendarConsulta(Agendamento agendamento) {
        return agendamento != null && agendamento.agendar();
    }

    public void cancelarConsulta(int id) {
    }

    public Tutor buscarTutor(String termo) {
        return null;
    }

    public Animal buscarAnimal(String termo) {
        return null;
    }

    @Override
    public void exibir() {
        System.out.println("[RECEPCIONISTA] " + getNome() + " | Ramal: " + ramal);
    }
}
