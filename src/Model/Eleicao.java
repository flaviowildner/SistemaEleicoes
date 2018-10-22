package Model;

import java.util.ArrayList;
import java.util.List;

public class Eleicao {
    private String id;
    private List<Candidato> candidatos;
    private List<ZonaEleitoral> zonasPermitidas;
    
    public Eleicao(Candidato candidatoUm, Candidato candidatoDois, List<ZonaEleitoral> zonasPermitidas) {
        this.zonasPermitidas = new ArrayList(zonasPermitidas);
        this.candidatos = new ArrayList<>();

        this.candidatos.add(candidatoUm);
        this.candidatos.add(candidatoDois);
    }

    public void adicionarCandidato(Candidato candidato){
        this.candidatos.add(candidato);
    }

    public void adicionarZonaEleitoral(ZonaEleitoral zonaEleitoral){
        this.zonasPermitidas.add(zonaEleitoral);
    }
}
