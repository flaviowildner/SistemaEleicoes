package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroEleitor extends View{
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField cpfField;
    private JButton cadastrarEleitorButton;
    private JPanel rootPanel;
    private JTextField nomeField;
    private JButton voltar;

    public TelaCadastroEleitor(Usuario usuario){
        super(usuario, "Cadastro Eleitor");
        initComponents();

        cadastrarEleitorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.criarEleitor(loginField.getText(), new String(passwordField.getPassword()), cpfField.getText(), nomeField.getText(), null);
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
        new TelaInicialAdministrador(usuario);
        dispose();
    }

    private void initComponents(){
        add(rootPanel);
    }
}
