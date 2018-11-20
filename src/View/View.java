package View;

import Controller.Sistema;
import Model.Usuario;

import javax.swing.*;

public class View extends JFrame {
    Sistema sistema;
    Usuario usuario;

    public View(Sistema sistema, Usuario usuario, String title){
        this.sistema = sistema;
        this.usuario = usuario;
        configView(title);
    }

    public void configView(String title){
        setTitle(title);
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
