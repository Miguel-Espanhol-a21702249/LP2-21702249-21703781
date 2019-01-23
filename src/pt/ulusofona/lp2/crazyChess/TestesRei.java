package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class TestesRei {

    @Test
    public void test01(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece rei = new Rei(1,0,10,-1,-1,false);
        assertFalse(simulador.processaJogada(-1,-1,1,1));
    }

    @Test
    public void test02(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece rei = new Rei(1,0,10,0,0,false);
        assertFalse(simulador.processaJogada(0,0,0,0));
    }

    @Test
    public void test03(){

        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece rei= new Rei(1,0,10,0,0,false);
        CrazyPiece rei2=new Rei(1,0,20,0,1,false);
        listaPecasAux.add(rei);
        listaPecasAux.add(rei2);
        assertFalse(simulador.processaJogada(0,1,0,0));
    }

    @Test
    public void test04(){
        Simulador simulador = new Simulador();
        simulador.setTamanho(8);
        CrazyPiece rei = new Rei(1,0,10,0,0,false);
        listaPecasAux.add(rei);
        assertTrue(rei.movimento(rei,10,0,0,1,0));
    }

}
