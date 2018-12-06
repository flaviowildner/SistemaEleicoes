package View;

import Controller.Sistema;
import Model.Estado;
import Model.UF;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.TelaListarUnidadesFederativas.Operacao;

public class TelaCadastrarMunicipio extends View {
    private JButton okButton;
    private JButton cancelarButton;
    private JTextField nomeText;
    private JPanel rootCadastrarMunicipio;

    public TelaCadastrarMunicipio(Estado estado, Operacao operacao) {
        super("Cadastrar Municipio em " + estado.getNome());
        add(rootCadastrarMunicipio);
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.criarMunicipio(nomeText.getText(), estado);
                new TelaListarUnidadesFederativas(estado, operacao);
                dispose();
            }
        });

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(estado, operacao);
                dispose();
            }
        });
    }
}
