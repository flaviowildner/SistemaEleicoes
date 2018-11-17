package Model;

import java.util.ArrayList;
import java.util.List;

public class Eleicao {
    private String nome;
    private boolean eleicaoEmAndamento;
    private Cargo cargo;
    private List<Candidatura> candidaturaList;
    private List<VotoInvalido> votoInvalidoList;

    public Eleicao(String nome, Cargo cargo){
        this.nome = nome;
        this.eleicaoEmAndamento = false;
        this.cargo = cargo;
        this.candidaturaList = new ArrayList<>();
        this.votoInvalidoList = new ArrayList<>();
    }

    public List<Candidatura> buscarCandidaturas(){
        return candidaturaList;
    }

    public int contabilizarVotosNulos(){
        int contador = 0;
        for(VotoInvalido voto : votoInvalidoList) {
            if (voto.obterTipo() == TipoVotoInvalido.NULO) {
                contador++;
            }
        }
        return contador;
    }

    public int contabilizarVotosBrancos(){
        int contador = 0;
        for(VotoInvalido voto : votoInvalidoList) {
            if (voto.obterTipo() == TipoVotoInvalido.BRANCO) {
                contador++;
            }
        }
        return contador;
    }

    public void adicionarCandidatura(Eleitor eleitor){
        Candidatura candidatura = new Candidatura(eleitor);
        candidaturaList.add(candidatura);
    }

    public void registrarVoto(Eleitor eleitor, Candidatura candidatura){
        VotoValido voto = new VotoValido(eleitor);
        candidatura.adicionarVoto(voto);
    }

    public void registrarVotoInvalido(Eleitor eleitor, TipoVotoInvalido tipoVoto){
        VotoInvalido voto = new VotoInvalido(eleitor, tipoVoto);
        votoInvalidoList.add(voto);
    }

    public boolean eleitorJaVotou(Eleitor eleitor){
        for(Candidatura candidatura : candidaturaList){
            for(VotoValido votoValido : candidatura.obterVotosValidos()){
                if(votoValido.obterEleitor() == eleitor){
                    return true;
                }
            }
        }
        for(VotoInvalido voto : votoInvalidoList){
            if(voto.obterEleitor() == eleitor){
                return true;
            }
        }
        return false;
    }

    public void iniciarEleição(){
        this.eleicaoEmAndamento = true;
    }
    public void encerrarEleicao(){
        this.eleicaoEmAndamento = false;
    }

    public void banirCandidatura(Candidatura candidatura){
        reiniciarEleicao();
        candidaturaList.remove(candidatura);
    }

    private void reiniciarEleicao(){
        for(Candidatura candidatura : candidaturaList){
            candidatura.removerVotos();
        }
        votoInvalidoList.clear();
    }

    public Candidatura obterVencedores(){
        Candidatura vencedor = candidaturaList.get(0);
        for(Candidatura candidatura : candidaturaList.subList(1, candidaturaList.size() - 1)){
            if(candidatura.numeroVotos() > vencedor.numeroVotos()){
                vencedor = candidatura;
            }
        }
        return vencedor;
    }
}
