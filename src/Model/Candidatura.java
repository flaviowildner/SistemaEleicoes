package Model;

import java.util.ArrayList;
import java.util.List;

public class Candidatura {
    private String nomeFantasia;
    private int numero;
    private List<VotoValido> votosValidos;
    private Eleitor eleitor;

    public Candidatura(String nomeFantasia, int numero, Eleitor eleitor){
        this.nomeFantasia = nomeFantasia;
        this.numero = numero;
        this.eleitor = eleitor;
        this.votosValidos = new ArrayList<>();
    }

    public void adicionarVoto(Eleitor eleitor){
        VotoValido voto = new VotoValido(eleitor);
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

    public Eleitor obterEleitor(){return this.eleitor;}

    public void removerVotos(){
        this.votosValidos.clear();
    }

    public String toString(){return this.nomeFantasia + " - " + this.numero;}
}
