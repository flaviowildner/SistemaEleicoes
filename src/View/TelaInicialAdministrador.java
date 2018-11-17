package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicialAdministrador extends JFrame{
    private JButton cadastrarEleitorButton;
    private JButton cadastrarAdministradorButton;
    private JPanel rootTelaInicialAdmin;
    private JButton deslogarButton;

    private Sistema sistema;
    private Usuario usuario;

    public TelaInicialAdministrador(Sistema sistema, Usuario usuario) {
        this.sistema = sistema;
        this.usuario = usuario;
        initComponents();

        cadastrarAdministradorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                solicitarCadastroAdministrador();
            }
        });

        deslogarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                deslogar();
            }
        });

    }

    private void initComponents(){
        setTitle("Administrador");
        setSize(800, 400);
        add(rootTelaInicialAdmin);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void solicitarCadastroAdministrador(){
        new TelaCadastroAdmistrador(sistema, usuario);
        dispose();
    }

    private void deslogar(){
        new TelaLogin(sistema);
        dispose();
    }
}