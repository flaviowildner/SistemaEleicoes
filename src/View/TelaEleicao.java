package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaEleicao extends View{
    private JPanel rootEleicao;
    private JTable tableCandidatos;
    private JLabel nomeEleicao;
    private JButton adicionarCandidaturaButton;
    private JButton inicioButton;
    private JButton voltarButton;
    private JButton exibirResultadoButton;

    private DefaultTableModel tmodel;

    public TelaEleicao(Usuario usuario, ProcessoEleitoral processoEleitoral, Eleicao eleicao){
        super(usuario, "Listar Candidatos");
        add(rootEleicao);

        nomeEleicao.setText(processoEleitoral.toString() + " - " + eleicao.toString());

        tmodel = (DefaultTableModel)this.tableCandidatos.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        for (Candidatura candidatura : eleicao.buscarCandidaturas()) {
            tmodel.addRow(new Object[] {candidatura});
        }


        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarEleicoes(usuario, processoEleitoral);
                dispose();
            }
        });
        inicioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(usuario instanceof Eleitor){
                    new TelaInicialEleitor(usuario);
                }else if(usuario instanceof Administrador){
                    new TelaInicialAdministrador(usuario);
                }
                dispose();
            }
        });
        adicionarCandidaturaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaAdicionarCandidatura(usuario, processoEleitoral, eleicao);
                dispose();
            }
        });
    }
}