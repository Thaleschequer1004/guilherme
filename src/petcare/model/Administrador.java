package petcare.model;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private int nivelAcesso;
    private String departamento;

    public Administrador(int id, String nome, String email, String senha) {
        super(id, nome, email, senha, "ADMIN");
        this.nivelAcesso = 10;
        this.departamento = "Administracao";
    }

    public boolean excluirRegistro(int id, String tipo) {
        return nivelAcesso >= 10 && id > 0 && tipo != null;
    }

    public void ajustarEstoque(ItemEstoque item, int qtd) {
        if (qtd >= 0)
            item.darEntrada(qtd, getNome());
    }

    public Relatorio gerarRelatorio(int mes, int ano) {
        Relatorio r = new Relatorio(mes, ano);
        r.gerar();
        return r;
    }

    public void gerenciarUsuario(Usuario usuario) {
        if (usuario == null)
            throw new IllegalArgumentException("Usuario obrigatorio");
    }

    public List<String> visualizarLogs() {
        return new ArrayList<>();
    }

    @Override
    public void exibir() {
        System.out.println("[ADMIN] " + getNome());
    }
}
