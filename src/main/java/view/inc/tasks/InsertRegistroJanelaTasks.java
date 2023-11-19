package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Janela;

import java.util.TimerTask;

public class InsertRegistroJanelaTasks extends TimerTask {

    private Computador computador;
    private Janela janela;
    private Integer qtdeTotalProgramas;

    public InsertRegistroJanelaTasks(Janela janela, Computador computador) {
        this.computador = computador;
        this.janela = janela;
        qtdeTotalProgramas = janela.getQuantidadeJanelas();
    }

    @Override
    public void run() {
        janela.insertRegistroJanela(computador.getIdComputador());
        System.out.println("Inseriu registro");
    }

}
