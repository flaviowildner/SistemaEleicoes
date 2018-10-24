package View.ViewController;

import Controller.MainController;
import Model.Administrador;
import Model.Eleitor;
import Model.Usuario;
import View.TelaLogin;

public class TelaLoginController {
    private MainController mainController;
    private TelaLogin view;

    public TelaLoginController(MainController mainController){
        this.mainController = mainController;
        this.view = new TelaLogin(this);
    }

    public void logar(String login, String senha){
        if(login.equals("")){
            view.setMessageError("Todos os campos são obrigatórios.");
            return;
        }
        Usuario usuario = mainController.logar(login, senha);
        if(usuario == null){
            view.setMessageError("Login ou senha inválida");
        }
        if(usuario instanceof Administrador){
            view.dispose();
            new TelaInicialAdministradorController(mainController);
        }else if(usuario instanceof Eleitor){
            //...
        }
    }
}
