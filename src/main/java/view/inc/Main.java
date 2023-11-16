package view.inc;

import com.github.britooo.looca.api.core.Looca;
import view.inc.model.*;

import java.util.Scanner;

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


        /*Scanner scannerOpcao = new Scanner(System.in);
        Integer opcao;
        Processo processo = new Processo();
        String menu = """
                MENU:
                1 - Listar Processo
                2 - Listar Processos Ilicitos
                3 - Adicionar Ilicito para essa máquina
                0 - Sair
                """;

        do{
            Integer fkFuncionario = funcionarioOn.getIdFuncionario();
            System.out.println(menu);
            opcao = scannerOpcao.nextInt();
            ProcessoIlicito ilicito = new ProcessoIlicito();
            if(opcao == 1){
                processo.cadastrarProcesso(fkFuncionario, computadorOn);

                System.out.println(processo.getAllProcessos());

            } else if (opcao == 2) {
                ilicito.getProcessosIlicitos(computadorOn);
            }else if(opcao == 3){
                processo.adicionaIlicito(computadorOn.getIdComputador());
            }
        }while(opcao != 0);*/

        Janela janelas = new Janela();
        Integer qtdeTotalProgramas = janelas.getQuantidadeJanelas();
        while(true){

            janelas.getJanelas();
            if(janelas.getQuantidadeJanelas() != qtdeTotalProgramas){
                qtdeTotalProgramas = janelas.getQuantidadeJanelas();
                janelas.insertJanelas(computadorOn.getIdComputador());
                janelas.showJanelas();
            }

        }

    }
}
