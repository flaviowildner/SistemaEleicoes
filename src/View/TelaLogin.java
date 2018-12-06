package View;

import Controller.Sistema;
import Model.Administrador;
import Model.Eleitor;
import Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends View {
    private JPanel rootLogin;
    private JButton loginButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel loginText;
    private JLabel passwordText;
    private JButton registerButton;
    private JLabel loginMessage;

    public TelaLogin(){
        super( "Login");
        initComponents();

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(Sistema.logar(loginField.getText(), new String(passwordField.getPassword())) == null){
                    setMessageError("Login ou senha incorreta");
                    return;
                }
            }
        });
    }

    public void setMessageError(String message){
        loginMessage.setText(message);
    }

    private void initComponents(){
        add(rootLogin);
    }
}
