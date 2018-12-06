package View;

import Controller.ControladorProcessoEleitoral;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCriarProcessoEleitoral extends View {
    private JTextField textNome;
    private JPanel panel1;
    private JButton buttonOk;
    private JButton buttonCancelar;
    private ControladorProcessoEleitoral controlador;

    public TelaCriarProcessoEleitoral() {
        super("Novo Processo Eleitoral");

        add(panel1);

        controlador = new ControladorProcessoEleitoral();

        buttonOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controlador.criarProcessoEleitoral(textNome.getText());
                new TelaListarProcessosEleitorais();
                dispose();
            }
        });

        buttonCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TelaListarProcessosEleitorais();
                dispose();
            }
        });
    }
}
