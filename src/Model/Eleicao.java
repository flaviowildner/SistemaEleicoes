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

    public void adicionarCandidatura(String nomeFantasia, int numero, Eleitor eleitor) throws SistemaEleicaoException {
        for(Candidatura candidatura : candidaturaList){
            if(candidatura.obterNomeFantasia().equals(nomeFantasia)){
                throw new SistemaEleicaoException("Ja existe candidatura com nome fantasia escolhido");
            }else if(candidatura.obterNumeroCandidatura() == numero){
                throw new SistemaEleicaoException("Ja existe candidatura com numero escolhido");
            }else if(candidatura.obterEleitor() == eleitor){
                throw new SistemaEleicaoException("Ja existe candidatura com eleitor escolhido");
            }
        }
        Candidatura candidatura = new Candidatura(nomeFantasia, numero, eleitor);
        candidaturaList.add(candidatura);
    }

    public void registrarVoto(Eleitor eleitor, Candidatura candidatura){
        if(this.eleicaoEmAndamento == false){
            return;
        }
        if(eleitorJaVotou(eleitor)){
            return;
        }
        candidatura.adicionarVoto(eleitor);
    }

    public void registrarVotoInvalido(Eleitor eleitor, TipoVotoInvalido tipoVoto){
        if(this.eleicaoEmAndamento == false){
            return;
        }
        if(eleitorJaVotou(eleitor)){
            return;
        }
        VotoInvalido voto = new VotoInvalido(eleitor, tipoVoto);
        votoInvalidoList.add(voto);
    }

    public boolean eleitorJaVotou(Eleitor eleitor){
        for(Candidatura candidatura : candidaturaList){
            for(VotoValido votoValido : candidatura.obterVotosValidos()){
                if(votoValido.obterEleitor().equals(eleitor)){
                    return true;
                }
            }
        }
        for(VotoInvalido voto : votoInvalidoList){
            if(voto.obterEleitor().equals(eleitor)){
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

    //Implementar caso de empate
    public Candidatura obterVencedor(){
        Candidatura vencedor = candidaturaList.get(0);
        for(Candidatura candidatura : candidaturaList.subList(1, candidaturaList.size())){
            if(candidatura.numeroVotos() > vencedor.numeroVotos()){
                vencedor = candidatura;
            }
        }
        return vencedor;
    }

    public String toString(){return this.nome;}
}
