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

<<<<<<< HEAD
    public void insertIlicito(ProcessoIlicito processo, Integer codMaquina){
        this.connection.update("CALL spInsertProcessoIlicito(?, ?);",
                processo.getNomeProcesso(),
                codMaquina
=======
    public void insertPrograma(String nomePrograma, Integer idComputador){
        this.connection.update("CALL spInsertProcesso(?, ?)",
                nomePrograma, idComputador
        );
    }

    public void insertRegistro(String nome, Integer fkComputador, Double registro){
        this.connection.update("CALL spInsertRegistroProcesso(?, ?, ?)",
                nome, fkComputador, registro
        );
    }

    public void insertIlicito(Processo processo){
        this.connection.update("INSERT INTO processoIlicito (fkProcesso) VALUES ((SELECT idProcesso FROM Processo WHERE nomeProcesso LIKE '%?%'));",
                processo.getNomeProcesso()
>>>>>>> 82d2a86c58b8c12cd40571b69a50c53e6b07ad64
        );
    }

    public void insertRegistroIlicito(Processo processo, Integer cod){
        this.connection.update("CALL spInsertRegistroIlicito(?, ?);",
                processo.getNomeProcesso(),
                cod
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
