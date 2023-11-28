package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import view.inc.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao {

    private JdbcTemplate connection;

    public FuncionarioDao() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ConnectionMySQL conn = new ConnectionMySQL();
        this.connection = conn.getConnection();
    }

    public List<Funcionario> selectLogin(String email, String senha){

        return this.connection.query("select * from funcionario where email = ? and senha = ?",
                new BeanPropertyRowMapper<>(Funcionario.class),email, senha
        );

    }

}
