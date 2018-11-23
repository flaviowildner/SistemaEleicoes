package Model;

import java.util.ArrayList;
import java.util.List;

public class Estado extends UF {

    private Pais pais;
    private List<Municipio> municipios;

    public Estado(String nome, Pais pais){
        super(nome);
        this.pais = pais;
        this.municipios = new ArrayList<Municipio>();
    }

    @Override
    public String getNivel () {
        return "Estado";
    }

    @Override
    public List<UF> getChildren () {
        return (List<UF>)(List<?>)this.getMunicipios();
    }

    @Override
    public UF getFather () {
        return this.getPais();
    }

    public Pais getPais(){
        return this.pais;
    }

    public List<Municipio> getMunicipios(){
        return this.municipios;
    }

    public void criarMunicipio (String nome) {
        Municipio add = new Municipio(nome, this);
        this.municipios.add(add);
    }
}
