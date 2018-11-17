package Controller;

import Model.*;
import View.TelaLogin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    List<Usuario> usuarioList;
    List<ProcessoEleitoral> processoEleitoralList;
    Pais pais;
    List<UF> ufList;

    //JFrame telaAtual = null;

    public Sistema(){
        usuarioList = new ArrayList<>();
        processoEleitoralList = new ArrayList<>();
        ufList = new ArrayList<>();

        //Administrador default
        Usuario defaultAdministrador = new Administrador("ff", "ff");
        usuarioList.add(defaultAdministrador);

        //Tela Inicial
        TelaLogin telaLogin = new TelaLogin(this);
        //telaAtual = telaLogin;
    }

    public Usuario logar(String login, String senha){
        for(Usuario usuario : usuarioList){
            if(usuario.getLogin().equals(login)){
                if(usuario.autenticar(senha)){
                    return usuario;
                }
            }
        }
        return null;
    }

    public void criarUF(String nome){
        UF uf = new UF(nome);
        ufList.add(uf);
    }

    //CHECAR SE USUARIO EXISTE
    public void cadastrarAdministrador(String login, String senha){
        Administrador administrador;
        administrador = new Administrador(login, senha);
        usuarioList.add(administrador);
    }
}
