package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListarProcessosEleitorais extends View{
    private JPanel rootPE;
    private JButton inicioButton;
    private JTable processosEleitoraisTable;
    private JButton novoProcessoEleitoralButton;

    private DefaultTableModel tmodel;

    public TelaListarProcessosEleitorais(){
        super( "Listar Processos Eleitorais");
        add(rootPE);

        tmodel = (DefaultTableModel)this.processosEleitoraisTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        for (ProcessoEleitoral pe : Sistema.buscarProcessosEleitorais()) {
            tmodel.addRow(new Object[] {pe});
        }

        processosEleitoraisTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                Sistema.listarEleicoes((ProcessoEleitoral)tmodel.getValueAt(processosEleitoraisTable.getSelectedRow(),0));
            }
        });

        inicioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(usuario instanceof Eleitor){
                    new TelaInicialEleitor();
                }else if(usuario instanceof Administrador){
                    new TelaInicialAdministrador();
                }
                dispose();
            }
        });

        if(usuario instanceof Eleitor){
            novoProcessoEleitoralButton.setVisible(false);
            novoProcessoEleitoralButton.getParent().revalidate();
        }else{
            novoProcessoEleitoralButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    //new TelaCadastrarProcessoEleitoral(usuario);
                    //dispose();
                }
            });
        }
    }
}
