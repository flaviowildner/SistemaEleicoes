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
    private JButton novaEleicaoButton;
    private JButton inicioButton;
    private JButton voltarButton;
    private JLabel nomeLabel;
    private JButton iniciarEleicoesButton;
    private JButton encerrarEleicoesButton;

    private DefaultTableModel tmodel;

    public TelaListarEleicoes(Usuario usuario, ProcessoEleitoral processoEleitoral){
        super(usuario, "Listar Eleiçoes");
        add(rootListarEleicoes);

        nomeLabel.setText(processoEleitoral.toString());

        tmodel = (DefaultTableModel)this.eleicoesTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        if(usuario instanceof Eleitor){
            for (Eleicao eleicao : processoEleitoral.buscarEleicoes()) {
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
            for (Eleicao eleicao : processoEleitoral.buscarEleicoes()) {
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
                new TelaEleicao(usuario, processoEleitoral, (Eleicao) tmodel.getValueAt(eleicoesTable.getSelectedRow(), 0));
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
        iniciarEleicoesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.iniciarEleicoes(processoEleitoral);
            }
        });
        encerrarEleicoesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sistema.encerrarEleicoes(processoEleitoral);
            }
        });
    }
}
