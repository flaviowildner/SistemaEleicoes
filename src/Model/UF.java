package Model;

import java.util.ArrayList;
import java.util.List;

public class UF {

    public static final int NIVEL_PAIS = 0;
    public static final int NIVEL_ESTADO = 1;
    public static final int NIVEL_MUNICIPIO = 2;
    public static final int NIVEL_ZONAELEITORAL = 3;
    public static final int NIVEL_SECAO = 4;

    private static final String[] NIVEIS = {"Pais", "Estado", "Municipio", "Zona Eleitoral", "Secao"};

    private String nome;
    private int nivel;
    protected List<UF> children;
    protected UF father;

    public UF(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
        this.children = new ArrayList<UF>();
        this.father = null;
    }

    public UF(String nome, int nivel, UF father) {
        this(nome, nivel);
        if (this.nivel > father.nivel) {
            father.children.add(this);
            this.father = father;
        }
    }

    public static String descricaoNivel (int nivel) {
        if (nivel >= 0 && nivel < NIVEIS.length) {
            return NIVEIS[nivel];
        }
        return "";
    }
    public static int subNivel (int nivel) {
        return (nivel < NIVEIS.length-1) ? nivel+1 : -1;
    }
    public static int superNivel (int nivel) {
        return (nivel > 0) ? nivel-1 : -1;
    }

    public String getNome () {
        return this.nome;
    }

    public UF getFather () {
        return this.father;
    }

    public List<UF> getChildren () {
        return this.children;
    }

    @Override
    public String toString () {
        return this.getNome();
    }

}
