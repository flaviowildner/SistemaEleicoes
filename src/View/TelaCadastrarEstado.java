package View;

import Controller.ControladorUnidadesFederativas;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarEstado extends View {
    private JPanel rootCadastrarEstado;
    private JButton okButton;
    private JButton cancelarButton;
    private JTextField nomeText;
    private ControladorUnidadesFederativas controlador;

    public TelaCadastrarEstado(ControladorUnidadesFederativas refControlador) {
        super("Cadastrar Estado");
        add(rootCadastrarEstado);

        controlador = refControlador;

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarEstado(nomeText.getText());
                new TelaListarUnidadesFederativas(controlador);
                dispose();
            }
        });

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(controlador);
                dispose();
            }
        });

    }
}
