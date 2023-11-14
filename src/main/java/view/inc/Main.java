package view.inc;

import com.github.britooo.looca.api.core.Looca;
import view.inc.model.Computador;
import view.inc.model.Funcionario;
import view.inc.model.Processo;
import view.inc.model.ProcessoIlicito;

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
        Processo processo = new Processo();
        String menu = """
                MENU:
                1 - Listar Processo
                2 - Listar Processos Ilicitos
                0 - Sair
                """;
        do{
            Integer fkFuncionario = funcionarioOn.getIdFuncionario();
            String ipComputador = computadorOn.getIpMaquina();
            System.out.println(menu);
            opcao = scannerOpcao.nextInt();
            if(opcao == 1){
                processo.cadastrarProcesso(fkFuncionario, ipComputador);
                Looca looca = new Looca();

                for (com.github.britooo.looca.api.group.processos.Processo processo1 : looca.getGrupoDeProcessos().getProcessos()) {
                    System.out.println(processo1.getNome());
                }
            } else if (opcao == 2) {
                ProcessoIlicito ilicito = new ProcessoIlicito();
                System.out.println(ilicito.getProcessosIlicitos(computadorOn).toString());
            }
        }while(opcao != 0);

    }
}
