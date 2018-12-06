package View;

import Controller.Database;
import Model.Usuario;

import javax.swing.*;

public class View extends JFrame {
    protected Usuario usuario;
    private Runnable resumeFunction;
    private View halted;

    public View(String title){
        usuario = Database.usuarioLogado();
        configView(title);
    }

    public void configView(String title){
        setTitle(title);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void dispose() {
        if (halted != null) {
            if (halted.resumeFunction != null) {
                halted.setVisible(true);
                halted.resumeFunction.run();
            }
        }
        super.dispose();
    }

    public void onResume (Runnable resumeFunction) {
        this.resumeFunction = resumeFunction;
    }

    public void halt (View v) {
        this.halted = v;
        v.setVisible(false);
    }
}
