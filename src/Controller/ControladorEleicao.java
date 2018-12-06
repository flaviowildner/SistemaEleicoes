package Controller;

import Model.*;

import java.util.List;

public class ControladorEleicao {

    private Eleicao _eleicao;
    private Candidatura _candidatura;
    private ProcessoEleitoral _processo;

    public void criarEleicao(String nome, Cargo cargo, ProcessoEleitoral processoEleitoral){
        processoEleitoral.criarEleicao(nome, cargo);
    }

    public List<Eleicao> buscarEleicoes(ProcessoEleitoral processoEleitoral){
        return processoEleitoral.buscarEleicoes();
    }

    public void iniciarEleicoes(ProcessoEleitoral processo){
        processo.iniciarEleicoes();
    }

    public void encerrarEleicoes(ProcessoEleitoral processo) {
        processo.encerrarEleicoes();
    }

    public void criarCargo(Posicao posicao, UF uf){
        Cargo cargo = new Cargo(posicao, uf);
        Database.cargos().add(cargo);
    }

    public void registrarVoto(){
        _eleicao.registrarVoto((Eleitor) Database.usuarioLogado(), _candidatura);
    }

    public List<Candidatura> listarCandidaturas(){
        return _eleicao.buscarCandidaturas();
    }

    public void adicionarCandidatura(String nomeFantasia, int numero, String cpfEleitor) throws SistemaEleicaoException {
        for(Usuario usuario : Database.usuarios()) {
            if (usuario instanceof Eleitor) {
                if (((Eleitor)usuario).obterCPF().equals(cpfEleitor)) {
                    _eleicao.adicionarCandidatura(nomeFantasia, numero, (Eleitor)usuario);
                    return;
                }
            }
        }
        throw new SistemaEleicaoException("Eleitor nao encontrado");
    }

    public boolean eleitorJaVotou (Eleitor eleitor) {
        return _eleicao.eleitorJaVotou(eleitor);
    }

    public void setEleicao (Eleicao eleicao) {
        _eleicao = eleicao;
    }

    public Eleicao eleicao(){
        return _eleicao;
    }

    public void setCandidatura (Candidatura candidatura) {
        _candidatura = candidatura;
    }

    public Candidatura candidatura() {
        return _candidatura;
    }

    public void setProcesso (ProcessoEleitoral processo) {
        _processo = processo;
    }

    public ProcessoEleitoral processo() {
        return _processo;
    }
}
