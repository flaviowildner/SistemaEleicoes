package View;

import Controller.Database;
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

    public TelaCadastroEleitor(){
        super( "Cadastro Eleitor");
        initComponents();

        cadastrarEleitorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Secao secao = null;
                if (session != null) {
                    secao = (Secao)session.get("uf");
                }
                if (secao != null) {
                    Database.criarEleitor(loginField.getText(), new String(passwordField.getPassword()), cpfField.getText(), nomeField.getText(), secao);
                    voltarTelaInicialAdministrador();
                }

            }
        });
        voltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                voltarTelaInicialAdministrador();
            }
        });
        selecionarSecaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                useSession();
                onResume (() -> {
                    Object o = session.get("uf");
                    if (o != null) {
                        if (o instanceof Secao) {
                            Secao secao = (Secao)o;
                            ZonaEleitoral ze = secao.getZonaEleitoral();
                            Municipio m = ze.getMunicipio();
                            Estado es = m.getEstado();
                            secaoField.setText(es.toString()+", "+m.toString()+", "+ze.toString()+", "+secao.toString());
                        }
                    }
                });
                View v = new TelaListarUnidadesFederativas( Database.brasil(), TelaListarUnidadesFederativas.Operacao.SELECIONAR_SECAO);
                v.useSession(session);
                v.halt(TelaCadastroEleitor.this);
            }
        });
    }

    private void voltarTelaInicialAdministrador(){
        new TelaInicialAdministrador();
        dispose();
    }

    private void initComponents(){
        add(rootPanel);
    }
}
