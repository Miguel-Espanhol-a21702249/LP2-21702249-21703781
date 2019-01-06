package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pt.ulusofona.lp2.crazyChess.Simulador.*;



public class TestePadreDaVila {



    @Test
    public void test01padreFalso(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece padreDaVila = new PadreDaVila(3,2,10, 4,5,false);
        padreDaVila.posicaoX(0);
        padreDaVila.posicaoY(0);
        assertFalse(padreDaVila.movimento(padreDaVila,10, padreDaVila.getX(), padreDaVila.getY(), 6, 1));
    }

}
