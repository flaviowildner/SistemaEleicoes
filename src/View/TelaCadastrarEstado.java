package View;

import Controller.Sistema;
import Model.UF;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import View.TelaListarUnidadesFederativas.Operacao;

public class TelaCadastrarEstado extends View {
    private JPanel rootCadastrarEstado;
    private JButton okButton;
    private JButton cancelarButton;
    private JTextField nomeText;

    public TelaCadastrarEstado(Usuario usuario, Operacao operacao) {
        super(usuario, "Cadastrar Estado");
        add(rootCadastrarEstado);

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.criarEstado(nomeText.getText());
                new TelaListarUnidadesFederativas(usuario, Sistema.brasil(), operacao);
                dispose();
            }
        });

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarUnidadesFederativas(usuario, Sistema.brasil(), operacao);
                dispose();
            }
        });

    }
}
