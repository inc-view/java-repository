package view.inc.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProcessoIlicito extends Processo{

    public List<ProcessoIlicito> getProcessosIlicitos(Computador computador) {
        return getProcessoDao().selectAllProcessosIlicitos(computador.getIdComputador());
    }

    public void adicionaProcessoIlicito(Processo processo) {
        getProcessoDao().insertIlicito(processo);
    }

    @Override
    public String toString() {
        return "ProcessoIlicito{} " + super.toString();
    }
}
