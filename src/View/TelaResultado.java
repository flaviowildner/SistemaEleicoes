package View;

import Controller.ControladorEleicao;
import Model.Candidatura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaResultado extends View {
    private JPanel rootResultado;
    private JTable resultadoTable;
    private JButton voltarButton;
    private JButton inícioButton;
    private ControladorEleicao controlador;

    private DefaultTableModel tmodel;

    public TelaResultado(ControladorEleicao refControlador){
        super( "Resultado da Eleição");
        add(rootResultado);

        controlador = refControlador;

        tmodel = (DefaultTableModel)this.resultadoTable.getModel();
        tmodel.addColumn("Nome");
        tmodel.addColumn("Numero");
        tmodel.addColumn("Numero de votos");

        tmodel.setRowCount(0);
        for (Candidatura candidatura : controlador.listarCandidaturas()) {
            tmodel.addRow(new Object[] {candidatura.obterNomeFantasia(), String.valueOf(candidatura.obterNumeroCandidatura()), String.valueOf(candidatura.numeroVotos())});
        }
        tmodel.addRow(new Object[] {"Nulos", "-", controlador.obterNumeroVotosNulos()});
        tmodel.addRow(new Object[] {"Brancos", "-", controlador.obterNumeroVotosBrancos()});

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
