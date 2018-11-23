package View;

import Controller.Sistema;
import Model.Municipio;
import Model.UF;
import Model.Usuario;

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

    public TelaCadastrarZonaEleitoral(Usuario usuario, Municipio uf, Operacao operacao, ViewReturn<UF> result) {
        super(usuario, "Cadastrar Zona Eleitoral em " + uf.getNome());
        add(rootCadastrarZonaEleitoral);
        title.setText("Cadastrar Zona Eleitoral em " + uf.getNome());
        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.criarZonaEleitoral(Integer.parseInt(numeroText.getText()), enderecoText.getText(), uf);
                new TelaListarUnidadesFederativas(usuario, uf, operacao, result);
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(usuario, uf, operacao, result);
                dispose();
            }
        });
    }
}
