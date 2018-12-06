package Controller;

import Model.Eleicao;
import Model.ProcessoEleitoral;

import java.util.List;

public class ControladorProcessoEleitoral {

    private ProcessoEleitoral _processo;

    public void criarProcessoEleitoral(String nome){
        ProcessoEleitoral processoEleitoral = new ProcessoEleitoral(nome);
        Database.processosEleitorais().add(processoEleitoral);
    }

    public List<ProcessoEleitoral> listarProcessosEleitorais(){
        return Database.processosEleitorais();
    }

    public List<Eleicao> listarEleicoes () {
        return _processo.buscarEleicoes();
    }

    public void iniciarEleicoes () {
        for (Eleicao e: _processo.buscarEleicoes()) {
            e.iniciarEleição();
        }
    }

    public void encerrarEleicoes() {
        for (Eleicao e : _processo.buscarEleicoes()) {
            e.encerrarEleicao();
        }
    }


    public void setProcesso (ProcessoEleitoral processo) {
        _processo = processo;
    }

    public ProcessoEleitoral processo() {
        return _processo;
    }

}
