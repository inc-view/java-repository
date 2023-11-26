package view.inc.model;

import com.github.britooo.looca.api.core.Looca;
import view.inc.dao.ComputadorDao;
import view.inc.dao.ComputadorSQLDao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Computador {

    private Integer idComputador;
    private String nomePatrimonio;
    private String marca;
    private String sistemaOperacional;
    private Integer fkFuncionario;

    private String ipComputador;

    private ComputadorDao computadorDao = new ComputadorDao();
    private ComputadorSQLDao computadorDaoSql = new ComputadorSQLDao();

    private List<Processo> processos = new ArrayList<>();

    public Computador() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {};

    public Computador(String nomePatrimonio, String marca, String sistemaOperacional, Integer fkFuncionario, String ipComputador) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.nomePatrimonio = nomePatrimonio;
        this.marca = marca;
        this.sistemaOperacional = sistemaOperacional;
        this.fkFuncionario = fkFuncionario;
        this.ipComputador = ipComputador;
    }


    public Computador recognizeMachine(Integer fkFuncionario, String ipComputador){

        if(fkFuncionario != null && fkFuncionario > 0 && !ipComputador.isEmpty()){
            List<Computador> dadosComputador = computadorDao.selectInfoComputador(fkFuncionario, ipComputador);
            if(!dadosComputador.isEmpty()){
                return dadosComputador.get(0);
            }

        }
        return null;

    }
    public Computador recognizeMachineSQL(Integer fkFuncionario, String ipComputador) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(fkFuncionario != null && fkFuncionario > 0 && !ipComputador.isEmpty()){
            List<Computador> dadosComputador = computadorDaoSql.selectInfoComputador(fkFuncionario, ipComputador);
            if(!dadosComputador.isEmpty()){
                return dadosComputador.get(0);
            }

        }
        return null;
    }

    public Integer recognizeIdMachine(Integer fkFuncionario, String ipComputador){

        if(fkFuncionario != null && fkFuncionario > 0 && !ipComputador.isEmpty()){
            List<Computador> dadosComputador = computadorDao.selectInfoComputador(fkFuncionario, ipComputador);
            if(!dadosComputador.isEmpty()){
                return dadosComputador.get(0).getIdComputador();
            }

        }
        return null;

    }

    public String getIpMaquina(){
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return ip;
    }

    public String getNomeMaquina(){
        String nomeMaquina = null;
        try {
            nomeMaquina = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return nomeMaquina;
    }

    public void registerMachine(Integer fkFuncionario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Looca sistema = new Looca();

        String so               = sistema.getSistema().getSistemaOperacional();
        String fabricante       = sistema.getSistema().getFabricante();
        String nomeComputador   = getNomeMaquina();
        String ipComputador     = getIpMaquina();
        Integer idFuncionario   = fkFuncionario;

        Computador computador = new Computador(
            nomeComputador, fabricante, so, idFuncionario, ipComputador
        );
        computadorDao.insert(computador);
    }

    public void registerMachineSQL(Integer fkFuncionario) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Looca sistema = new Looca();

        String so               = sistema.getSistema().getSistemaOperacional();
        String fabricante       = sistema.getSistema().getFabricante();
        String nomeComputador   = getNomeMaquina();
        String ipComputador     = getIpMaquina();
        Integer idFuncionario   = fkFuncionario;

        Computador computador = new Computador(
                nomeComputador, fabricante, so, idFuncionario, ipComputador
        );
        computadorDaoSql.insert(computador);
    }

    public void adicionarProcesso(Processo processo){
        this.processos.add(processo);
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public Integer getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Integer idComputador) {
        this.idComputador = idComputador;
    }

    public String getNomePatrimonio() {
        return nomePatrimonio;
    }

    public void setNomePatrimonio(String nomePatrimonio) {
        this.nomePatrimonio = nomePatrimonio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public String getIpComputador() {
        return ipComputador;
    }

    public void setIpComputador(String ipComputador) {
        this.ipComputador = ipComputador;
    }

    @Override
    public String toString() {
        return "Computador{" +
                "idComputador=" + idComputador +
                ", nomePatrimonio='" + nomePatrimonio + '\'' +
                ", marca='" + marca + '\'' +
                ", sistemaOperacional='" + sistemaOperacional + '\'' +
                ", fkFuncionario=" + fkFuncionario +
                ", ipComputador='" + ipComputador + '\'' +
                '}';
    }
}
