package Model;

import java.util.List;

public abstract class UF {

    private String nome;

    public UF(String nome) {
        this.nome = nome;
    }

    public String getNome () {
        return this.nome;
    }

    public List<UF> getChildren () { return null; }
    public UF getFather() { return null; }

    public abstract String getNivel ();

    @Override
    public String toString () {
        return this.getNome();
    }

}
