package View;

import Controller.ControladorUnidadesFederativas;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarSecao extends View {

    private JTextField numeroText;
    private JPanel rootCadastrarSecao;
    private JButton OKButton;
    private JButton cancelarButton;
    private ControladorUnidadesFederativas controlador;

    public TelaCadastrarSecao (ControladorUnidadesFederativas refControlador) {
        super("Cadastrar Secao");
        add(rootCadastrarSecao);

        controlador = refControlador;

        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarSecao(Integer.parseInt(numeroText.getText()));
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
