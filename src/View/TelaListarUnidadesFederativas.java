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

    public TelaListarUnidadesFederativas(Usuario usuario, UF uf, Operacao operacao) {
        super(usuario, "Unidade Federativa");
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
                    View v = new TelaConfirmar(usuario);
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
                new TelaInicialAdministrador(usuario);
                dispose();
            }
        });
        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentUf.getFather() != null) {
                    new TelaListarUnidadesFederativas(usuario, currentUf.getFather(), operacao);
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
                new TelaCadastrarZonaEleitoral(usuario, (Municipio)currentUf, operacao);
                dispose();
            }
        });

        novoEstadoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarEstado(usuario, operacao);
                dispose();
            }
        });

        novoMunicipioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarMunicipio(usuario, (Estado)currentUf, operacao);
                dispose();
            }
        });

        novaSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarSecao(usuario, (ZonaEleitoral)currentUf, operacao);
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
}
