package View;

import Model.Usuario;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaConfirmar extends View {
    private JPanel rootConfirmar;
    private JButton simButton;
    private JButton naoButton;

    public TelaConfirmar(Usuario usuario) {
        super(usuario, "Confirmar Acao");
        add(rootConfirmar);
        simButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (session != null) session.put("confirmation", true);
                dispose();
            }
        });

        naoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (session != null) session.put("confirmation", false);
                dispose();
            }
        });
    }
}
