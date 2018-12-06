package View;

import Controller.ControladorCadastroEleitor;
import Controller.ControladorUnidadesFederativas;
import Model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroEleitor extends View{
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField cpfField;
    private JButton cadastrarEleitorButton;
    private JPanel rootPanel;
    private JTextField nomeField;
    private JButton voltar;
    private JTextField secaoField;
    private JButton selecionarSecaoButton;
    private ControladorCadastroEleitor controlador;

    public TelaCadastroEleitor(){
        super( "Cadastro Eleitor");
        add(rootPanel);

        controlador = new ControladorCadastroEleitor();

        cadastrarEleitorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarEleitor(loginField.getText(), new String(passwordField.getPassword()), cpfField.getText(), nomeField.getText());
                new TelaInicialAdministrador();
                dispose();

            }
        });
        voltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaInicialAdministrador();
                dispose();
            }
        });

        selecionarSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                ControladorUnidadesFederativas c = new ControladorUnidadesFederativas();
                c.setOperacao(ControladorUnidadesFederativas.Operacao.SELECIONAR_SECAO);

                onResume (() -> {
                    UF uf = c.ufSelecionada();
                    if (uf != null) {
                        if (uf instanceof Secao) {
                            Secao secao = (Secao)uf;
                            ZonaEleitoral ze = secao.getZonaEleitoral();
                            Municipio m = ze.getMunicipio();
                            Estado es = m.getEstado();
                            secaoField.setText(es.toString()+", "+m.toString()+", "+ze.toString()+", "+secao.toString());
                            controlador.setSecao(secao);
                        }
                    }
                });

                View v = new TelaListarUnidadesFederativas(c);
                v.halt(TelaCadastroEleitor.this);
            }
        });
    }
}
