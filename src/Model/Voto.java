package Model;

public abstract class Voto {
    private Eleitor eleitor;

    public Voto(Eleitor eleitor){
        this.eleitor = eleitor;
    }

    public Eleitor obterEleitor(){
        return this.eleitor;
    }
}
