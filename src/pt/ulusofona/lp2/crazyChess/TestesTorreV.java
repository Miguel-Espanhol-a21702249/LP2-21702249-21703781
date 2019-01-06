package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class TestesTorreV {
    @Test
    public void test01torreVertFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece torreV = new TorreVert(3,2,10, 4,5,false);
        torreV.posicaoX(0);
        torreV.posicaoY(0);
        assertFalse(torreV.movimento(torreV,10, torreV.getX(), torreV.getY(), 6, 1));
    }

}
