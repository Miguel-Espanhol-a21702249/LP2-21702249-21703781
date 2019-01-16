package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class TestesTorreH {
    @Test
    public void test01torreHFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece torreH = new TorreHor(3,2,10, 4,5,false);
        torreH.posicaoX(0);
        torreH.posicaoY(0);
        assertFalse(torreH.movimento(torreH,10, torreH.getX(), torreH.getY(), 6, 1));
    }

}
