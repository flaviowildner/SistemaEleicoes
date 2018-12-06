package View;

import Controller.ControladorUnidadesFederativas;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarZonaEleitoral extends View {
    private JButton OKButton;
    private JPanel rootCadastrarZonaEleitoral;
    private JButton cancelarButton;
    private JTextField numeroText;
    private JTextField enderecoText;
    private JLabel title;
    private ControladorUnidadesFederativas controlador;

    public TelaCadastrarZonaEleitoral(ControladorUnidadesFederativas refControlador) {
        super( "Cadastrar Zona Eleitoral em " + refControlador.ufSelecionada().getNome());
        add(rootCadastrarZonaEleitoral);
        title.setText("Cadastrar Zona Eleitoral em " + refControlador.ufSelecionada().getNome());

        controlador = (refControlador == null) ? new ControladorUnidadesFederativas() : refControlador;

        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarZonaEleitoral(Integer.parseInt(numeroText.getText()), enderecoText.getText());
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
