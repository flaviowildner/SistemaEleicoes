package Controller;

import Model.Estado;
import Model.Municipio;
import Model.ZonaEleitoral;

public class ControladorUnidadesFederativas {

    public void criarEstado(String nome) {
        Database.brasil().criarEstado(nome);
    }

    public void criarMunicipio(String nome, Estado estado){
        estado.criarMunicipio(nome);
    }

    public void criarZonaEleitoral(int numero, String endereco, Municipio municipio) {
        municipio.criarZonaEleitoral(numero, endereco);
    }

    public static void criarSecao(int numero, ZonaEleitoral zonaEleitoral){
        zonaEleitoral.criarSecao(numero);
    }

}
