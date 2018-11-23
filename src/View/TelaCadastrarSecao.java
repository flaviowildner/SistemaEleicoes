package View;

import Controller.Sistema;
import Model.UF;
import Model.Usuario;
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

    public TelaCadastrarSecao (Usuario usuario, ZonaEleitoral uf, Operacao operacao, ViewReturn<UF> result) {
        super(usuario, "Cadastrar Secao");
        add(rootCadastrarSecao);

        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.criarSecao(Integer.parseInt(numeroText.getText()), uf);
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
