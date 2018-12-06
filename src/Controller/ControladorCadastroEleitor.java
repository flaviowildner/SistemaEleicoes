package Controller;

import Model.Eleitor;
import Model.Secao;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControladorCadastroEleitor {

    public static void criarEleitor(String login, String senha, String cpf, String nome, Secao secao){
        Eleitor eleitor = new Eleitor(login, senha, cpf, nome, secao);
        Database.usuarios().add(eleitor);
    }


    public static List<Eleitor> buscarEleitor(){
        List<Eleitor> retornoEleitor = new ArrayList<>();
        for(Usuario usuario : Database.usuarios()) {
            if(usuario instanceof Eleitor){
                retornoEleitor.add((Eleitor)usuario);
            }
        }
        return retornoEleitor;
    }

    public static void transferirSecaoEleitor(Eleitor eleitor, Secao secao){
        ((Eleitor)Database.usuarios().get(Database.usuarios().indexOf(eleitor))).alterarSecao(secao);
    }
}
