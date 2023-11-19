package view.inc.model;

import com.github.britooo.looca.api.core.Looca;
import view.inc.dao.ProcessoDao;

import java.io.IOException;
import java.sql.Date;
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


    public List<ProcessoIlicito> getProcessosIlicitos(Computador computador) {
        List<ProcessoIlicito> processosIlicitos = processoDao.selectAllProcessosIlicitos(computador.getIdComputador());
        for (ProcessoIlicito procEncontrado : processosIlicitos) {
            this.processosIlicitos.add(procEncontrado);
        }
        return this.processosIlicitos;
    }

    public void checkProcessosIlicitos(Computador computador) throws IOException {
        Janela janela = new Janela();
        List<com.github.britooo.looca.api.group.janelas.Janela> janelas = janela.getAllJanelas();
        List<ProcessoIlicito> processoIlicitos = processoDao.selectAllProcessosIlicitos(computador.getIdComputador());
        ProcessoComputador matarProc = new ProcessoComputador();
        for (com.github.britooo.looca.api.group.janelas.Janela janela1 : janelas) {
            for (int i = 0; i < processoIlicitos.size(); i++) {
                String nomeProcesso = janela1.getTitulo().toLowerCase();
                String nomeIlicito = processoIlicitos.get(i).getNomeProcesso().toLowerCase();
                if(nomeProcesso.contains(nomeIlicito)){
                    int pid = janela1.getPid().intValue();
                    //adicionar aqui o registro de processo ilicito com data e hora da ocorrencia
                    processoDao.insertRegistroIlicito(processoIlicitos.get(i), computador.getIdComputador());
                    //aqui pede pra matar aquele processo
                    matarProc.killProcess(pid);
                }
            }
        }
        System.out.println("Matei todos os processos ilícitos");
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
