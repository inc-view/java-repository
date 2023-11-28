package view.inc.dao;

import view.inc.ProcessoRowMapper;
import view.inc.model.Processo;
import view.inc.model.ProcessoIlicito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessoSQLDao {
    private Connection con;
    public ProcessoSQLDao() throws SQLException {
        ConnectionSQL conn = new ConnectionSQL();
        this.con = conn.getCon();
    }

    public void insertPrograma(String nomePrograma, Integer idComputador) throws SQLException {
        Statement st = this.con.createStatement();
        String sql = "EXEC spInsertProcesso'" + nomePrograma + "'," +  idComputador + ";";
        int count = st.executeUpdate(sql);
    }

    public void insertRegistro(String nome, Integer fkComputador, Double registroCPU, Double registroRAM) throws SQLException {
        Statement st = this.con.createStatement();
        String tratar1;
        if(registroCPU != null) {
            tratar1 = registroCPU.toString().replace(",", ".");
        }else{
            tratar1 = "0.0";
        }
        String tratar2;
        if(registroRAM != null) {
            tratar2 = registroRAM.toString().replace(",", ".");
        }else{
            tratar2 = "0.0";
        }

        String sql = String.format("""
                        EXEC spInsertNovoRegistro @vNomeProcesso = '%s',
                                                                    		@vfkComputador = %d,
                                                                    		@dadoCPU = %s,
                                                                    		@RAM = %s;""",
                nome , fkComputador , tratar1, tratar2 );
        st.executeUpdate(sql);
    }
    public void insertRegistroIlicito(String nome, Integer cod) throws SQLException {
        Statement st = this.con.createStatement();
        String sql = "EXEC spInsertRegistroIlicito'" + nome + "'," + cod + ";";
        st.executeUpdate(sql);
    }

    public List<ProcessoIlicito> selectAllProcessosIlicitos(Integer computador) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<ProcessoIlicito> processos = new ArrayList<>();
        Statement st = con.createStatement();
        String sql = "SELECT * FROM software AS s" +
                " JOIN softwarePermitido AS sp ON s.idSoftware = sp.fkSoftware WHERE fkComputador = " + computador +";";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            ProcessoIlicito processo = new ProcessoIlicito();
            processo.setNomeProcesso(rs.getString("nomeSoftware"));
            processo.setFkProcesso(rs.getInt("fkSoftware"));
            processo.setIdProcessoIlicito(rs.getInt("idSoftwarePermitido"));
            processos.add(processo);
        }
        return processos;
    }
}
