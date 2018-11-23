package View;

import Model.Usuario;

import javax.swing.*;

public class View extends JFrame {
    Usuario usuario;

    public View(Usuario usuario, String title){
        this.usuario = usuario;
        configView(title);
    }

    public void configView(String title){
        setTitle(title);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
