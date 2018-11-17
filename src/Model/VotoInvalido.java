package Model;

public class VotoInvalido extends Voto {
    private TipoVotoInvalido tipoVotoInvalido;

    public VotoInvalido(Eleitor eleitor, TipoVotoInvalido tipoVotoInvalido){
        super(eleitor);
        this.tipoVotoInvalido = tipoVotoInvalido;
    }

    public TipoVotoInvalido obterTipo(){
        return tipoVotoInvalido;
    }
}
