package Controller;

import Model.ProcessoEleitoral;

import java.util.List;

public class ControladorProcessoEleitoral {

    public static void criarProcessoEleitoral(String nome){
        ProcessoEleitoral processoEleitoral = new ProcessoEleitoral(nome);
        Database.processosEleitorais().add(processoEleitoral);
    }



    public List<ProcessoEleitoral> listarProcessosEleitorais(){
        return Database.processosEleitorais();
    }

}
