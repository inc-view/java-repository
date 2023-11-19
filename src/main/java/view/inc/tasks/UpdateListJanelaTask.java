package view.inc.tasks;

import view.inc.model.Janela;

import java.util.TimerTask;

public class UpdateListJanelaTask extends TimerTask {

    private Janela janela;

    public UpdateListJanelaTask(Janela janela) {
        this.janela = janela;
    }

    @Override
    public void run() {
        janela.getJanelas();
        System.out.println("Atualizou janelas");

    }

}
