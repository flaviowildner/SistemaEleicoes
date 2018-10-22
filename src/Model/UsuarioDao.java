package Model;

import java.util.HashMap;

public class UsuarioDao {
    private static UsuarioDao dao = null;
    private HashMap<String, Usuario> listaUsuario;

    private UsuarioDao(){}

    public static UsuarioDao getInstance(){
        if(dao == null){
            dao = new UsuarioDao();
            dao.listaUsuario = new HashMap<>();
        }
        return dao;
    }

    public void salvarUsuario(Usuario usuario){
        this.listaUsuario.put(usuario.getId(), usuario);
    }

    public Usuario buscarUsuario(String id){
        return listaUsuario.get(id);
    }
}
