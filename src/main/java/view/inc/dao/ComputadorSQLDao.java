package view.inc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import view.inc.model.Computador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComputadorSQLDao {
    private Connection con;
    public ComputadorSQLDao() throws SQLException {
        ConnectionSQL conn = new ConnectionSQL();
        this.con = conn.getCon();
    }

    public List<Computador> selectInfoComputador(Integer fkFuncionario, String ipComputador) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Statement st = con.createStatement();
        String sqlQuery = "SELECT TOP 1 *\n" +
                "FROM computador\n" +
                "WHERE fkFuncionario = " + fkFuncionario + " AND ipComputador = '" + ipComputador + "';";
        ResultSet rs = st.executeQuery(sqlQuery);
        List<Computador> computadores = new ArrayList<>();
        while(rs.next()){
            Computador computador = new Computador();
            computador.setIdComputador(rs.getInt("idComputador"));
            computador.setIpComputador(rs.getString("ipComputador"));
            computador.setNomePatrimonio(rs.getString("nomePatrimonio"));
            computador.setMarca(rs.getString("marca"));
            computador.setFkFuncionario(fkFuncionario);
            computador.setSistemaOperacional(rs.getString("sistemaOperacional"));
            computadores.add(computador);
        }
        return computadores;
    }

    public void insert(Computador computador) throws SQLException {
        Statement st = con.createStatement();
        String sql =String.format("""
                EXEC spInsertComputador '%s', '%s', %d, '%s', '%s'""",
        computador.getNomePatrimonio(), computador.getMarca(), computador.getFkFuncionario(),
                computador.getSistemaOperacional(), computador.getIpComputador()
        );
        int count = st.executeUpdate(sql);
    }
}
