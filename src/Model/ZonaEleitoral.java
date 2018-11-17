package Model;

import java.util.ArrayList;
import java.util.List;

public class ZonaEleitoral {
    private int numero;
    private int endereco;
    private List<Secao> secaoList;
    private Municipio municipio;

    public ZonaEleitoral(int numero, String endereco, Municipio municipio){
        this.secaoList = new ArrayList<>();
        this.municipio = municipio;
    }

    public void criarSecao(int numero){
        Secao secao = new Secao(numero, this);
        secaoList.add(secao);
    }

    public Municipio obterMunicipio(){
        return this.municipio;
    }

    public List<Secao> obterSecoes(){
        return this.secaoList;
    }
}
