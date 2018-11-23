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

    public TelaCadastrarMunicipio(Usuario usuario, Estado estado, Operacao operacao, ViewReturn<UF> result) {
        super(usuario, "Cadastrar Municipio em " + estado.getNome());
        add(rootCadastrarMunicipio);
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.criarMunicipio(nomeText.getText(), estado);
                new TelaListarUnidadesFederativas(usuario, estado, operacao, result);
                dispose();
            }
        });

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(usuario, estado, operacao, result);
                dispose();
            }
        });
    }
}
