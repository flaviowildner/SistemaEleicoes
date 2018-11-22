package Model;

import java.util.ArrayList;
import java.util.List;

public class ZonaEleitoral extends UF {
    private int numero;
    private String endereco;

    public ZonaEleitoral(int numero, String endereco, Municipio municipio) {
        super(Integer.toString(numero), UF.NIVEL_ZONAELEITORAL, municipio);
        this.numero = numero;
        this.endereco = endereco;
    }

    public Municipio obterMunicipio(){
        return (Municipio)this.father;
    }

    public List<UF> obterSecoes(){
        return this.children;
    }

    public void criarSecao (int numero) {
        new Secao(numero, this);
    }

    @Override
    public String toString () {
        return this.getNome() + " - " + this.endereco;
    }
}
