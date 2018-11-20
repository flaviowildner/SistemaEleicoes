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

    public void iniciarEleicao(Eleicao eleicao){
        eleicao.iniciarEleição();
    }

    public void encerrarEleicao(Eleicao eleicao){
        eleicao.encerrarEleicao();
    }
}
