package Model;

import java.util.List;

public class Estado extends UF {

    public Estado(String nome, Pais pais){
        super(nome, UF.NIVEL_ESTADO, pais);
    }

    public Pais obterPais(){
        return (Pais)this.father;
    }

    public List<UF> obterMunicipios(){
        return this.children;
    }
}
