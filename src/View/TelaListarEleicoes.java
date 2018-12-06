package View;

import Controller.ControladorEleicao;
import Controller.ControladorProcessoEleitoral;
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
    private JButton novaEleicaoButton;
    private JButton inicioButton;
    private JButton voltarButton;
    private JLabel nomeLabel;
    private JButton iniciarEleicoesButton;
    private JButton encerrarEleicoesButton;
    private ControladorProcessoEleitoral controlador;

    private DefaultTableModel tmodel;

    public TelaListarEleicoes(ControladorProcessoEleitoral refControlador){
        super("Listar Eleiçoes");
        add(rootListarEleicoes);

        controlador = refControlador;

        nomeLabel.setText(controlador.processo().toString());

        tmodel = (DefaultTableModel)this.eleicoesTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        if(usuario instanceof Eleitor){
            for (Eleicao eleicao : controlador.listarEleicoes()) {
                if(!eleicao.eleitorJaVotou((Eleitor)usuario)){
                    tmodel.addRow(new Object[] {eleicao});
                }
            }
            novaEleicaoButton.setVisible(false);
            novaEleicaoButton.getParent().revalidate();
            iniciarEleicoesButton.setVisible(false);
            iniciarEleicoesButton.getParent().revalidate();
            encerrarEleicoesButton.setVisible(false);
            encerrarEleicoesButton.getParent().revalidate();
        }else {
            for (Eleicao eleicao : controlador.listarEleicoes()) {
                tmodel.addRow(new Object[] {eleicao});
            }
            novaEleicaoButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }
            });
        }

        eleicoesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged (ListSelectionEvent e) {
                ControladorEleicao c = new ControladorEleicao();
                c.setEleicao((Eleicao) tmodel.getValueAt(eleicoesTable.getSelectedRow(), 0));
                c.setProcesso(controlador.processo());
                new TelaEleicao(c);
                dispose();
            }
        });

        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarProcessosEleitorais();
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
        iniciarEleicoesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.iniciarEleicoes();
            }
        });
        encerrarEleicoesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.encerrarEleicoes();
            }
        });
        novaEleicaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaCadastrarEleicao(controlador);
                dispose();
            }
        });
    }
}
