package Model;

import java.util.ArrayList;
import java.util.List;

public class Estado extends UF {
    private List<Municipio> municipioList;
    private Pais pais;

    public Estado(String nome, Pais pais){
        super(nome);
        this.municipioList = new ArrayList<>();
        this.pais = pais;
    }

    public void criarMunicipio(String nome){
        Municipio municipio = new Municipio(nome, this);
        municipioList.add(municipio);
    }

    public Pais obterPais(){
        return this.pais;
    }

    public List<Municipio> obterMunicipios(){
        return this.municipioList;
    }
}
