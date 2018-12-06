package Controller;

import Model.Administrador;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControladorCadastroAdministrador {

    public void criarAdministrador(String login, String senha) {
        Administrador administrador;
        administrador = new Administrador(login, senha);
        Database.usuarios().add(administrador);
    }

    public List<Administrador> buscarAdministrador(){
        List<Administrador> retornoAdministrador = new ArrayList<>();
        for(Usuario usuario : Database.usuarios()){
            if(usuario instanceof Administrador){
                retornoAdministrador.add((Administrador) usuario);
            }
        }
        return retornoAdministrador;
    }
}
