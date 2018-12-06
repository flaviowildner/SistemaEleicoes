package View;

import Controller.Database;
import Model.ZonaEleitoral;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.TelaListarUnidadesFederativas.Operacao;

public class TelaCadastrarSecao extends View {

    private JTextField numeroText;
    private JPanel rootCadastrarSecao;
    private JButton OKButton;
    private JButton cancelarButton;

    public TelaCadastrarSecao ( ZonaEleitoral uf, Operacao operacao) {
        super("Cadastrar Secao");
        add(rootCadastrarSecao);

        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Database.criarSecao(Integer.parseInt(numeroText.getText()), uf);
                new TelaListarUnidadesFederativas(uf, operacao);
                dispose();
            }
        });
        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(uf, operacao);
                dispose();
            }
        });
    }
}
