package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import view.inc.ProcessoRowMapper;
import view.inc.model.Computador;
import view.inc.model.Janela;
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

    public void insertIlicito(ProcessoIlicito processo, Integer codMaquina){
        this.connection.update("CALL spInsertProcessoIlicito(?, ?);",
                processo.getNomeProcesso(),
                codMaquina
        );
    }
    public void insertPrograma(String nomePrograma, Integer idComputador){
        this.connection.update("CALL spInsertProcesso(?, ?)",
                nomePrograma, idComputador
        );
    }

    public void insertRegistro(String nome, Integer fkComputador, Double registroCPU, Double registroRAM){
        this.connection.update("CALL spInsertRegistroProcesso(?, ?, ?, ?)",
                nome, fkComputador, registroCPU, registroRAM
        );
    }


    public void insertRegistroIlicito(ProcessoIlicito processo, Integer cod){
        this.connection.update("CALL spInsertRegistroIlicito(?, ?);",
                processo.getNomeProcesso(),
                cod
                        );
    }

    public List<ProcessoIlicito> selectAllProcessosIlicitos(Integer computador){
        return this.connection.query("SELECT * FROM software AS s" +
                        " JOIN softwarePermitido AS sp ON s.idSoftware = sp.fkSoftware WHERE fkComputador = ?;",
                new ProcessoRowMapper(), computador);
    }

    public List<Processo> getProcessosLista(Integer computador){
        return this.connection.query("SELECT * FROM Processo WHERE fkComputador = ?;",
                new BeanPropertyRowMapper<>(Processo.class), computador
        );
    }
}
