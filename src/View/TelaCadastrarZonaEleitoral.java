package View;

import Controller.Database;
import Model.Municipio;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.TelaListarUnidadesFederativas.Operacao;

public class TelaCadastrarZonaEleitoral extends View {
    private JButton OKButton;
    private JPanel rootCadastrarZonaEleitoral;
    private JButton cancelarButton;
    private JTextField numeroText;
    private JTextField enderecoText;
    private JLabel title;

    public TelaCadastrarZonaEleitoral( Municipio uf, Operacao operacao) {
        super( "Cadastrar Zona Eleitoral em " + uf.getNome());
        add(rootCadastrarZonaEleitoral);
        title.setText("Cadastrar Zona Eleitoral em " + uf.getNome());
        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Database.criarZonaEleitoral(Integer.parseInt(numeroText.getText()), enderecoText.getText(), uf);
                new TelaListarUnidadesFederativas( uf, operacao);
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas( uf, operacao);
                dispose();
            }
        });
    }
}
