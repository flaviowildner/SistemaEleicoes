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

    public enum Operacao {
        NADA, SELECIONAR_UF, SELECIONAR_ESTADO, SELECIONAR_MUNICIPIO, SELECIONAR_ZONAELEITORAL, SELECIONAR_SECAO
    }

    public TelaListarUnidadesFederativas(Usuario usuario, UF uf, Operacao operacao, ViewReturn<UF> result) {
        super(usuario, "Unidade Federativa");

        tmodel = (DefaultTableModel)this.unidadesFederativasTable.getModel();
        currentUf = uf;

        add(rootListarUfs);

        nomeLabel.setText(uf.toString());

        tmodel.addColumn("Sub");
        this.reloadList();

        this.unidadesFederativasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                UF selection = (UF) tmodel.getValueAt(unidadesFederativasTable.getSelectedRow(), 0);
                if (    selection instanceof Estado && operacao == Operacao.SELECIONAR_ESTADO ||
                        selection instanceof Municipio && operacao == Operacao.SELECIONAR_MUNICIPIO ||
                        selection instanceof ZonaEleitoral && operacao == Operacao.SELECIONAR_ZONAELEITORAL ||
                        selection instanceof Secao && operacao == Operacao.SELECIONAR_SECAO
                    ) {
                    ViewReturn<Boolean> b = new ViewReturn<Boolean>();
                    new TelaConfirmar(usuario, b);
                    if (b.getResult()) {
                        result.setResult(selection);
                        dispose();
                    }
                }
                else{
                    new TelaListarUnidadesFederativas(usuario, selection, operacao, result);
                    dispose();
                }
            }
        });

        inicioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador(usuario);
                dispose();
            }
        });
        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentUf.getFather() != null) {
                    new TelaListarUnidadesFederativas(usuario, currentUf.getFather(), operacao, result);
                } else {
                    new TelaInicialAdministrador(usuario);
                }
                dispose();
            }
        });

        novaZonaEleitoralButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarZonaEleitoral(usuario, (Municipio)currentUf, operacao, result);
                dispose();
            }
        });

        novoEstadoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarEstado(usuario, operacao, result);
                dispose();
            }
        });

        novoMunicipioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarMunicipio(usuario, (Estado)currentUf, operacao, result);
                dispose();
            }
        });

        novaSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarSecao(usuario, (ZonaEleitoral)currentUf, operacao, result);
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
