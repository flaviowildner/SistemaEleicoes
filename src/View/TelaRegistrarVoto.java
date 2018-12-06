package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaRegistrarVoto extends View {

    private JButton okButton;
    private JButton cancelarButton;
    private JLabel nomeFantasiaLabel;
    private JLabel numeroLabel;
    private JPanel rootRegistrarVoto;

    public TelaRegistrarVoto(ProcessoEleitoral processoEleitoral, Eleicao eleicao, Candidatura candidatura){
        super( "Registrar Voto");
        add(rootRegistrarVoto);

        nomeFantasiaLabel.setText(candidatura.obterNomeFantasia());
        numeroLabel.setText(String.valueOf(candidatura.obterNumeroCandidatura()));

        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao( processoEleitoral, eleicao);
                dispose();
            }
        });
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.registrarVoto();
            }
        });
    }
}
