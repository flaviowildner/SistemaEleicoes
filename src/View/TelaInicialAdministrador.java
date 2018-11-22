package View;

import Controller.Sistema;
import Model.Pais;
import Model.UF;
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

    public TelaInicialAdministrador(Sistema sistema, Usuario usuario) {
        super(sistema, usuario, "Tela Inicial Administrador");
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
                new TelaListarUnidadesFederativas(sistema, usuario, Pais.brasil());
                dispose();
            }
        });
    }

    private void initComponents(){
        add(rootTelaInicialAdmin);
    }

    private void solicitarCadastroEleitor(){
        new TelaCadastroEleitor(sistema, usuario);
        dispose();
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