package Model;

import java.util.ArrayList;
import java.util.List;

public class Pais extends UF {
    public List<Estado> estadoList;

    public Pais(String nome){
        super(nome);
        estadoList = new ArrayList<>();
    }

    public void adicionarEstado(String nome){
        Estado estado = new Estado(nome);
        estadoList.add(estado);
    }

    public List<Estado> obterEstados(){
        return estadoList;
    }
}
