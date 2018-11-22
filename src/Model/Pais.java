package Model;

import java.util.List;

public class Pais extends UF {
    private static Pais _brasil;

    public Pais(String nome){
        super(nome, UF.NIVEL_PAIS);
    }

    public List<UF> obterEstados(){
        return this.children;
    }

    public static Pais brasil () {
        if (_brasil == null) {
            _brasil = new Pais("Brasil");
            Estado rj = new Estado("Rio de Janeiro", _brasil);
            Estado sp = new Estado("Sao Paulo", _brasil);
            Municipio ni = new Municipio("Nova Iguacu", rj);
            Municipio gema = new Municipio("Rio de Janeiro", rj);
            Municipio capital = new Municipio("Sao Paulo", sp);
            Municipio rp = new Municipio("Ribeirao Preto", sp);
        }
        return _brasil;
    }
}
