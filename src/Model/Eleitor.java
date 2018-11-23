package Model;

public class Eleitor extends Usuario {
    private String cpf;
    private String nome;
    private Secao secao;

    public Eleitor(String login, String senha, String cpf, String nome, Secao secao){
        super(login, senha);
        this.cpf = cpf;
        this.nome = nome;
        this.secao = secao;
    }

    public Secao obterSecao(){
        return this.secao;
    }

    public String obterCPF(){return this.cpf;}

    public void alterarSecao(Secao secao){
        this.secao = secao;
    }
}
