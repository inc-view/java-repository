package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import view.inc.model.Computador;
import view.inc.model.Funcionario;

import java.util.List;

public class ComputadorDao {

    private JdbcTemplate connection;

    public ComputadorDao() {
        Connection conn = new Connection();
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
                "insert into computador(nomePatrimonio, marca, fkFuncionario, sistemaOperacional, ipComputador)" +
                    "values (?, ?, ?, ?, ?)",
                computador.getNomePatrimonio(), computador.getMarca(), computador.getFkFuncionario(),
                computador.getSistemaOperacional(), computador.getIpComputador()
        );
    }


}
