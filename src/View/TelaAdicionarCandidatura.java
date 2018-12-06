package View;

import Controller.ControladorEleicao;
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
    private ControladorEleicao controlador;

    public TelaAdicionarCandidatura(ControladorEleicao refControlador){
        super("Adicionar Candidatura - " + refControlador.eleicao().toString());
        add(rootAdicionarCandidatura);

        controlador = refControlador;

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    controlador.adicionarCandidatura(nomeFantasiaField.getText(), Integer.parseInt(numeroField.getText()), cpfField.getText());
                    new TelaEleicao(controlador);
                    dispose();
                } catch (SistemaEleicaoException ex) {
                    setMessageError(ex.getMessage());
                }
            }
        });


        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao(controlador);
                dispose();
            }
        });
    }

    private void setMessageError(String message){
        errorMessageLabel.setText(message);
    }
}
