package Controller;

import Model.*;
import View.TelaLogin;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    List<Usuario> usuarioList;
    List<ProcessoEleitoral> processoEleitoralList;
    List<Cargo> cargoList;
    Pais pais;

    //JFrame telaAtual = null;

    public Sistema(){
        usuarioList = new ArrayList<>();
        processoEleitoralList = new ArrayList<>();
        cargoList = new ArrayList<>();

        //Administrador default
        criarAdministrador("admin", "admin");
        //PE teste
        criarProcessoEleitoral("Eleições 2018");
        criarProcessoEleitoral("Eleições 2020");

        pais = Pais.brasil();

        criarCargo(Posicao.PRESIDENTE, pais);
        criarCargo(Posicao.GOVERNADOR, pais.getChildren().get(0));
        criarCargo(Posicao.PREFEITO, pais.getChildren().get(0).getChildren().get(0));

        criarEleicao("Presidente", cargoList.get(0), processoEleitoralList.get(0));
        criarEleicao("Governador", cargoList.get(1), processoEleitoralList.get(0));

        criarEleicao("Prefeito", cargoList.get(2), processoEleitoralList.get(1));


        TelaLogin telaLogin = new TelaLogin(this);
        telaLogin.setVisible(true);

/*
        criarPais("Brasil");
        criarEstado("RJ", pais);
        criarMunicipio("SG", pais.obterEstados().get(0));
        criarZonaEleitoral(10, "CN", pais.obterEstados().get(0).obterMunicipios().get(0));
        criarSecao(50, pais.obterEstados().get(0).obterMunicipios().get(0).obterZonasEleitorais().get(0));
        criarSecao(100, pais.obterEstados().get(0).obterMunicipios().get(0).obterZonasEleitorais().get(0));

        Secao secaoEleitor = pais.obterEstados().get(0).obterMunicipios().get(0).obterZonasEleitorais().get(0).obterSecoes().get(0);

        //Eleitores
        criarEleitor("fulano", "123", "123456789", "Fulano", secaoEleitor);
        criarEleitor("ciclano", "123", "123456789", "Ciclano", secaoEleitor);
        criarEleitor("beltrano", "123", "123456789", "Beltrano", secaoEleitor);

        criarCargo(Posicao.PRESIDENTE, obterPais());

        //Candidatura
        criarProcessoEleitoral("2018");
        processoEleitoralList.get(0).criarEleicao("Presidente", cargoList.get(0));
        processoEleitoralList.get(0).buscarEleicoes().get(0).adicionarCandidatura("Candidato 1", 1, (Eleitor)usuarioList.get(1));
        processoEleitoralList.get(0).buscarEleicoes().get(0).adicionarCandidatura("Candidato 2", 2, (Eleitor)usuarioList.get(2));

        processoEleitoralList.get(0).buscarEleicoes().get(0).iniciarEleição();

        //VOTOS
        //Votos no coxinha
        processoEleitoralList.get(0).buscarEleicoes().get(0).registrarVoto((Eleitor)usuarioList.get(1), processoEleitoralList.get(0).buscarEleicoes().get(0).buscarCandidaturas().get(0));
        //processoEleitoralList.get(0).buscarEleicoes().get(0).registrarVoto((Eleitor)usuarioList.get(3), processoEleitoralList.get(0).buscarEleicoes().get(0).buscarCandidaturas().get(0));
        //Votos no mortadela
        processoEleitoralList.get(0).buscarEleicoes().get(0).registrarVoto((Eleitor)usuarioList.get(3), processoEleitoralList.get(0).buscarEleicoes().get(0).buscarCandidaturas().get(1));
        processoEleitoralList.get(0).buscarEleicoes().get(0).registrarVoto((Eleitor)usuarioList.get(3), processoEleitoralList.get(0).buscarEleicoes().get(0).buscarCandidaturas().get(1));



        System.out.println("Numero votos Candidato 1: " + processoEleitoralList.get(0).buscarEleicoes().get(0).buscarCandidaturas().get(0).numeroVotos());
        System.out.println("Numero de votos Candidato 2: " + processoEleitoralList.get(0).buscarEleicoes().get(0).buscarCandidaturas().get(1).numeroVotos());
        System.out.println("Vencedor: " + processoEleitoralList.get(0).buscarEleicoes().get(0).obterVencedor().obterNomeFantasia());
*/
        //Tela Inicial
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

    //CHECAR SE USUARIO EXISTE
    public void criarAdministrador(String login, String senha){
        Administrador administrador;
        administrador = new Administrador(login, senha);
        usuarioList.add(administrador);
    }

    public void criarEleitor(String login, String senha, String cpf, String nome, Secao secao){
        Eleitor eleitor = new Eleitor(login, senha, cpf, nome, secao);
        usuarioList.add(eleitor);
    }

    public void criarProcessoEleitoral(String nome){
        ProcessoEleitoral processoEleitoral = new ProcessoEleitoral(nome);
        processoEleitoralList.add(processoEleitoral);
    }

    public void criarEleicao(String nome, Cargo cargo, ProcessoEleitoral processoEleitoral){
        processoEleitoral.criarEleicao(nome, cargo);
    }

    public List<ProcessoEleitoral> buscarProcessosEleitorais(){
        return this.processoEleitoralList;
    }

    public List<Eleicao> buscarEleicoes(ProcessoEleitoral processoEleitoral){
        return processoEleitoral.buscarEleicoes();
    }

    public List<Usuario> buscarEleitor(){
        List<Usuario> retornoEleitor = new ArrayList<>();
        for(Usuario usuario : usuarioList){
            if(usuario instanceof Eleitor){
                retornoEleitor.add(usuario);
            }
        }
        return retornoEleitor;
    }

    public List<Candidatura> buscarCandidatura(){
        List<Candidatura> retornoCandidatura = new ArrayList<>();
        for(ProcessoEleitoral processoEleitoral : processoEleitoralList){
            for(Eleicao eleicao : processoEleitoral.buscarEleicoes()){
                for(Candidatura candidatura : eleicao.buscarCandidaturas()){
                    retornoCandidatura.add(candidatura);
                }
            }
        }
        return retornoCandidatura;
    }

    public void criarCargo(Posicao posicao, UF uf){
        Cargo cargo = new Cargo(posicao, uf);
        cargoList.add(cargo);
    }

    /*
    public void criarPais(String nome){
        Pais pais = new Pais(nome);
        this.pais = pais;
    }

    public void criarEstado(String nome, Pais pais){
        pais.criarEstado(nome);
    }

    public void criarMunicipio(String nome, Estado estado){
        estado.criarMunicipio(nome);
    }
    */

    public void criarZonaEleitoral(int numero, String endereco, Municipio municipio) {
        municipio.criarZonaEleitoral(numero, endereco);
    }

    public void criarSecao(int numero, ZonaEleitoral zonaEleitoral){
        zonaEleitoral.criarSecao(numero);
    }

    public Pais obterPais(){
        return this.pais;
    }

    public void transferirSecaoEleitor(Eleitor eleitor, Secao secao){
        ((Eleitor)this.usuarioList.get(usuarioList.indexOf(eleitor))).alterarSecao(secao);
    }
}
