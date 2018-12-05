package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicialAdministrador extends View{
    private JButton cadastrarEleitorButton;
    private JButton cadastrarAdministradorButton;
    private JPanel rootTelaInicialAdmin;
    private JButton deslogarButton;
    private JButton listarUfsButton;
    private JButton listarProcessosEleitoraisButton;

    public TelaInicialAdministrador() {
        super( "Tela Inicial Administrador");
        initComponents();

        cadastrarAdministradorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                solicitarCadastroAdministrador();
            }
        });

        cadastrarEleitorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                solicitarCadastroEleitor();
            }
        });

        deslogarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                deslogar();
            }
        });
        listarUfsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.listarUnidadesFederativas();
            }
        });
        listarProcessosEleitoraisButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.listarProcessosEleitorais();
            }
        });
    }

    private void initComponents(){
        add(rootTelaInicialAdmin);
    }

    private void solicitarCadastroEleitor(){
        new TelaCadastroEleitor();
        dispose();
    }

    private void solicitarCadastroAdministrador(){
        new TelaCadastroAdmistrador();
        dispose();
    }

    private void deslogar(){
        new TelaLogin();
        dispose();
    }
}