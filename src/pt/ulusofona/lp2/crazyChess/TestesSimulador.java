package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
public class TestesSimulador {

    @Test
    public void test01ProcessaJogada(){
        Simulador simulador = new Simulador ();
        simulador.setTamanho (8);
        simulador.setCrazyPieces(1,0,0,"O bebado",2,5,false);
        //a peca na posicao (2,5) pode andar para (2,6)
        assertTrue(simulador.processaJogada(2,5, 2,6));

    }
    @Test
    public void test02ProcessaJogada(){
        Simulador simulador = new Simulador ();
        simulador.setTamanho (6);
        simulador.setCrazyPieces(2,0,0,"O esperto",5,1,false);
        //a peca na posicao (5,1) pode andar para (3,2)
        assertFalse(simulador.processaJogada(5,1, 3,2));

    }
}
