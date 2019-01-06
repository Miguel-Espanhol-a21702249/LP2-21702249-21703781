package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
public class TestesSimulador {

    @Test
    public void test01GetEquipaAJogar(){
        PoneiMagico ponei = new PoneiMagico(3, 2, 10, "Ponei Muito mas muito mas muito magico");
        Simulador simulador = new Simulador ();
        simulador.setTamanho (8);
        int equipaEsperada = 10;
        int equipaObtida = ponei.getIDEquipa();
        assertEquals(equipaEsperada,equipaObtida);
    }



    @Test
    public void test02GetTipoDePeca(){
        PoneiMagico ponei = new PoneiMagico(3, 2, 10, "Ponei Muito mas muito mas muito magico");
        Simulador simulador = new Simulador ();
        simulador.setTamanho (6);
        int pecaEsperada= 2;
        int pecaObtida = ponei.getTipoDePeca();
        assertEquals(pecaEsperada,pecaObtida);
    }

    @Test
    public void test03GetAlcunha(){
        PoneiMagico ponei = new PoneiMagico(3, 2, 10, "Ponei Muito mas muito mas muito magico");
        Simulador simulador = new Simulador ();
        simulador.setTamanho (6);
        String alcunhaEsperada = "Ponei Muito mas muito mas muito magico";
        String alcunhaObtida = ponei.getAlcunha();
        assertEquals(alcunhaEsperada,alcunhaObtida);
    }

}