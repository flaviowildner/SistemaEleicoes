package View;

import Controller.ControladorLogin;
import Controller.ControladorLogin.TipoUsuario;

import javax.swing.*;
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
    private ControladorLogin controlador;

    public TelaLogin(){
        super( "Login");
        add(rootLogin);

        controlador = new ControladorLogin();

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                TipoUsuario usuario = controlador.logar(loginField.getText(), new String(passwordField.getPassword()));

                switch (usuario) {
                    case NAO_ENCONTRADO:
                        setMessageError("Login ou senha incorreta");
                        break;
                    case ADMINISTRADOR:
                        new TelaInicialAdministrador();
                        dispose();
                        break;
                    case ELEITOR:
                        new TelaInicialEleitor();
                        dispose();
                        break;
                }
            }
        });
    }

    public void setMessageError(String message){
        loginMessage.setText(message);
    }
}
