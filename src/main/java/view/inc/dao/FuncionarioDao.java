package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import view.inc.model.Funcionario;

import java.util.List;

public class FuncionarioDao {

    private JdbcTemplate connection;

    public FuncionarioDao() {
        Connection conn = new Connection();
        this.connection = conn.getConnection();
    }

    public List<Funcionario> selectLogin(String email, String senha){

        return this.connection.query("select * from funcionario where email = ? and senha = ?",
                new BeanPropertyRowMapper<>(Funcionario.class),email, senha
        );

    }

}
