package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Janela;

import java.sql.SQLException;
import java.util.TimerTask;

public class JanelaInsertTask extends TimerTask {

    private Computador computador;
    private Computador computadorSQL;
    private Janela janela;
    private Integer qtdeTotalProgramas;

    public JanelaInsertTask(Janela janela, Computador computador, Computador computadorSQL) {
        this.computador = computador;
        this.janela = janela;
        this.computadorSQL = computadorSQL;
        qtdeTotalProgramas = janela.getQuantidadeJanelas();
    }

    @Override
    public void run() {
        if(janela.getQuantidadeJanelas() != qtdeTotalProgramas){
            qtdeTotalProgramas = janela.getQuantidadeJanelas();
            try {
                janela.insertJanelas(computador.getIdComputador());
                janela.insertJanelasSQL(computadorSQL.getIdComputador());
                System.out.println("Atualizou");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
