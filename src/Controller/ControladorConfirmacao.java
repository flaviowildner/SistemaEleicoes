package Controller;

public class ControladorConfirmacao {

    private boolean _confirmado;

    public ControladorConfirmacao () {
        _confirmado = false;
    }

    public boolean confirmado () {
        return _confirmado;
    }

    public void confirmar (){
        _confirmado = true;
    }

    public void cancelar (){
        _confirmado = false;
    }

}
