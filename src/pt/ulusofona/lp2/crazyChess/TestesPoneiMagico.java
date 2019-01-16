package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class TestesPoneiMagico {

    @Test
    public void test01poneiFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece ponei = new PoneiMagico(3,2,10, 4,5,false);
        ponei.posicaoX(0);
        ponei.posicaoY(0);
        assertFalse(ponei.movimento(ponei,10, ponei.getX(), ponei.getY(), 6, 1));
    }

}
