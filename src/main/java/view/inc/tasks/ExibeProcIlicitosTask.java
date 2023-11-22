package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Processo;
import view.inc.model.ProcessoIlicito;

import java.io.IOException;
import java.util.TimerTask;

public class ExibeProcIlicitosTask extends TimerTask {
    private Processo processo = new Processo();
    private ProcessoIlicito processoIlicito = new ProcessoIlicito();
    private Computador computador = new Computador();
    private Integer idMaquina;
    private Integer fkFuncionario;
    private int delay;
    private int periodo;

    public ExibeProcIlicitosTask(Computador computador, Integer fkFuncionario, int delay, int periodo){
        this.computador = computador;
        this.fkFuncionario = fkFuncionario;
        this.delay = delay;
        this.periodo = periodo;
    }
    @Override
    public void run() {
        try {
            processoIlicito.checkProcessosIlicitos(computador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDelay() {
        return delay;
    }

    public int getPeriodo() {
        return periodo;
    }
}
