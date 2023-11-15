package view.inc.model;

import com.github.britooo.looca.api.core.Looca;
import view.inc.dao.ComputadorDao;
import view.inc.dao.ProcessoDao;

import java.util.ArrayList;
import java.util.List;

public class Processo{
    private Integer idProcesso;
    private String nomeProcesso;
    private Computador computador;
    private Double usoCPU;


    private ProcessoDao processoDao = new ProcessoDao();
    private List<Processo> processos;

   private  Looca looca = new Looca();

    public Processo(){}
    public Processo(Integer idProcesso, String nomeProcesso,Integer fkFuncionario, String ipComputador, Double usoCPU) {
        this.idProcesso = idProcesso;
        this.nomeProcesso = nomeProcesso;
        this.computador = new Computador().recognizeMachine(fkFuncionario, ipComputador);
        this.usoCPU = usoCPU;
        this.processos = new ArrayList<>();
    }
    public Processo(Integer idProcesso, String nomeProcesso, Integer fkFuncionario, String ipComputador) {
        this.idProcesso = idProcesso;
        this.nomeProcesso = nomeProcesso;
        this.computador = new Computador().recognizeMachine(fkFuncionario, ipComputador);
    }

    public Processo(Integer idProcesso, String nomeProcesso) {
        this.idProcesso = idProcesso;
        this.nomeProcesso = nomeProcesso;
    }

    public ProcessoDao getProcessoDao() {
        return processoDao;
    }

    //pega todos os processos naquele momento
    public void getProcessos(){
        List<com.github.britooo.looca.api.group.processos.Processo> processos = looca.getGrupoDeProcessos().getProcessos();
        for (com.github.britooo.looca.api.group.processos.Processo processo : processos) {
            Processo p1 = new Processo(processo.getPid(), processo.getNome());
            System.out.println(p1);
            this.processos.add(p1);
        }
    }

    public void cadastrarProcesso(Integer fkFuncionario, String ipComputador){

        List<com.github.britooo.looca.api.group.processos.Processo> processos = looca.getGrupoDeProcessos().getProcessos();
        for (com.github.britooo.looca.api.group.processos.Processo processo : processos) {
            Integer PID = processo.getPid();
            String nomeProcesso = processo.getNome();
            Processo p = new Processo(PID, nomeProcesso,fkFuncionario, ipComputador);
                    processoDao.insert(p);
        }

    }


    public Integer getIdMaquina(Integer fkFuncionario, String ipComputador){
        Computador computador1 = new Computador();
        Integer idMaquina = computador1.recognizeIdMachine(fkFuncionario, ipComputador);
        return idMaquina;
    }
    public List<Processo> getAllProcessos(){
        return this.processos;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public Integer getIdComputador() {
        return computador.getIdComputador();
    }

    public Computador getComputador() {
        return computador;
    }

    public Double getUsoCPU() {
        return usoCPU;
    }

    @Override
    public String toString() {
        return "Processo{" +
                "idProcesso=" + idProcesso +
                ", nomeProcesso='" + nomeProcesso + '\'' +
                '}';
    }
}
