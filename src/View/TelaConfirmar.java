package View;

import Controller.ControladorConfirmacao;
import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaConfirmar extends View {
    private JPanel rootConfirmar;
    private JButton simButton;
    private JButton naoButton;
    private ControladorConfirmacao controlador;

    public TelaConfirmar(ControladorConfirmacao controlador) {
        super("Confirmar Acao");
        add(rootConfirmar);

        this.controlador = controlador;

        simButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaConfirmar.this.controlador.confirmar();
                dispose();
            }
        });

        naoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaConfirmar.this.controlador.cancelar();
                dispose();
            }
        });
    }
}
