package View.ViewController;

import Controller.MainController;
import View.TelaInicialAdministrador;

public class TelaInicialAdministradorController {
    private MainController mainController;
    private TelaInicialAdministrador view;

    public TelaInicialAdministradorController(MainController mainController){
        this.mainController = mainController;
        this.view = new TelaInicialAdministrador(this);
    }

    public void solicitaCadastroAdministrador(){
        view.dispose();
        new TelaCadastroAdministradorController(mainController);
    }

    public void deslogar(){
        mainController.deslogar();
        view.dispose();
        new TelaLoginController(mainController);
    }
}
