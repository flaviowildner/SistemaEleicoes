package View;

import Controller.ControladorUnidadesFederativas;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarMunicipio extends View {
    private JButton okButton;
    private JButton cancelarButton;
    private JTextField nomeText;
    private JPanel rootCadastrarMunicipio;
    private ControladorUnidadesFederativas controlador;

    public TelaCadastrarMunicipio(ControladorUnidadesFederativas refControlador) {
        super("Cadastrar Municipio em " + refControlador.ufSelecionada().getNome());
        add(rootCadastrarMunicipio);

        controlador = refControlador;

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarMunicipio(nomeText.getText());
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
