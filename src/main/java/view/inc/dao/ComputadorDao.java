package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import view.inc.model.Computador;
import view.inc.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class ComputadorDao {

    private JdbcTemplate connection;

    public ComputadorDao() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionMySQL conn = new ConnectionMySQL();
        this.connection = conn.getConnection();
    }

    public List<Computador> selectInfoComputador(Integer fkFuncionario, String ipComputador){

        return this.connection.query(
                "select * from computador where fkFuncionario = ? and ipComputador = ? limit 1",
                new BeanPropertyRowMapper<>(Computador.class), fkFuncionario, ipComputador
        );

    }

    public void insert(Computador computador){
        this.connection.update(
                "INSERT INTO computador (nomePatrimonio, marca, fkFuncionario, sistemaOperacional, ipComputador)\n" +
                        "SELECT * FROM (SELECT ?, ?, ?, ?, ?) AS tmp\n" +
                        "WHERE NOT EXISTS (\n" +
                        "    SELECT nomePatrimonio FROM computador WHERE nomePatrimonio = ?\n" +
                        ") LIMIT 1;",
                computador.getNomePatrimonio(), computador.getMarca(), computador.getFkFuncionario(),
                computador.getSistemaOperacional(), computador.getIpComputador(), computador.getNomePatrimonio()
        );
    }


}
