package pt.ulusofona.es.num_aluno.data;

import java.io.Serializable;

/**
 * Created by Miguel Soeiro on 03/12/2016.
 */
public class Variacao implements Serializable {

    private int variacaoValue;

    public int getVariacaoValue() {
        return variacaoValue;
    }

    public int variacaoCalculo(double mesAnterios, double mesSeguinte){
        int conta = (int)(Math.round(((mesSeguinte * 100) / (mesAnterios))));
        variacaoValue = conta - 100;
        return variacaoValue;
    }
}
