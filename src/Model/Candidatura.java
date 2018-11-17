package Model;

import java.util.ArrayList;
import java.util.List;

public class Candidatura {
    private String nomeFantasia;
    private int numero;
    private List<VotoValido> votosValidos;
    private Eleitor eleitor;

    public Candidatura(Eleitor eleitor){
        this.eleitor = eleitor;
        this.votosValidos = new ArrayList<>();
    }

    public void adicionarVoto(VotoValido voto){
        this.votosValidos.add(voto);
    }

    public List<VotoValido> obterVotosValidos(){
        return votosValidos;
    }

    public int numeroVotos(){
        return votosValidos.size();
    }

    public int obterNumeroCandidatura(){
        return this.numero;
    }

    public String obterNomeFantasia(){
        return this.nomeFantasia;
    }

    public void removerVotos(){
        this.votosValidos.clear();
    }
}
