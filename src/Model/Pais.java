package Model;

import java.util.ArrayList;
import java.util.List;

public class Pais extends UF {
    public List<Estado> estadoList;

    public Pais(String nome){
        super(nome);
        estadoList = new ArrayList<>();
    }

    public void criarEstado(String nome){
        Estado estado = new Estado(nome, this);
        estadoList.add(estado);
    }

    public List<Estado> obterEstados(){
        return estadoList;
    }
}
