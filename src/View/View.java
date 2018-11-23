package View;

import Model.Usuario;

import javax.swing.*;
import java.util.HashMap;

public class View extends JFrame {
    protected Usuario usuario;
    protected HashMap<String, Object> session;
    private Runnable resumeFunction;
    private View halted;

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

    public void useSession () {
        if (this.session == null) this.session = new HashMap<>();
    }

    public void useSession (HashMap<String, Object> session) {
        if (this.session == null) this.session = session;
        else throw new IllegalStateException("Cannot set session for a view that already has a session");
    }
}
