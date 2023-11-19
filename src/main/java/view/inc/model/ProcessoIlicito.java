package view.inc.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import view.inc.dao.ProcessoDao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProcessoIlicito{
    private int idProcessoIlicito;
    private int fkProcesso;
    private String nomeProcesso;
    private LocalDateTime dataHora;


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

    public List<ProcessoIlicito> getProcessosIlicitos(Computador computador) {
        List<ProcessoIlicito> processosIlicitos = processoDao.selectAllProcessosIlicitos(computador.getIdComputador());
        for (ProcessoIlicito procEncontrado : processosIlicitos) {
            this.processosIlicitos.add(procEncontrado);
            procEncontrado.cadastrarProcesso(procEncontrado, computador.getIdComputador());
        }
        return this.processosIlicitos;
    }

    public void checkProcessosIlicitos(Computador computador) throws IOException {
        Looca looca = new Looca();
        List<com.github.britooo.looca.api.group.processos.Processo> processosMaquina = looca.getGrupoDeProcessos().getProcessos();
        List<ProcessoIlicito> processoIlicitos = processoDao.selectAllProcessosIlicitos(computador.getIdComputador());
        ProcessoComputador matarProc = new ProcessoComputador();
        for(com.github.britooo.looca.api.group.processos.Processo processoLooca : processosMaquina){
            for (int i = 0; i < processoIlicitos.size(); i++) {
                String nomeProcesso = processoLooca.getNome().toLowerCase();
                String nomeIlicito = processoIlicitos.get(i).getNomeProcesso().toLowerCase();
                if(nomeProcesso.contains(nomeIlicito)){
                    int pid = processoLooca.getPid().intValue();
                    //adicionar aqui o registro de processo ilicito com data e hora da ocorrencia

                    //aqui pede pra matar aquele processo
                    matarProc.killProcess(pid);
                }
            }
        }
        System.out.println("Matei todos os processos ilícitos");
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
