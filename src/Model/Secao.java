package Model;

public class Secao {
    private int numero;
    private ZonaEleitoral zonaEleitoral;

    public Secao(int numero, ZonaEleitoral zonaEleitoral){
        this.numero = numero;
        this.zonaEleitoral = zonaEleitoral;
    }

    public ZonaEleitoral obterZonaEleitoral(){
        return this.zonaEleitoral;
    }

    public int obterNumero(){
        return this.numero;
    }
}
