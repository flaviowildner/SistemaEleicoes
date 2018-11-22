package Model;

public class Secao extends UF {
    private int numero;

    public Secao(int numero, ZonaEleitoral zonaEleitoral) {
        super(Integer.toString(numero), UF.NIVEL_SECAO, zonaEleitoral);
        this.numero = numero;
    }

    public ZonaEleitoral obterZonaEleitoral(){
        return (ZonaEleitoral)this.father;
    }

    public int obterNumero(){
        return this.numero;
    }
}
