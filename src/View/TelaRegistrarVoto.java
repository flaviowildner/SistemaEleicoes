package View;

import Controller.ControladorEleicao;
import Controller.ControladorProcessoEleitoral;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaRegistrarVoto extends View {

    private JButton okButton;
    private JButton cancelarButton;
    private JLabel nomeFantasiaLabel;
    private JLabel numeroLabel;
    private JPanel rootRegistrarVoto;
    private ControladorEleicao controlador;

    public TelaRegistrarVoto(ControladorEleicao refControlador){
        super( "Registrar Voto");
        add(rootRegistrarVoto);

        controlador = refControlador;

        nomeFantasiaLabel.setText(controlador.candidatura().obterNomeFantasia());
        numeroLabel.setText(String.valueOf(controlador.candidatura().obterNumeroCandidatura()));

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao(controlador);
                dispose();
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.registrarVoto();
                ControladorProcessoEleitoral c = new ControladorProcessoEleitoral();
                c.setProcesso(controlador.processo());
                new TelaListarEleicoes(c);
                dispose();
            }
        });
    }
}
