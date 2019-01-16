package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestesRainha {
    @Test
    public void test01rainhaFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece rainha = new Rainha(3,2,10, 4,5,false);
        rainha.posicaoX(0);
        rainha.posicaoY(0);
        assertTrue(rainha.movimento(rainha,10, rainha.getX(), rainha.getY(), 6, 1));
    }

}
