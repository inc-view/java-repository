package view.inc;

import view.inc.model.Computador;
import view.inc.model.Funcionario;
import view.inc.model.Processo;

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


        System.out.println(computadorOn.getNomeMaquina());
        System.out.println(funcionarioOn.getIdFuncionario() + "  " + funcionarioOn.getNome());

        Scanner scannerOpcao = new Scanner(System.in);
        Integer opcao;
        String menu = """
                MENU:
                1 - Listar Processos
                0 - Sair""";
        do{
            System.out.println(menu);
            opcao = scannerOpcao.nextInt();
            if(opcao == 1){
                Processo p = new Processo();
                p.getProcessos();
            }
        }while(opcao != 0);

    }
}
