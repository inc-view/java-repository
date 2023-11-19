package view.inc.model;

import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessoComputador {

    private List<com.github.britooo.looca.api.group.processos.Processo> processos;
    private Looca looca;

    public ProcessoComputador(){
        this.processos = new ArrayList<>();
        this.looca = new Looca();
        getGroupProcessos();
    }

    public void getGroupProcessos(){
        processos = looca.getGrupoDeProcessos().getProcessos();
    }

    public List<com.github.britooo.looca.api.group.processos.Processo> getListProcessos(){
        return processos;
    }

    public String getProcessoPID(int pid){
        for(com.github.britooo.looca.api.group.processos.Processo processoAtual : processos){
            if(processoAtual.getPid().equals(pid)){
                String usoComponente = String.format("CPU: %.2f | MEM: %.2f",
                        processoAtual.getUsoCpu(), processoAtual.getUsoMemoria());
                return usoComponente;
            }
        }
        return null;
    }

    public Double getProcessCPU(int pid){
        for(com.github.britooo.looca.api.group.processos.Processo processoAtual : processos){
            if(processoAtual.getPid().equals(pid)){
                return processoAtual.getUsoCpu();
            }
        }
        return null;
    }

    public Double getProcessRAM(int pid){
        for(com.github.britooo.looca.api.group.processos.Processo processoAtual : processos){
            if(processoAtual.getPid().equals(pid)){
                return processoAtual.getUsoMemoria();
            }
        }
        return null;
    }



    public void killProcess(int pid) throws IOException {
        Runtime rt = Runtime.getRuntime();
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            rt.exec("tskill " + pid);
        }else{
            rt.exec("kill -9 " + pid);
        }
    }




}
