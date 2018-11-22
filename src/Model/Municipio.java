package Model;

import java.util.List;

public class Municipio extends UF {

    public Municipio(String nome, Estado estado){
        super(nome, UF.NIVEL_MUNICIPIO, estado);
    }

    public Estado obterEstado(){
        return (Estado)this.father;
    }

    public List<UF> obterZonasEleitorais(){
        return this.children;
    }

    public void criarZonaEleitoral (int numero, String endereco) {
        new ZonaEleitoral(numero, endereco, this);
    }
}
