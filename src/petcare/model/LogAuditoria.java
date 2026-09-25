package petcare.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogAuditoria {
    private static final List<LogAuditoria> LOGS = new ArrayList<>();
    private int id;
    private String dataHora;
    private Usuario usuario;
    private String acao;
    private String entidadeAfetada;
    private int idEntidade;
    private String ipOrigem;

    public LogAuditoria(Usuario usuario, String acao, String entidade) {
        this.id = LOGS.size() + 1;
        this.usuario = usuario;
        this.acao = acao;
        this.entidadeAfetada = entidade;
        this.idEntidade = 0;
        this.ipOrigem = "127.0.0.1";
    }

    public void registrar() {
        dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LOGS.add(this);
    }

    public String getAcao() {
        return acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public static List<LogAuditoria> buscarPorUsuario(Usuario usuario) {
        return LOGS.stream().filter(l -> l.usuario == usuario).toList();
    }

    public static List<LogAuditoria> buscarPorEntidade(String entidade) {
        return LOGS.stream().filter(l -> l.entidadeAfetada.equalsIgnoreCase(entidade)).toList();
    }

    public void exibir() {
        System.out.println("[LOG] " + dataHora + " | Usuario: " + usuario.getNome() + " | Acao: " + acao);
    }
}
