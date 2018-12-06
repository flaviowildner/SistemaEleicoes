package View;

import Controller.ControladorConfirmacao;
import Controller.ControladorUnidadesFederativas;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
    private ControladorUnidadesFederativas controlador;

    private DefaultTableModel tmodel;

    public TelaListarUnidadesFederativas(ControladorUnidadesFederativas refControlador) {
        super("Unidade Federativa");
        add(rootListarUfs);

        controlador = (refControlador == null) ? new ControladorUnidadesFederativas() : refControlador;

        tmodel = (DefaultTableModel)this.unidadesFederativasTable.getModel();
        tmodel.addColumn("Sub");

        this.unidadesFederativasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                UF selection = (UF)tmodel.getValueAt(unidadesFederativasTable.rowAtPoint(e.getPoint()), 0);
                if (controlador.selecionarUf(selection)) {
                    ControladorConfirmacao c = new ControladorConfirmacao();
                    View v = new TelaConfirmar(c);

                    onResume(() -> {
                        if (c.confirmado()) {
                            TelaListarUnidadesFederativas.this.controlador.selecionarUf(selection);
                            dispose();
                        }
                    });

                    v.halt(TelaListarUnidadesFederativas.this);
                }
                else {
                    refresh();
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
                if (controlador.ufAnterior() != null) {
                    controlador.selecionarUf(controlador.ufAnterior());
                    new TelaListarUnidadesFederativas(controlador);
                } else {
                    new TelaInicialAdministrador();
                }
                dispose();
            }
        });

        novaZonaEleitoralButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarZonaEleitoral(controlador);
                dispose();
            }
        });

        novoEstadoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarEstado(controlador);
                dispose();
            }
        });

        novoMunicipioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarMunicipio(controlador);
                dispose();
            }
        });

        novaSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarSecao(controlador);
                dispose();
            }
        });

        selecionarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });

        if (controlador.operacao() != ControladorUnidadesFederativas.Operacao.SELECIONAR_UF) {
            selecionarButton.setVisible(false);
            selecionarButton.getParent().revalidate();
        }

        this.refresh();
    }

    private boolean reloadList () {
        List<UF> proximasUfs = controlador.proximasUfs();
        if (proximasUfs != null) {
            this.tmodel.setRowCount(0);
            for (UF child : proximasUfs) {
                this.tmodel.addRow(new Object[]{child});
            }
            return true;
        }
        return false;
    }

    private void refresh () {
        if (reloadList()) {
            nomeLabel.setText(controlador.ufSelecionada().toString());

            novaZonaEleitoralButton.setVisible(controlador.ufSelecionada() instanceof Municipio);
            novaZonaEleitoralButton.getParent().revalidate();

            novaSecaoButton.setVisible(controlador.ufSelecionada() instanceof ZonaEleitoral);
            novaSecaoButton.getParent().revalidate();

            novoEstadoButton.setVisible(controlador.ufSelecionada() instanceof Pais);
            novoEstadoButton.getParent().revalidate();

            novoMunicipioButton.setVisible(controlador.ufSelecionada() instanceof Estado);
            novoMunicipioButton.getParent().revalidate();
        }
    }
}
