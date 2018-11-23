package View;

import Controller.Sistema;
import Model.Candidatura;
import Model.Eleicao;
import Model.ProcessoEleitoral;
import Model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaResultado extends View {
    private JPanel rootResultado;
    private JTable resultadoTable;
    private JButton voltarButton;
    private JButton inícioButton;

    private DefaultTableModel tmodel;

    public TelaResultado(Sistema sistema, Usuario usuario, ProcessoEleitoral processoEleitoral, Eleicao eleicao){
        super(sistema, usuario, "Resultado da Eleição");
        add(rootResultado);

        tmodel = (DefaultTableModel)this.resultadoTable.getModel();
        tmodel.addColumn("Nome");
        tmodel.addColumn("Numero");
        tmodel.addColumn("Numero de votos");

        tmodel.setRowCount(0);
        for (Candidatura candidatura : eleicao.buscarCandidaturas()) {
            tmodel.addRow(new Object[] {candidatura.obterNomeFantasia(), String.valueOf(candidatura.obterNumeroCandidatura()), String.valueOf(candidatura.numeroVotos())});
        }

        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaEleicao(sistema, usuario, processoEleitoral, eleicao);
                dispose();
            }
        });
        inícioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador(sistema, usuario);
                dispose();
            }
        });
    }
}