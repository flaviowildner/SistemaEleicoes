package Controller;

import Model.*;

import java.util.List;


public class MainController {
    public Usuario usuarioAtual = null;

    public MainController(){
        UsuarioDao.getInstance().salvarUsuario(new Administrador("ff", "ff", "ff"));    //Usuario padrao
        new TelaLoginController(this);
    }

    public void cadastrarEleitor(String id, String senha, String nome, List<ZonaEleitoral> zonaEleitoral){
        UsuarioDao.getInstance().salvarUsuario(new Eleitor(id, senha, nome, zonaEleitoral));
    }

    public void cadastrarAdministrador(String id, String senha, String nome){
        UsuarioDao.getInstance().salvarUsuario(new Administrador(id, senha, nome));
    }

    public Usuario logar(String login, String senha){
        UsuarioDao dao = UsuarioDao.getInstance();
        Usuario usuarioLogin = dao.buscarUsuario(login);

        if(usuarioLogin != null) {
            if(usuarioLogin.autenticar(senha) != null){
                usuarioAtual = usuarioLogin;
                return usuarioAtual;
            }
        }
        return null;
    }

    public void deslogar(){
        usuarioAtual = null;
    }
}
