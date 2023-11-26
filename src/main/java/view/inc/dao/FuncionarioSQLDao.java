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
        Statement st = con.createStatement();
        String sql = String.format("SELECT *\n" +
                "FROM funcionario\n" +
                "WHERE email = '%s' AND senha = '%s';\n", email, senha);
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setFkEmpresa(rs.getInt("fkEmpresa"));
            funcionario.setTelefone(rs.getString("telefone"));
        }
        return funcionario;
    }
}
