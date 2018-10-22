package View;

import Controller.TelaCadastroAdministradorController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroAdmistrador extends JFrame{
    private JTextField loginField;
    private JPanel root;
    private JPasswordField passwordField;
    private JTextField nomeField;
    private JButton cadastrarButton;

    private TelaCadastroAdministradorController controller;

    public TelaCadastroAdmistrador(TelaCadastroAdministradorController controller){
        this.controller = controller;
        initComponents();

        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.cadastrarAdministrador(loginField.getText(), new String(passwordField.getPassword()), nomeField.getText());
            }
        });
    }

    private void initComponents(){
        setTitle("Administrador");
        add(root);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}