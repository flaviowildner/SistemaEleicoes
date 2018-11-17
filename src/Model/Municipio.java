package Model;

import java.util.List;

public class Municipio extends UF {
    private List<ZonaEleitoral> zonaEleitoralList;
    private Estado estado;

    public Municipio(String nome, Estado estado){
        super(nome);
        this.estado = estado;
    }

    public void criarZonaEleitoral(int numero, String endereco){
        ZonaEleitoral zonaEleitoral = new ZonaEleitoral(numero, endereco, this);
        zonaEleitoralList.add(zonaEleitoral);
    }

    public Estado obterEstado(){
        return estado;
    }

    public List<ZonaEleitoral> obterZonasEleitorais(){
        return this.zonaEleitoralList;
    }
}
