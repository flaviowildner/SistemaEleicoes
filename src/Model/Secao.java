package Model;

public class Secao extends UF {
    private int numero;
    private ZonaEleitoral zonaEleitoral;

    public Secao(int numero, ZonaEleitoral zonaEleitoral) {
        super(Integer.toString(numero));
        this.numero = numero;
        this.zonaEleitoral = zonaEleitoral;
    }

    @Override
    public String getNivel () {
        return "Secao";
    }

    @Override
    public UF getFather () {
        return this.getZonaEleitoral();
    }

    public ZonaEleitoral getZonaEleitoral(){
        return this.zonaEleitoral;
    }
}
