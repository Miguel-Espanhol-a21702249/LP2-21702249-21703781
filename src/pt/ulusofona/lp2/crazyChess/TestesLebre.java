package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class TestesLebre {
    @Test
    public void test01LebreFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece lebre = new Lebre(3,2,10, 4,5,false);
        lebre.posicaoX(0);
        lebre.posicaoY(0);
        assertFalse(lebre.movimento(lebre,10, lebre.getX(), lebre.getY(), 6, 1));
    }

}
