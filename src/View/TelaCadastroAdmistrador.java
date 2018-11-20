package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroAdmistrador extends View{
    private JTextField loginField;
    private JPanel rootTelaCadastroAdmin;
    private JPasswordField passwordField;
    private JTextField nomeField;
    private JButton cadastrarButton;
    private JButton voltar;

    public TelaCadastroAdmistrador(Sistema sistema, Usuario usuario){
        super(sistema, usuario, "Cadastro Administrador");
        initComponents();

        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            sistema.criarAdministrador(loginField.getText(), new String(passwordField.getPassword()));
            voltarTelaInicialAdministrador();
            }
        });

        voltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                voltarTelaInicialAdministrador();
            }
        });
    }

    private void voltarTelaInicialAdministrador(){
        new TelaInicialAdministrador(sistema, usuario);
        dispose();
    }

    private void initComponents(){
        add(rootTelaCadastroAdmin);
    }
}
