package View;

import Controller.ControladorEleicao;
import Controller.ControladorProcessoEleitoral;
import Model.Candidatura;
import Model.Eleicao;
import Model.ProcessoEleitoral;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCandidatura extends View {
    private JPanel rootCandidatura;
    private JButton voltarButton;
    private JButton inícioButton;
    private JLabel nomeFantasiaField;
    private JLabel numeroField;
    private JLabel qntVotosField;
    private ControladorEleicao controlador;

    public TelaCandidatura(ControladorEleicao refControladorEleicao) {
        super( "Informações da Candidatura");
        add(rootCandidatura);

        controlador = refControladorEleicao;

        nomeFantasiaField.setText(controlador.candidatura().obterNomeFantasia());
        numeroField.setText(String.valueOf(controlador.candidatura().obterNumeroCandidatura()));
        qntVotosField.setText(String.valueOf(controlador.candidatura().numeroVotos()));


        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao(controlador);
                dispose();
            }
        });
        inícioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador();
                dispose();
            }
        });
    }
}
