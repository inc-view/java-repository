package view.inc.tasks;

import view.inc.model.Computador;
import view.inc.model.Funcionario;
import view.inc.model.Processo;
import view.inc.model.ProcessoIlicito;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TimerTask;

public class ExibeProcIlicitosTask extends TimerTask {
    private Processo processo = new Processo();
    private ProcessoIlicito processoIlicito = new ProcessoIlicito();
    private Computador computador = new Computador();
    private Computador computadorSQL = new Computador();
    private Funcionario funcionarioOn = new Funcionario();
    private Integer idMaquina;
    private Integer fkFuncionario;
    private int delay;
    private int periodo;

    public ExibeProcIlicitosTask(Computador computador, Funcionario funcionarioOn ,Integer fkFuncionario, Computador computadorSQL, int delay, int periodo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.computador = computador;
        this.fkFuncionario = fkFuncionario;
        this.computadorSQL = computadorSQL;
        this.funcionarioOn = funcionarioOn;
        this.delay = delay;
        this.periodo = periodo;
    }
    @Override
    public void run() {
        try {
            //processoIlicito.checkProcessosIlicitos(computador);
            processoIlicito.checkProcIlicitosSQL(computadorSQL, funcionarioOn);
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
