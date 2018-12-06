package Controller;

import Model.*;

import java.util.List;

public class ControladorUnidadesFederativas {

    public enum Operacao {
        NADA, SELECIONAR_UF, SELECIONAR_ESTADO, SELECIONAR_MUNICIPIO, SELECIONAR_ZONAELEITORAL, SELECIONAR_SECAO
    }

    private UF _uf;
    private Operacao _op;

    public ControladorUnidadesFederativas() {
        _uf = Database.brasil();
        _op = Operacao.NADA;
    }

    public void criarEstado(String nome) {
        Database.brasil().criarEstado(nome);
    }

    public void criarMunicipio(String nome){
        if (_uf instanceof Estado) {
            ((Estado)_uf).criarMunicipio(nome);
        }
    }

    public void criarZonaEleitoral(int numero, String endereco) {
        if (_uf instanceof Municipio) {
            ((Municipio)_uf).criarZonaEleitoral(numero, endereco);
        }
    }

    public void criarSecao(int numero)
    {
        if (_uf instanceof ZonaEleitoral) {
            ((ZonaEleitoral)_uf).criarSecao(numero);
        }

    }

    public boolean selecionarUf(UF uf) {
        _uf = uf;
        return (
                _uf instanceof Estado && _op == Operacao.SELECIONAR_ESTADO ||
                _uf instanceof Municipio && _op == Operacao.SELECIONAR_MUNICIPIO ||
                _uf instanceof ZonaEleitoral && _op == Operacao.SELECIONAR_ZONAELEITORAL ||
                _uf instanceof Secao && _op == Operacao.SELECIONAR_SECAO

        );
    }

    public UF ufSelecionada() {
        return this._uf;
    }

    public UF ufAnterior() {
        return this._uf.getFather();
    }

    public List<UF> proximasUfs() {
        return this._uf.getChildren();
    }

    public void setOperacao (Operacao op) {
        _op = op;
    }

    public Operacao operacao () {
        return _op;
    }

}
