package View;

import Controller.TelaLoginController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends JFrame {
    private JPanel root;
    private JButton loginButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel loginText;
    private JLabel passwordText;
    private JButton registerButton;
    private JLabel loginMessage;

    private TelaLoginController controller;

    public TelaLogin(TelaLoginController controller){
        this.controller = controller;
        initComponents();

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.logar(loginField.getText(), new String(passwordField.getPassword()));
            }
        });
    }

    public void setMessageError(String message){
        loginMessage.setText(message);
    }

    private void initComponents(){
        setTitle("Login");
        add(root);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
