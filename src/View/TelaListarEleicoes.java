package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListarEleicoes extends View {
    private JPanel rootListarEleicoes;
    private JTable eleicoesTable;
    private JButton novaEleiçãoButton;
    private JButton inicioButton;
    private JButton voltarButton;
    private JLabel nomeLabel;

    private DefaultTableModel tmodel;

    public TelaListarEleicoes(Usuario usuario, ProcessoEleitoral processoEleitoral){
        super(usuario, "Listar Eleiçoes");
        add(rootListarEleicoes);

        nomeLabel.setText(processoEleitoral.toString());

        tmodel = (DefaultTableModel)this.eleicoesTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        for (Eleicao eleicao : processoEleitoral.buscarEleicoes()) {
            tmodel.addRow(new Object[] {eleicao});
        }

        eleicoesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                new TelaCandidatosEleicao(usuario, processoEleitoral, (Eleicao) tmodel.getValueAt(eleicoesTable.getSelectedRow(), 0));
                dispose();
            }
        });

        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarProcessosEleitorais(usuario);
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
    }
}
