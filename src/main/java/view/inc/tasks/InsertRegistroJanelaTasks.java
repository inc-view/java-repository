package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Janela;

import java.sql.SQLException;
import java.util.TimerTask;

public class InsertRegistroJanelaTasks extends TimerTask {

    private Computador computador;
    private Computador computadorSQL;
    private Janela janela;
    private Integer qtdeTotalProgramas;

    public InsertRegistroJanelaTasks(Janela janela, Computador computador, Computador computadorSQL) {
        this.computador = computador;
        this.janela = janela;
        this.computadorSQL = computadorSQL;
        qtdeTotalProgramas = janela.getQuantidadeJanelas();
    }

    @Override
    public void run() {
        try {
            //janela.insertRegistroJanela(computador.getIdComputador());
            janela.insertJanelasSQL(computadorSQL.getIdComputador());
            System.out.println("Inseriu registro");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
