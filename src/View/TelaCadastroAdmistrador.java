package View;

import Controller.ControladorCadastroAdministrador;

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
    private ControladorCadastroAdministrador controlador;

    public TelaCadastroAdmistrador(){
        super( "Cadastro Administrador");
        add(rootTelaCadastroAdmin);

        controlador = new ControladorCadastroAdministrador();

        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            controlador.criarAdministrador(loginField.getText(), new String(passwordField.getPassword()));
            new TelaInicialAdministrador();
            dispose();
            }
        });

        voltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador();
                dispose();
            }
        });
    }
}
