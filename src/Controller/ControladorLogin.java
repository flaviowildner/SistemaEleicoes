package Controller;

import Model.Eleitor;
import Model.Usuario;

public class ControladorLogin {

    public enum TipoUsuario {NAO_ENCONTRADO, ELEITOR, ADMINISTRADOR}

    public TipoUsuario logar(String login, String senha){
        for(Usuario usuario : Database.usuarios()){
            if(usuario.getLogin().equals(login)){
                if(usuario.autenticar(senha)) {
                    Database.usuarioLogado(usuario);
                    if (usuario instanceof Eleitor) {
                        return TipoUsuario.ELEITOR;
                    } else {
                        return TipoUsuario.ADMINISTRADOR;
                    }
                }
            }
        }
        return TipoUsuario.NAO_ENCONTRADO;
    }
}
