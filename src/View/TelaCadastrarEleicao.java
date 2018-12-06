package View;

import Controller.ControladorEleicao;
import Controller.ControladorProcessoEleitoral;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarEleicao extends View {
    private JPanel rootCriarEleicao;
    private JTextField nomeField;
    private JButton cancelarButton;
    private JButton okButton;
    private ControladorEleicao controlador;

    public TelaCadastrarEleicao(ControladorProcessoEleitoral controladorProcessoEleitoral){
        super("Cadastrar Eleição");
        add(rootCriarEleicao);
        controlador = new ControladorEleicao();



        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarEleicao(nomeField.getText(), null, controladorProcessoEleitoral.processo());
                new TelaListarEleicoes(controladorProcessoEleitoral);
                dispose();
            }
        });

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
