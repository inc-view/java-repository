package view.inc.dao;

import view.inc.model.Funcionario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioSQLDao {
    private Connection con;
    public FuncionarioSQLDao() throws SQLException {
        ConnectionSQL conn = new ConnectionSQL();
        this.con = conn.getCon();
    }
    public Funcionario selectLogin(String email, String senha) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Funcionario funcionario = new Funcionario();
        Statement st = con.createStatement();;
        String sql = "select * from funcionario where email = '" + email + "' and senha = '" + senha + "';";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setFkGestor(rs.getInt("fkGestor"));
            funcionario.setFkEmpresa(rs.getInt("fkEmpresa"));
            funcionario.setTelefone(rs.getString("telefone"));
        }
        return funcionario;
    }
}
