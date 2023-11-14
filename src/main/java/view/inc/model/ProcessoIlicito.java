package view.inc.model;

import view.inc.dao.ProcessoDao;

import java.util.ArrayList;
import java.util.List;

public class ProcessoIlicito{
    private int idProcessoIlicito;
    private int fkProcesso;
    private String nomeProcesso;
    private List<ProcessoIlicito> processosIlicitos = new ArrayList<>();
    private ProcessoDao processoDao = new ProcessoDao();


    public ProcessoIlicito(){
    }

    public ProcessoIlicito(Integer idProcesso, int fkProcesso, String nomeProcesso) {
        this.idProcessoIlicito = idProcesso;
        this.nomeProcesso = nomeProcesso;
        this.fkProcesso = fkProcesso;
    }
    public ProcessoIlicito(int fkProcesso, String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
        this.fkProcesso = fkProcesso;
    }


    public void cadastrarProcesso(ProcessoIlicito processoIlicito, Integer computador) {
        List<ProcessoIlicito> todosProcessos = processoDao.selectAllProcessosIlicitos(computador);
            for (ProcessoIlicito todosProcesso : todosProcessos) {
                processoDao.insertIlicito(processoIlicito, computador);
                this.processosIlicitos.add(processoIlicito);
        }
        processoDao.insertIlicito(processoIlicito, computador);
    }

    public void getProcessosIlicitos(Computador computador) {
        List<ProcessoIlicito> processosIlicitos = processoDao.selectAllProcessosIlicitos(computador.getIdComputador());
        for (ProcessoIlicito procEncontrado : processosIlicitos) {
            this.processosIlicitos.add(procEncontrado);
            System.out.println(procEncontrado.toString());
            procEncontrado.cadastrarProcesso(procEncontrado, computador.getIdComputador());
        }


    }



    @Override
    public String toString() {
        return "ProcessoIlicito{" +
                "idProcessoIlicito=" + idProcessoIlicito +
                ", fkProcesso=" + fkProcesso +
                ", nomeProcesso='" + nomeProcesso + '\'' +
                '}';
    }

    public int getIdProcessoIlicito() {
        return idProcessoIlicito;
    }

    public int getFkProcesso() {
        return fkProcesso;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setIdProcessoIlicito(int idProcessoIlicito) {
        this.idProcessoIlicito = idProcessoIlicito;
    }

    public void setFkProcesso(int fkProcesso) {
        this.fkProcesso = fkProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public List<ProcessoIlicito> getProcessosIlicitos() {
        return processosIlicitos;
    }

    public void setProcessosIlicitos(List<ProcessoIlicito> processosIlicitos) {
        this.processosIlicitos = processosIlicitos;
    }

    public ProcessoDao getProcessoDao() {
        return processoDao;
    }

    public void setProcessoDao(ProcessoDao processoDao) {
        this.processoDao = processoDao;
    }
}
