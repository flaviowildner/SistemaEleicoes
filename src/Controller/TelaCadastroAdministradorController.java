package Controller;

import View.TelaCadastroAdmistrador;

public class TelaCadastroAdministradorController {
    private MainController mainController;
    private TelaCadastroAdmistrador view;

    public TelaCadastroAdministradorController(MainController mainController){
        this.mainController = mainController;
        this.view = new TelaCadastroAdmistrador(this);
    }

    public void cadastrarAdministrador(String id, String senha, String nome){
        this.mainController.cadastrarAdministrador(id, senha, nome);
        view.dispose();
        new TelaInicialAdministradorController(mainController);
    }
}
