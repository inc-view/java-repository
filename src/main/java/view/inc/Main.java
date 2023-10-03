package view.inc;

import com.github.britooo.looca.api.core.Looca;
import view.inc.model.Computador;

public class Main {
    public static void main(String[] args){

        Computador computador = new Computador();
        if(computador.recognizeMachine(1, computador.getIpMaquina()) != null){
            computador = computador.recognizeMachine(1, computador.getIpMaquina());
            System.out.println(computador.getId());
        }else{
            computador.registerMachine(1);
        }






    }
}
