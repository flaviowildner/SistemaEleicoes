package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaEleicao extends View{
    private JPanel rootEleicao;
    private JTable candidatosTable;
    private JLabel nomeEleicao;
    private JButton adicionarCandidaturaButton;
    private JButton inicioButton;
    private JButton voltarButton;
    private JButton exibirResultadoButton;

    private DefaultTableModel tmodel;

    public TelaEleicao(Sistema sistema, Usuario usuario, ProcessoEleitoral processoEleitoral, Eleicao eleicao){
        super(sistema, usuario, "Listar Candidatos");
        add(rootEleicao);

        nomeEleicao.setText(processoEleitoral.toString() + " - " + eleicao.toString());

        tmodel = (DefaultTableModel)this.candidatosTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        for (Candidatura candidatura : eleicao.buscarCandidaturas()) {
            tmodel.addRow(new Object[] {candidatura});
        }

        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarEleicoes(sistema, usuario, processoEleitoral);
                dispose();
            }
        });
        inicioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(usuario instanceof Eleitor){
                    new TelaInicialEleitor(sistema, usuario);
                }else if(usuario instanceof Administrador){
                    new TelaInicialAdministrador(sistema, usuario);
                }
                dispose();
            }
        });
        if(usuario instanceof Eleitor){
            candidatosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged (ListSelectionEvent e) {
                    if(eleicao.eleitorJaVotou((Eleitor)usuario)){
                        new TelaListarEleicoes(sistema, usuario, processoEleitoral);
                    }else{
                        new TelaRegistrarVoto(sistema, usuario, processoEleitoral, eleicao, (Candidatura) tmodel.getValueAt(candidatosTable.getSelectedRow(), 0));
                    }
                    dispose();
                }
            });
            adicionarCandidaturaButton.setVisible(false);
            adicionarCandidaturaButton.getParent().revalidate();
            exibirResultadoButton.setVisible(false);
            exibirResultadoButton.getParent().revalidate();
        }else {
            candidatosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged (ListSelectionEvent e) {
                    new TelaCandidatura(sistema, usuario, processoEleitoral, eleicao, (Candidatura) tmodel.getValueAt(candidatosTable.getSelectedRow(), 0));
                    dispose();
                }
            });
            adicionarCandidaturaButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    new TelaAdicionarCandidatura(sistema, usuario, processoEleitoral, eleicao);
                    dispose();
                }
            });
            exibirResultadoButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }
            });
        }
    }
}
