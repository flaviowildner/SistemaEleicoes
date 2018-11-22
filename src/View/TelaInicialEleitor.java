package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicialEleitor extends View{
    private JButton buscarProcessosEleitoraisButton;
    private JButton deslogarButton;
    private JPanel rootPanel;

    public TelaInicialEleitor(Sistema sistema, Usuario usuario){
        super(sistema, usuario, "Tela Inicial Eleitor");
        initComponents();

        deslogarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                deslogar();
            }
        });
    }

    private void initComponents(){
        add(rootPanel);
    }

    private void deslogar(){
        new TelaLogin(sistema);
        dispose();
    }
}