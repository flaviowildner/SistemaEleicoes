package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessoEleitoral {
    String nome;
    Date data_inicio;
    Date data_termino;
    private List<Eleicao> eleicaoList;

    public ProcessoEleitoral(String nome){
        this.nome = nome;
        eleicaoList = new ArrayList<>();
    }

    public List<Eleicao> buscarEleicoes(){
        return this.eleicaoList;
    }

    public void criarEleicao(String nome, Cargo cargo){
        Eleicao eleicao = new Eleicao(nome, cargo);
        eleicaoList.add(eleicao);
    }

    public void iniciarEleicoes(){
        for(Eleicao eleicao : eleicaoList){
            eleicao.iniciarEleição();
        }
    }

    public void encerrarEleicoes(){
        for(Eleicao eleicao : eleicaoList){
            eleicao.encerrarEleicao();
        }
    }

    public String toString(){return this.nome;}
}
