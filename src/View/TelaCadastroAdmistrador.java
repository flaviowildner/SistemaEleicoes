package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroAdmistrador extends JFrame{
    private JTextField loginField;
    private JPanel rootTelaCadastroAdmin;
    private JPasswordField passwordField;
    private JTextField nomeField;
    private JButton cadastrarButton;

    private Sistema sistema;
    private Usuario usuario;

    public TelaCadastroAdmistrador(Sistema sistema, Usuario usuario){
        this.sistema = sistema;
        this.usuario = usuario;
        initComponents();

        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sistema.cadastrarAdministrador(loginField.getText(), new String(passwordField.getPassword()));
                voltarTelaInicialAdministrador();
            }
        });
    }

    private void voltarTelaInicialAdministrador(){
        new TelaInicialAdministrador(sistema, usuario);
        dispose();
    }

    private void initComponents(){
        setTitle("Cadastro Administrador");
        setSize(800, 400);
        add(rootTelaCadastroAdmin);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
