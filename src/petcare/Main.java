package petcare;

import java.time.LocalDate;
import java.util.List;
import petcare.model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA PETCARE ===");

        Administrador administrador = new Administrador(1, "Ana Administradora", "admin@petcare.com", "admin123");
        Veterinario veterinario = new Veterinario(2, "Ana Veterinaria", "ana@petcare.com", "CRMV-123", "Clinica geral");
        Recepcionista recepcionista = new Recepcionista(3, "Bia Recepcao", "bia@petcare.com", "101");
        Tutor tutor = new Tutor(4, "Joao Silva", "joao@email.com", "12345678900", "11999999999");
        System.out.println("[USUARIO] Login: ana@petcare.com - Perfil: " + veterinario.getPerfil() + " -> "
                + veterinario.login("ana@petcare.com", "vet1234"));

        Animal rex = new Animal(10, "Rex", "Cao", "Labrador", tutor);
        rex.setPeso(28.5);
        rex.exibir();

        Consulta consulta = new Consulta(100, "2026-09-25", "Avaliacao clinica", veterinario, "Retorno");
        consulta.setPrescricao("Repouso e hidratacao");
        Vacina vacina = new Vacina(101, "2026-09-25", "Aplicacao anual", veterinario, "Antirrabica");
        vacina.setDataReforco(LocalDate.now().minusDays(1).toString());
        rex.getHistorico().adicionarConsulta(consulta);
        rex.getHistorico().adicionarVacina(vacina);
        rex.getHistorico().exibir();
        System.out.println("[VACINA] Precisa reforco: " + vacina.precisaReforco());

        Cirurgia cirurgia = new Cirurgia(102, "2026-09-26", "Procedimento odontologico", veterinario, "Sala cirurgica");
        cirurgia.setEquipe(List.of("Dra. Ana", "Enfermeiro Carlos"));
        cirurgia.adicionarMedicamento("Anestesico");
        rex.getHistorico().adicionarCirurgia(cirurgia);
        Exame exame = new Exame(103, "2026-09-25", "Hemograma", veterinario, "Laboratorial");
        exame.setResultado("Normal");
        exame.anexarImagem("/laudos/rex-hemograma.png");
        rex.getHistorico().adicionarExame(exame);
        Tratamento tratamento = new Tratamento(104, "2026-09-25", "Tratamento preventivo", veterinario,
                "Medicacao por 5 dias");
        rex.getHistorico().adicionarTratamento(tratamento);

        Notificador notificador = new Notificador("EMAIL", tutor.getEmail());
        Agendamento agendamento = new Agendamento(200, "2026-09-25 10:00", "CONSULTA", rex, veterinario, notificador);
        System.out.println("[AGENDA] Agendamento aceito: " + agendamento.agendar());
        agendamento.exibir();
        recepcionista.cancelarConsulta(999);

        Estoque estoque = new Estoque(notificador);
        ItemEstoque dipirona = new ItemEstoque(300, "Dipirona", 50, 10, "LOT-2026");
        estoque.adicionarItem(dipirona);
        estoque.registrarSaida(300, 45, "Bia Recepcao");
        estoque.exibir();
        estoque.registrarEntrada(300, 20, administrador.getNome());
        dipirona.setValidade(LocalDate.now().plusMonths(6).toString());
        dipirona.setResponsavelRetirada(administrador.getNome());
        dipirona.setControlado(true);

        Fatura fatura = new Fatura(400, tutor, 150.0, "Consulta clinica", notificador);
        fatura.emitir();
        fatura.exibir();
        System.out.println("[PAGAMENTO] Confirmado pela API: " + fatura.confirmarPagOnline());

        LogAuditoria log = new LogAuditoria(administrador, "AJUSTE_ESTOQUE", "ItemEstoque");
        log.registrar();
        log.exibir();
        Relatorio relatorio = administrador.gerarRelatorio(9, 2026);
        relatorio.exibir();

        rex.getHistorico().finalizar();
        try {
            rex.getHistorico().adicionarConsulta(new Consulta(105, "2026-09-27", "Tentativa", veterinario, "Teste"));
        } catch (IllegalStateException erro) {
            System.out.println("[REGRA] Historico finalizado bloqueou nova insercao.");
        }
    }
}
