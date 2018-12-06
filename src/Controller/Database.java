package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<Usuario> _usuarios = null;
    private static List<ProcessoEleitoral> _processosEleitorais = null;
    private static List<Cargo> _cargos = null;
    private static Pais _brasil = null;
    private static Usuario _usuarioLogado = null;

    public static List<Usuario> usuarios() {
        if (_usuarios == null) {
            _usuarios = new ArrayList<Usuario>();
            _usuarios.add(new Administrador("admin", "admin"));
            _usuarios.add(new Eleitor("fulano", "123", "123456789", "Fulano", brasil().getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).getSecoes().get(0)));
            _usuarios.add(new Eleitor("ciclano", "123", "123456789", "Ciclano", brasil().getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).getSecoes().get(0)));
            _usuarios.add(new Eleitor("beltrano", "123", "123456789", "Beltrano", brasil().getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).getSecoes().get(0)));
        }
        return _usuarios;
    }

    public static List<Cargo> cargos() {
        if (_cargos == null) {
            _cargos = new ArrayList<Cargo>();
            _cargos.add(new Cargo(Posicao.PRESIDENTE, brasil()));
        }
        return _cargos;
    }

    public static List<ProcessoEleitoral> processosEleitorais () {
        if (_processosEleitorais == null) {
            _processosEleitorais = new ArrayList<ProcessoEleitoral>();
            _processosEleitorais.add(new ProcessoEleitoral("Eleicoes 2018"));
            processosEleitorais().get(0).criarEleicao("Presidente", cargos().get(0));
            processosEleitorais().get(0).buscarEleicoes().get(0).adicionarCandidatura("Candidato Fulano", 10, (Eleitor)usuarios().get(1));
            processosEleitorais().get(0).buscarEleicoes().get(0).adicionarCandidatura("Candidato Ciclano", 20, (Eleitor)usuarios().get(2));
            _processosEleitorais.add(new ProcessoEleitoral("Eleicoes 2020"));
            _processosEleitorais.get(0).iniciarEleicoes();
            _processosEleitorais.get(1).iniciarEleicoes();
        }
        return _processosEleitorais;
    }

    public static Pais brasil () {
        if (_brasil == null) {
            _brasil = new Pais("Brasil");
            _brasil.criarEstado("Rio de Janeiro");
            _brasil.criarEstado("Sao Paulo");
            _brasil.getEstados().get(0).criarMunicipio("Nova Iguacu");
            _brasil.getEstados().get(0).criarMunicipio("Rio de Janeiro");
            _brasil.getEstados().get(1).criarMunicipio("Sao Paulo");
            _brasil.getEstados().get(1).criarMunicipio("Ribeirao Preto");
            _brasil.getEstados().get(0).getMunicipios().get(0).criarZonaEleitoral(10, "Av. Gov. Roberto Silveira");
            _brasil.getEstados().get(0).getMunicipios().get(0).getZonasEleitorais().get(0).criarSecao(20);
        }
        return _brasil;
    }

    public static Usuario usuarioLogado() {
        return _usuarioLogado;
    }

    public static void usuarioLogado(Usuario usuario) {
        _usuarioLogado = usuario;
    }
}
