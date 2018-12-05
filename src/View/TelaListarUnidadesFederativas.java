package View;

import Controller.Sistema;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

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
    private JButton selecionarButton;

    private UF currentUf;
    private DefaultTableModel tmodel;

    public enum Operacao {
        NADA, SELECIONAR_UF, SELECIONAR_ESTADO, SELECIONAR_MUNICIPIO, SELECIONAR_ZONAELEITORAL, SELECIONAR_SECAO
    }

    public TelaListarUnidadesFederativas(UF uf, Operacao operacao) {
        super("Unidade Federativa");
        add(rootListarUfs);

        tmodel = (DefaultTableModel)this.unidadesFederativasTable.getModel();
        tmodel.addColumn("Sub");

        this.unidadesFederativasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                UF selection = (UF)tmodel.getValueAt(unidadesFederativasTable.rowAtPoint(e.getPoint()), 0);
                if (    selection instanceof Estado && operacao == Operacao.SELECIONAR_ESTADO ||
                        selection instanceof Municipio && operacao == Operacao.SELECIONAR_MUNICIPIO ||
                        selection instanceof ZonaEleitoral && operacao == Operacao.SELECIONAR_ZONAELEITORAL ||
                        selection instanceof Secao && operacao == Operacao.SELECIONAR_SECAO
                    ) {
                    View v = new TelaConfirmar();
                    useSession();
                    v.useSession(session);
                    onResume(() -> {
                        Boolean confirmed = (Boolean)session.get("confirmation");
                        if (confirmed) {
                            session.put("uf", selection);
                            dispose();
                        }
                    });
                    v.halt(TelaListarUnidadesFederativas.this);
                }
                else {
                    refresh(selection);
                }
            }
        });

        inicioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador();
                dispose();
            }
        });
        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentUf.getFather() != null) {
                    refresh(currentUf.getFather());
                } else {
                    new TelaInicialAdministrador();
                    dispose();
                }
            }
        });

        novaZonaEleitoralButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarZonaEleitoral( (Municipio)currentUf, operacao);
                dispose();
            }
        });

        novoEstadoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarEstado( operacao);
                dispose();
            }
        });

        novoMunicipioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarMunicipio( (Estado)currentUf, operacao);
                dispose();
            }
        });

        novaSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarSecao( (ZonaEleitoral)currentUf, operacao);
                dispose();
            }
        });

        selecionarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (session != null) session.put("uf", currentUf);
                dispose();
            }
        });

        if (operacao != Operacao.SELECIONAR_UF) {
            selecionarButton.setVisible(false);
            selecionarButton.getParent().revalidate();
        }

        this.refresh(uf);
    }

    private void reloadList () {
        this.tmodel.setRowCount(0);
        for (UF child : this.currentUf.getChildren()) {
            this.tmodel.addRow(new Object[] {child});
        }
    }

    private void refresh (UF uf) {
        this.currentUf = uf;
        nomeLabel.setText(this.currentUf.toString());
        this.reloadList();

        novaZonaEleitoralButton.setVisible(currentUf instanceof Municipio);
        novaZonaEleitoralButton.getParent().revalidate();

        novaSecaoButton.setVisible(currentUf instanceof ZonaEleitoral);
        novaSecaoButton.getParent().revalidate();

        novoEstadoButton.setVisible(currentUf instanceof Pais);
        novoEstadoButton.getParent().revalidate();

        novoMunicipioButton.setVisible(currentUf instanceof Estado);
        novoMunicipioButton.getParent().revalidate();

    }
}
