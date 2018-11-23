package Model;

public class Usuario {
    private String login;
    private String senha;

    public Usuario(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public Boolean autenticar(String senha){
        if(this.senha.equals(senha)){
            return true;
        }else{
            return false;
        }
    }

    public String getLogin() {
        return login;
    }
}
