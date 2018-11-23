package Model;

import java.util.ArrayList;
import java.util.List;

public class ZonaEleitoral extends UF {
    private int numero;
    private String endereco;
    private Municipio municipio;
    private List<Secao> secoes;

    public ZonaEleitoral(int numero, String endereco, Municipio municipio) {
        super(Integer.toString(numero));
        this.numero = numero;
        this.endereco = endereco;
        this.municipio = municipio;
        this.secoes = new ArrayList<Secao>();
    }

    @Override
    public String getNivel () {
        return "Zona Eleitoral";
    }

    @Override
    public List<UF> getChildren () {
        return (List<UF>)(List<?>)this.getSecoes();
    }

    @Override
    public UF getFather () {
        return this.getMunicipio();
    }

    public Municipio getMunicipio(){
        return this.municipio;
    }

    public List<Secao> getSecoes(){
        return this.secoes;
    }

    public void criarSecao (int numero) {
        Secao add = new Secao(numero, this);
        this.secoes.add(add);
    }

    @Override
    public String toString () {
        return this.getNome() + " - " + this.endereco;
    }
}
