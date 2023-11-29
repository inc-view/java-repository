package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Processo;
import view.inc.model.ProcessoIlicito;

import java.sql.SQLException;
import java.util.TimerTask;

public class ExibeProcIlicitosTask extends TimerTask {
    private Processo processo = new Processo();
    private ProcessoIlicito processoIlicito = new ProcessoIlicito();
    //private Computador computador = new Computador();
    private Computador computadorSQL = new Computador();
    private Integer fkFuncionario;
    private int delay;
    private int periodo;

    public ExibeProcIlicitosTask(Integer fkFuncionario, Computador computadorSQL, int delay, int periodo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       // this.computador = computador;
        this.fkFuncionario = fkFuncionario;
        this.computadorSQL = computadorSQL;
        this.delay = delay;
        this.periodo = periodo;
    }
    @Override
    public void run() {
        try {
            //processoIlicito.checkProcessosIlicitos(computador);
            processoIlicito.checkProcIlicitosSQL(computadorSQL);
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
