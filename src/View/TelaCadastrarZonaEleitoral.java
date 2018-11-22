package View;

import Controller.Sistema;
import Model.Municipio;
import Model.UF;
import Model.Usuario;

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

    public TelaCadastrarZonaEleitoral(Sistema sistema, Usuario usuario, UF uf) {
        super(sistema, usuario, "Cadastrar Zona Eleitoral em " + uf.getNome());
        add(rootCadastrarZonaEleitoral);
        title.setText("Cadastrar Zona Eleitoral em " + uf.getNome());
        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sistema.criarZonaEleitoral(Integer.parseInt(numeroText.getText()), enderecoText.getText(), (Municipio)uf);
                new TelaListarUnidadesFederativas(sistema, usuario, uf);
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(sistema, usuario, uf);
                dispose();
            }
        });
    }
}
