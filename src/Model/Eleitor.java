package Model;

import java.util.ArrayList;
import java.util.List;

public class Eleitor extends Usuario{
    private List<ZonaEleitoral> zonaEleitoral;
    private List<Voto> votos;

    public Eleitor(String id, String senha, String nome, List<ZonaEleitoral> zonaEleitoral) {
        super(id, senha, nome);
        this.zonaEleitoral = new ArrayList<>();
        this.votos = new ArrayList<>();
    }
}