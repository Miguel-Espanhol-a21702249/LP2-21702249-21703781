package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class TestesJoker {
    @Test
    public void test01jokerFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece joker = new Joker(3,2,10,"Jokerino", 4,5,false);
        joker.posicaoX(0);
        joker.posicaoY(0);
        assertFalse(joker.movimento(joker,10, joker.getX(), joker.getY(), 6, 1));
    }

}
