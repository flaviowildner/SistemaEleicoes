package View;

import Controller.ControladorProcessoEleitoral;
import Controller.Database;
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
    private ControladorProcessoEleitoral controlador;

    private DefaultTableModel tmodel;

    public TelaListarProcessosEleitorais(){
        super( "Listar Processos Eleitorais");
        add(rootPE);

        controlador = new ControladorProcessoEleitoral();

        tmodel = (DefaultTableModel)this.processosEleitoraisTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        for (ProcessoEleitoral pe : controlador.listarProcessosEleitorais()) {
            tmodel.addRow(new Object[] {pe});
        }

        processosEleitoraisTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                controlador.setProcesso((ProcessoEleitoral)tmodel.getValueAt(processosEleitoraisTable.getSelectedRow(),0));
                new TelaListarEleicoes(controlador);
                dispose();
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
                    //new TelaCadastrarProcessoEleitoral();
                    //dispose();
                }
            });
        }
    }
}
