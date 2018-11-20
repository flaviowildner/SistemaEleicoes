package View;

import Controller.Sistema;
import Model.Administrador;
import Model.Eleitor;
import Model.Usuario;

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

    public TelaLogin(Sistema sistema){
        super(sistema, null, "Login");
        initComponents();

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Usuario usuario;
                usuario = sistema.logar(loginField.getText(), new String(passwordField.getPassword()));
                if(usuario == null){
                    setMessageError("Login ou senha incorreta");
                    return;
                }
                if(Administrador.class.isInstance(usuario)){
                    new TelaInicialAdministrador(sistema, usuario);
                }else if(Eleitor.class.isInstance(usuario)){
                }
                dispose();
            }
        });
    }

    public void setMessageError(String message){
        loginMessage.setText(message);
    }

    private void initComponents(){
        setTitle("Login");
        setSize(800, 400);
        add(rootLogin);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
