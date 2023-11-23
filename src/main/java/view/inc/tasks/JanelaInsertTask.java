package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Janela;

import java.util.TimerTask;

public class JanelaInsertTask extends TimerTask {

    private Computador computador;
    private Janela janela;
    private Integer qtdeTotalProgramas;

    public JanelaInsertTask(Janela janela, Computador computador) {
        this.computador = computador;
        this.janela = janela;
        qtdeTotalProgramas = janela.getQuantidadeJanelas();
    }

    @Override
    public void run() {
        janela.insertJanelas(computador.getIdComputador());
        System.out.println("Atualizou");
    }
}
