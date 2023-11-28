package view.inc.model;

import com.github.britooo.looca.api.core.Looca;
import view.inc.dao.ProcessoDao;
import view.inc.dao.ProcessoSQLDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Janela {

    private List<com.github.britooo.looca.api.group.janelas.Janela> janelas;
    private Looca looca;
    private ProcessoComputador processoComputador;


    ProcessoDao processoDao = new ProcessoDao();
    ProcessoSQLDao processoSQLDao = new ProcessoSQLDao();

    public Janela() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.janelas = new ArrayList<>();
        this.looca = new Looca();
        this.processoComputador = new ProcessoComputador();
        getJanelas();
    }

    public void getJanelas(){
        janelas = looca.getGrupoDeJanelas().getJanelasVisiveis();
    }

    public List<com.github.britooo.looca.api.group.janelas.Janela> getAllJanelas(){
        return looca.getGrupoDeJanelas().getJanelas();
    }

    public Integer getQuantidadeJanelas(){ return janelas.size(); }


    public void insertJanelas(Integer idComputador) throws SQLException {
        for (com.github.britooo.looca.api.group.janelas.Janela janelaAtual : janelas){
            if(!janelaAtual.getTitulo().isEmpty()){
                int pid = janelaAtual.getPid().intValue();
                processoDao.insertPrograma(janelaAtual.getTitulo(), idComputador);
            }
        }
    }
    public void insertJanelasSQL(Integer idComputador) throws SQLException {
        for (com.github.britooo.looca.api.group.janelas.Janela janelaAtual : janelas){
            if(!janelaAtual.getTitulo().isEmpty()){
                int pid = janelaAtual.getPid().intValue();
                processoSQLDao.insertPrograma(janelaAtual.getTitulo(), idComputador);
            }
        }
    }
    public void insertRegistroSQL(Integer computador) throws SQLException {
        for (com.github.britooo.looca.api.group.janelas.Janela janelaAtual : janelas){
            if(!janelaAtual.getTitulo().isEmpty()){
                int pid = janelaAtual.getPid().intValue();
                processoSQLDao.insertRegistro(janelaAtual.getTitulo(), computador,
                        processoComputador.getProcessCPU(pid), processoComputador.getProcessRAM(pid));
            }
        }
    }


    public void insertRegistroJanela(Integer idComputador) throws SQLException {
        for (com.github.britooo.looca.api.group.janelas.Janela janelaAtual : janelas){
            if(!janelaAtual.getTitulo().isEmpty()){
                int pid = janelaAtual.getPid().intValue();
                processoDao.insertRegistro(
                        janelaAtual.getTitulo(), idComputador,
                        processoComputador.getProcessCPU(pid), processoComputador.getProcessRAM(pid)
                );
            }
        }
    }


    public void showJanelas(){

        for(com.github.britooo.looca.api.group.janelas.Janela janelaAtual : janelas){

            int pid = janelaAtual.getPid().intValue();
            String nomeProcesso = (janelaAtual.getTitulo().equals("") ? "Não identificado" : janelaAtual.getTitulo());

            System.out.println(
                    String.format("Nome: %s  >>  %s",
                            nomeProcesso, processoComputador.getProcessoPID(pid)
                    )
            );

        }

    }

    public void checkProcess() throws IOException {

        List<String> processosBloqueados = new ArrayList<>();
        processosBloqueados.add("ChatGPT");
        processosBloqueados.add("Discord");
        processosBloqueados.add("Bard");

        for(com.github.britooo.looca.api.group.janelas.Janela janelaAtual : janelas){

            for (int i = 0; i < processosBloqueados.size(); i++) {

                String nomeProcessoBloqueado = processosBloqueados.get(i).toLowerCase();
                String nomeJanelaAtual = janelaAtual.getTitulo().toLowerCase();

                if(nomeJanelaAtual.contains(nomeProcessoBloqueado)){
                    int pid = janelaAtual.getPid().intValue();
                    processoComputador.killProcess(pid);

                    System.out.println("Processo encerrado >> " + nomeJanelaAtual);
                }

            }

        }
    }


}
