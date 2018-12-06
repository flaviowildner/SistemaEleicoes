package Controller;

import Model.Eleitor;
import Model.Secao;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControladorCadastroEleitor {

    private Secao _secao;

    public void criarEleitor(String login, String senha, String cpf, String nome){
        Eleitor eleitor = new Eleitor(login, senha, cpf, nome, _secao);
        Database.usuarios().add(eleitor);
    }


    public List<Eleitor> buscarEleitor(){
        List<Eleitor> retornoEleitor = new ArrayList<>();
        for(Usuario usuario : Database.usuarios()) {
            if(usuario instanceof Eleitor){
                retornoEleitor.add((Eleitor)usuario);
            }
        }
        return retornoEleitor;
    }

    public void transferirSecaoEleitor(Eleitor eleitor){
        ((Eleitor)Database.usuarios().get(Database.usuarios().indexOf(eleitor))).alterarSecao(_secao);
    }

    public void setSecao (Secao secao) {
        _secao = secao;
    }
}
