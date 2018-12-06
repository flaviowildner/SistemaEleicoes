package Controller;

import Model.*;

import java.util.List;

public class ControladorEleicao {

    public static void criarEleicao(String nome, Cargo cargo, ProcessoEleitoral processoEleitoral){
        processoEleitoral.criarEleicao(nome, cargo);
    }

    public static List<Eleicao> buscarEleicoes(ProcessoEleitoral processoEleitoral){
        return processoEleitoral.buscarEleicoes();
    }

    public void iniciarEleicoes(ProcessoEleitoral processo){
        processo.iniciarEleicoes();
    }

    public void encerrarEleicoes(ProcessoEleitoral processo) {
        processo.encerrarEleicoes();
    }

    public void criarCargo(Posicao posicao, UF uf){
        Cargo cargo = new Cargo(posicao, uf);
        Database.cargos().add(cargo);
    }

    public void registrarVoto(Eleicao eleicao, Candidatura candidatura){
        eleicao.registrarVoto((Eleitor) Database.usuarioLogado(), candidatura);
    }
}
