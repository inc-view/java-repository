package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import view.inc.ProcessoRowMapper;
import view.inc.model.Computador;
import view.inc.model.Processo;
import view.inc.model.ProcessoIlicito;

import java.util.List;

public class ProcessoDao {
    private JdbcTemplate connection;

    public ProcessoDao() {
        Connection conn = new Connection();
        this.connection = conn.getConnection();
    }

    public void insert(Processo processo){
        this.connection.update("CALL spInsertProcesso(?, ?)",
                processo.getNomeProcesso(),
                processo.getIdComputador()
        );
    }

    public void insertIlicito(ProcessoIlicito processo){
        String pesquisa = "%" + processo.getNomeProcesso() + "%";
        System.out.println(pesquisa);
        this.connection.update("INSERT INTO processoIlicito (fkProcesso) VALUES ((SELECT idProcesso FROM Processo WHERE nomeProcesso LIKE ?));",
                pesquisa
        );
    }

    public void insertRegistroIlicito(Processo processo){
        this.connection.update("CALL spInsertRegistroIlicito(?, ?);",
                processo.getNomeProcesso(),
                processo.getIdComputador()
        );
    }

    public List<ProcessoIlicito> selectAllProcessosIlicitos(Integer computador){
        return this.connection.query("SELECT * FROM processoIlicito as procIli JOIN\n" +
                        "\tprocesso AS p ON procIli.fkProcesso = p.idProcesso AND p.fkComputador = ?;",
                new ProcessoRowMapper(), computador);
    }

    public List<Processo> getProcessosLista(Integer computador){
        return this.connection.query("SELECT * FROM Processo WHERE fkComputador = ?;",
                new BeanPropertyRowMapper<>(Processo.class), computador
        );
    }
}
