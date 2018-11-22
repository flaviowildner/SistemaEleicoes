package View;

import Controller.Sistema;
import Model.UF;
import Model.Usuario;
import Model.ZonaEleitoral;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarSecao extends View {

    private JTextField numeroText;
    private JPanel rootCadastrarSecao;
    private JButton OKButton;
    private JButton cancelarButton;

    public TelaCadastrarSecao (Sistema sistema, Usuario usuario, UF uf) {
        super(sistema, usuario, "Cadastrar Secao");
        add(rootCadastrarSecao);

        OKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sistema.criarSecao(Integer.parseInt(numeroText.getText()), (ZonaEleitoral)uf);
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
