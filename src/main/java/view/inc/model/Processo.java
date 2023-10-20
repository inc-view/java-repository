package view.inc.model;

import com.github.britooo.looca.api.core.Looca;

import java.util.List;

public class Processo {
    Integer idProcesso;
    String nomeProcesso;
    Computador computador;
    Double usoCPU;

    public Processo(){}
    public Processo(Integer idProcesso, String nomeProcesso, Computador computador, Double usoCPU) {
        this.idProcesso = idProcesso;
        this.nomeProcesso = nomeProcesso;
        this.computador = computador;
        this.usoCPU = usoCPU;
    }

    //pega todos os processos naquele momento
    public void getProcessos(){
        Looca looca = new Looca();
        List<com.github.britooo.looca.api.group.processos.Processo> processos = looca.getGrupoDeProcessos().getProcessos();
    }
    public void getProcessosMaliciosos(){

    }

}
