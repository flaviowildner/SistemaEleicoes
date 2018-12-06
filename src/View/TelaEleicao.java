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

public class TelaEleicao extends View{
    private JPanel rootEleicao;
    private JTable candidatosTable;
    private JLabel nomeEleicao;
    private JButton adicionarCandidaturaButton;
    private JButton inicioButton;
    private JButton voltarButton;
    private JButton exibirResultadoButton;
    private ControladorEleicao controlador;

    private DefaultTableModel tmodel;

    public TelaEleicao(ControladorEleicao refControladorEleicao){
        super( "Listar Candidatos");
        add(rootEleicao);

        controlador = refControladorEleicao;

        nomeEleicao.setText(controlador.processo().toString() + " - " + controlador.eleicao().toString());

        tmodel = (DefaultTableModel)this.candidatosTable.getModel();
        tmodel.addColumn("Sub");

        tmodel.setRowCount(0);
        for (Candidatura candidatura : controlador.listarCandidaturas()) {
            tmodel.addRow(new Object[] {candidatura});
        }

        voltarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ControladorProcessoEleitoral c = new ControladorProcessoEleitoral();
                c.setProcesso(controlador.processo());
                new TelaListarEleicoes(c);
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

        adicionarCandidaturaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaAdicionarCandidatura(controlador);
                dispose();
            }
        });

        exibirResultadoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaResultado(controlador);
                dispose();
            }
        });

        if(usuario instanceof Eleitor){

            candidatosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged (ListSelectionEvent e) {
                    if(controlador.eleitorJaVotou((Eleitor)usuario)){
                        ControladorProcessoEleitoral c = new ControladorProcessoEleitoral();
                        c.setProcesso(controlador.processo());
                        new TelaListarEleicoes(c);
                    }else{
                        controlador.setCandidatura((Candidatura) tmodel.getValueAt(candidatosTable.getSelectedRow(), 0));
                        new TelaRegistrarVoto(controlador);
                    }
                    dispose();
                }
            });

            adicionarCandidaturaButton.setVisible(false);
            adicionarCandidaturaButton.getParent().revalidate();
            exibirResultadoButton.setVisible(false);
            exibirResultadoButton.getParent().revalidate();
        }
    }
}
