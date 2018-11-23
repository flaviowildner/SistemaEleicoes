package Model;

import java.util.ArrayList;
import java.util.List;

public class Pais extends UF {
    private static Pais _brasil;

    private List<Estado> estados;

    public Pais(String nome){
        super(nome);

        this.estados = new ArrayList<Estado>();
    }

    @Override
    public String getNivel () {
        return "Pais";
    }

    @Override
    public List<UF> getChildren () {
        return (List<UF>)(List<?>)this.getEstados();
    }

    public List<Estado> getEstados(){
        return this.estados;
    }

    public void criarEstado (String nome) {
        Estado add = new Estado(nome, this);
        this.estados.add(add);
    }
}
