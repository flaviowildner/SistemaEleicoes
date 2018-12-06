package View;

import Controller.ControladorCandidatura;
import Model.Eleicao;
import Model.ProcessoEleitoral;
import Model.SistemaEleicaoException;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaAdicionarCandidatura extends View {
    private JTextField nomeFantasiaField;
    private JTextField numeroField;
    private JTextField cpfField;
    private JButton okButton;
    private JButton cancelarButton;
    private JPanel rootAdicionarCandidatura;
    private JLabel errorMessageLabel;
    private ControladorCandidatura controlador;

    public TelaAdicionarCandidatura(ProcessoEleitoral processoEleitoral, Eleicao eleicao){
        super("Adicionar Candidatura - " + eleicao.toString());
        add(rootAdicionarCandidatura);

        this.controlador = new ControladorCandidatura();

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    controlador.adicionarCandidatura(eleicao, nomeFantasiaField.getText(), Integer.parseInt(numeroField.getText()), cpfField.getText());
                } catch (SistemaEleicaoException ex) {
                    setMessageError(ex.getMessage());
                }
                dispose();
            }
        });


        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao(processoEleitoral, eleicao);
                dispose();
            }
        });
    }

    private void setMessageError(String message){
        errorMessageLabel.setText(message);
    }
}
