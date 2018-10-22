package View;

import Controller.TelaInicialAdministradorController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicialAdministrador extends JFrame{
    private JButton cadastrarEleitorButton;
    private JButton cadastrarAdministradorButton;
    private JPanel root;
    private JButton deslogarButton;

    private TelaInicialAdministradorController controller;

    public TelaInicialAdministrador(TelaInicialAdministradorController controller) {
        this.controller = controller;
        initComponents();

        cadastrarAdministradorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.solicitaCadastroAdministrador();
            }
        });

        deslogarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.deslogar();
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
