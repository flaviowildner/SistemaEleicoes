package View;

import Controller.Sistema;
import Model.Eleicao;
import Model.Eleitor;
import Model.ProcessoEleitoral;
import Model.Usuario;

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

    public TelaAdicionarCandidatura(Usuario usuario, ProcessoEleitoral processoEleitoral, Eleicao eleicao){
        super(usuario, "Adicionar Candidatura - " + eleicao.toString());
        add(rootAdicionarCandidatura);

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int status = Sistema.adicionarCandidatura(eleicao, nomeFantasiaField.getText(), Integer.parseInt(numeroField.getText()), cpfField.getText());
                if(status == 1){
                    setMessageError("Nome Fantasia já existe");
                }else if(status == 2){
                    setMessageError("Numero de candidatura já existe");
                }else if(status == 3){
                    setMessageError("O eleitor já possui uma candidatura nessa eleição");
                }else if(status == 4){
                    setMessageError("Não existe um eleitor com este CPF");
                }
                else if(status == 0){
                    new TelaEleicao(usuario, processoEleitoral, eleicao);
                    dispose();
                }
            }
        });


        cancelarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao(usuario, processoEleitoral, eleicao);
                dispose();
            }
        });
    }

    private void setMessageError(String message){
        errorMessageLabel.setText(message);
    }
}
