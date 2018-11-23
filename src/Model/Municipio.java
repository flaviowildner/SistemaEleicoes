package Model;

import java.util.ArrayList;
import java.util.List;

public class Municipio extends UF {

    private Estado estado;
    private List<ZonaEleitoral> zonasEleitorais;

    public Municipio(String nome, Estado estado){
        super(nome);
        this.estado = estado;
        this.zonasEleitorais = new ArrayList<ZonaEleitoral>();
    }

    @Override
    public String getNivel () {
        return "Municipio";
    }

    @Override
    public List<UF> getChildren () {
        return (List<UF>)(List<?>)this.getZonasEleitorais();
    }

    @Override
    public UF getFather () {
        return this.getEstado();
    }

    public Estado getEstado(){
        return this.estado;
    }

    public List<ZonaEleitoral> getZonasEleitorais(){
        return this.zonasEleitorais;
    }

    public void criarZonaEleitoral (int numero, String endereco) {
        ZonaEleitoral add = new ZonaEleitoral(numero, endereco, this);
        this.zonasEleitorais.add(add);
    }
}
