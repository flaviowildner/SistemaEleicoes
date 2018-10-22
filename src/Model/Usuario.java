package Model;

public abstract class Usuario {
    private String id;
    private String nome;
    private String senha;

    public Usuario(String id, String senha, String nome){
        this.id = id;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario autenticar(String senha){
        if(this.senha.equals(senha)){
            return this;
        }else{
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
