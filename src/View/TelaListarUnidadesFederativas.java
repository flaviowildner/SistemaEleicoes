package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListarUnidadesFederativas extends View {
    private JTable unidadesFederativasTable;
    private JPanel rootListarUfs;
    private JLabel nomeLabel;
    private JButton voltarButton;
    private JButton inicioButton;
    private JButton novaZonaEleitoralButton;
    private JButton novaSecaoButton;
    private JButton novoEstadoButton;
    private JButton novoMunicipioButton;

    private UF currentUf;
    private DefaultTableModel tmodel;

    public TelaListarUnidadesFederativas(Sistema sistema, Usuario usuario, UF uf) {
        super(sistema, usuario, "Unidade Federativa");

        tmodel = (DefaultTableModel)this.unidadesFederativasTable.getModel();
        currentUf = uf;

        add(rootListarUfs);

        nomeLabel.setText(uf.toString());

        tmodel.addColumn("Sub");
        this.reloadList();

        this.unidadesFederativasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                new TelaListarUnidadesFederativas(sistema, usuario, (UF)tmodel.getValueAt(unidadesFederativasTable.getSelectedRow(), 0));
                dispose();
            }
        });

        inicioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador(sistema, usuario);
                dispose();
            }
        });
        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentUf.getFather() != null) {
                    new TelaListarUnidadesFederativas(sistema, usuario, currentUf.getFather());
                } else {
                    new TelaInicialAdministrador(sistema, usuario);
                }
                dispose();
            }
        });

        novaZonaEleitoralButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarZonaEleitoral(sistema, usuario, currentUf);
                dispose();
            }
        });

        novoEstadoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarEstado();
                dispose();
            }
        });

        novoMunicipioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarMunicipio();
                dispose();
            }
        });

        novaSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarSecao(sistema, usuario, currentUf);
                dispose();
            }
        });

        if (!(currentUf instanceof Municipio)) {
            novaZonaEleitoralButton.setVisible(false);
            novaZonaEleitoralButton.getParent().revalidate();
        }

        if (!(currentUf instanceof ZonaEleitoral)) {
            novaSecaoButton.setVisible(false);
            novaSecaoButton.getParent().revalidate();
        }

        if (!(currentUf instanceof Pais)) {
            novoEstadoButton.setVisible(false);
            novoEstadoButton.getParent().revalidate();
        }

        if (!(currentUf instanceof Estado)) {
            novoMunicipioButton.setVisible(false);
            novoMunicipioButton.getParent().revalidate();
        }


    }

    private void reloadList () {
        tmodel.setRowCount(0);
        for (UF child : currentUf.getChildren()) {
            tmodel.addRow(new Object[] {child});
        }
    }
}
