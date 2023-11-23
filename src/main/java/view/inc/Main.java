package view.inc;

import com.github.britooo.looca.api.core.Looca;
import org.h2.command.dml.Insert;
import view.inc.model.*;
import view.inc.tasks.ExibeProcIlicitosTask;
import view.inc.tasks.InsertRegistroJanelaTasks;
import view.inc.tasks.JanelaInsertTask;
import view.inc.tasks.UpdateListJanelaTask;

import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args){

        Scanner inputText = new Scanner(System.in);
        Scanner inputTextLong = new Scanner(System.in);
        Computador computadorOn;
        Funcionario funcionarioOn;

        Boolean logged = false;
        do{

            System.out.print("Email: ");
            String email = inputText.next();

            System.out.print("Senha: ");
            String senha = inputTextLong.nextLine();

            funcionarioOn = new Funcionario();
            funcionarioOn = funcionarioOn.logar(email, senha);
            logged = (funcionarioOn != null);

            if(!logged){
                System.out.println("Email ou Senha incorretos!");
            }

        }while(!logged);


        /* Reconhecendo máquina */
        Boolean recognized = false;
        do{

            computadorOn = new Computador();
            Integer fkFuncionario = funcionarioOn.getIdFuncionario();
            String ipComputador = computadorOn.getIpMaquina();

            computadorOn = computadorOn.recognizeMachine(fkFuncionario, ipComputador);
            if(computadorOn != null){
                System.out.println(">> Bem-vindo!");
                recognized = true;
            }else{
                System.out.println(">> Registrando máquina...");
                computadorOn = new Computador();
                computadorOn.registerMachine(fkFuncionario);
            }

        }while(!recognized);



        Timer agendador = new Timer();
        Janela janelas = new Janela();

        InsertRegistroJanelaTasks tarefaInsertRegistrojanela = new InsertRegistroJanelaTasks(janelas, computadorOn);
        agendador.schedule(tarefaInsertRegistrojanela, 11000, 5000);

        UpdateListJanelaTask tarefaUpdateList = new UpdateListJanelaTask(janelas);
        agendador.schedule(tarefaUpdateList, 0, 9000);

        JanelaInsertTask tarefaInsertJanela = new JanelaInsertTask(janelas, computadorOn);
        agendador.schedule(tarefaInsertJanela, 0, 10000);

        ExibeProcIlicitosTask exibir = new ExibeProcIlicitosTask(computadorOn, funcionarioOn.getIdFuncionario(), 11000, 10000);
        agendador.schedule(exibir, exibir.getDelay(), exibir.getPeriodo());


    }
}
