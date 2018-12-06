package View;

import Controller.Sistema;
import Model.Candidatura;
import Model.Eleicao;
import Model.ProcessoEleitoral;
import Model.Usuario;

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

    public TelaCandidatura( ProcessoEleitoral processoEleitoral, Eleicao eleicao, Candidatura candidatura){
        super( "Informações da Candidatura");
        add(rootCandidatura);

        nomeFantasiaField.setText(candidatura.obterNomeFantasia());
        numeroField.setText(String.valueOf(candidatura.obterNumeroCandidatura()));
        qntVotosField.setText(String.valueOf(candidatura.numeroVotos()));


        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao( processoEleitoral, eleicao);
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
