package Model;

public class Voto {
    private int tipoVoto;
    private String hash;
    private ZonaEleitoral zonaEleitoral;
    private Eleicao eleicao;
    private Candidato candidato;

    public Voto(Eleicao eleicao, Candidato candidato, ZonaEleitoral zonaEleitoral){
        this.eleicao = eleicao;
        this.candidato = candidato;
        this.zonaEleitoral = zonaEleitoral;
    }
}
