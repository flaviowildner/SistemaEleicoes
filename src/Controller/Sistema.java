package Controller;

import Model.*;
import View.TelaLogin;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static List<Usuario> _usuarios = null;
    private static List<ProcessoEleitoral> _processosEleitorais = null;
    private static List<Cargo> _cargos = null;
    private static Pais _brasil = null;


    //JFrame telaAtual = null;

    public static void inicializar () {

        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);

    }

    public static List<Usuario> usuarios() {
        if (_usuarios == null) {
            _usuarios = new ArrayList<Usuario>();
            criarAdministrador("admin", "admin");
            criarEleitor("fulano", "123", "123456789", "Fulano", brasil().getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).getSecoes().get(0));
            criarEleitor("ciclano", "123", "123456789", "Ciclano", brasil().getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).getSecoes().get(0));
            criarEleitor("beltrano", "123", "123456789", "Beltrano", brasil().getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).getSecoes().get(0));
        }
        return _usuarios;
    }

    public static List<Cargo> cargos() {
        if (_cargos == null) {
            _cargos = new ArrayList<Cargo>();
            criarCargo(Posicao.PRESIDENTE, brasil());
        }
        return _cargos;
    }

    public static List<ProcessoEleitoral> processosEleitorais () {
        if (_processosEleitorais == null) {
            _processosEleitorais = new ArrayList<ProcessoEleitoral>();
            criarProcessoEleitoral("Eleicoes 2018");
            processosEleitorais().get(0).criarEleicao("Presidente", cargos().get(0));
            processosEleitorais().get(0).buscarEleicoes().get(0).adicionarCandidatura("Candidato Fulano", 10, (Eleitor)usuarios().get(1));
            processosEleitorais().get(0).buscarEleicoes().get(0).adicionarCandidatura("Candidato Ciclano", 20, (Eleitor)usuarios().get(2));
            criarProcessoEleitoral("Eleicoes 2020");
        }
        return _processosEleitorais;
    }

    public static Pais brasil () {
        if (_brasil == null) {
            _brasil = new Pais("Brasil");
            criarEstado("Rio de Janeiro");
            criarEstado("Sao Paulo");
            criarMunicipio("Nova Iguacu", _brasil.getEstados().get(0));
            criarMunicipio("Rio de Janeiro", _brasil.getEstados().get(0));
            criarMunicipio("Sao Paulo", _brasil.getEstados().get(1));
            criarMunicipio("Ribeirao Preto", _brasil.getEstados().get(1));
            criarZonaEleitoral(10, "Av. Gov. Roberto Silveira", _brasil.getEstados().get(0).getMunicipios().get(0));
            criarSecao(20, _brasil.getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0));
        }
        return _brasil;
    }

    public static Usuario logar(String login, String senha){
        for(Usuario usuario : usuarios()){
            if(usuario.getLogin().equals(login)){
                if(usuario.autenticar(senha)){
                    return usuario;
                }
            }
        }
        return null;
    }

    //CHECAR SE USUARIO EXISTE
    public static void criarAdministrador(String login, String senha) {
        Administrador administrador;
        administrador = new Administrador(login, senha);
        _usuarios.add(administrador);
    }

    public static void criarEleitor(String login, String senha, String cpf, String nome, Secao secao){
        Eleitor eleitor = new Eleitor(login, senha, cpf, nome, secao);
        _usuarios.add(eleitor);
    }

    public static void criarProcessoEleitoral(String nome){
        ProcessoEleitoral processoEleitoral = new ProcessoEleitoral(nome);
        _processosEleitorais.add(processoEleitoral);
    }

    public static void criarEleicao(String nome, Cargo cargo, ProcessoEleitoral processoEleitoral){
        processoEleitoral.criarEleicao(nome, cargo);
    }

    public static List<ProcessoEleitoral> buscarProcessosEleitorais(){
        return processosEleitorais();
    }

    public static List<Eleicao> buscarEleicoes(ProcessoEleitoral processoEleitoral){
        return processoEleitoral.buscarEleicoes();
    }

    public static List<Eleitor> buscarEleitor(){
        List<Eleitor> retornoEleitor = new ArrayList<>();
        for(Usuario usuario : usuarios()) {
            if(usuario instanceof Eleitor){
                retornoEleitor.add((Eleitor)usuario);
            }
        }
        return retornoEleitor;
    }

    public static List<Candidatura> buscarCandidatura(){
        List<Candidatura> retornoCandidatura = new ArrayList<>();
        for(ProcessoEleitoral processoEleitoral : processosEleitorais()){
            for(Eleicao eleicao : processoEleitoral.buscarEleicoes()){
                for(Candidatura candidatura : eleicao.buscarCandidaturas()){
                    retornoCandidatura.add(candidatura);
                }
            }
        }
        return retornoCandidatura;
    }

    public List<Administrador> buscarAdministrador(){
        List<Administrador> retornoAdministrador = new ArrayList<>();
        for(Usuario usuario : usuarios()){
            if(usuario instanceof Administrador){
                retornoAdministrador.add((Administrador) usuario);
            }
        }
        return retornoAdministrador;
    }

    public static void criarCargo(Posicao posicao, UF uf){
        Cargo cargo = new Cargo(posicao, uf);
        _cargos.add(cargo);
    }

    public static int adicionarCandidatura(Eleicao eleicao, String nomeFantasia, int numero, String cpfEleitor){
        for(Eleitor eleitor : buscarEleitor()){
            if(eleitor.obterCPF().equals(cpfEleitor)){
                return eleicao.adicionarCandidatura(nomeFantasia, numero, eleitor);
            }
        }
        return 4;
    }

    public static void criarEstado(String nome){
        brasil().criarEstado(nome);
    }

    public static void criarMunicipio(String nome, Estado estado){
        estado.criarMunicipio(nome);
    }

    public static void criarZonaEleitoral(int numero, String endereco, Municipio municipio) {
        municipio.criarZonaEleitoral(numero, endereco);
    }

    public static void criarSecao(int numero, ZonaEleitoral zonaEleitoral){
        zonaEleitoral.criarSecao(numero);
    }

    public static void transferirSecaoEleitor(Eleitor eleitor, Secao secao){
        ((Eleitor)usuarios().get(usuarios().indexOf(eleitor))).alterarSecao(secao);
    }
}
