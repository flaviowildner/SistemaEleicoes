package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorCandidatura {

    public static List<Candidatura> buscarCandidatura(){
        List<Candidatura> retornoCandidatura = new ArrayList<>();
        for(ProcessoEleitoral processoEleitoral : Database.processosEleitorais()){
            for(Eleicao eleicao : processoEleitoral.buscarEleicoes()){
                for(Candidatura candidatura : eleicao.buscarCandidaturas()){
                    retornoCandidatura.add(candidatura);
                }
            }
        }
        return retornoCandidatura;
    }

    public static int adicionarCandidatura(Eleicao eleicao, String nomeFantasia, int numero, String cpfEleitor) throws SistemaEleicaoException {
        for(Usuario usuario : Database.usuarios()) {
            if (usuario instanceof Eleitor) {
                if (((Eleitor)usuario).obterCPF().equals(cpfEleitor)) {
                    eleicao.adicionarCandidatura(nomeFantasia, numero, (Eleitor)usuario);
                }
            }
        }
        throw new SistemaEleicaoException("Eleitor nao encontrado");
    }

}
