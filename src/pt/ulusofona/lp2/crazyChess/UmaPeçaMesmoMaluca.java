package pt.ulusofona.lp2.crazyChess;
import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVBranca;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVPreta;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadasSemCaptura;

public class UmaPeçaMesmoMaluca extends CrazyPiece {

    public String getImagePNG(){
        if(iDEquipa == 10){
            return null;
        }else{
            return null;
        }
    }
    public boolean movimento(CrazyPiece peca,int equipaAJogar,int xO, int yO, int xD, int yD) {
        if (peca.getX() == xO && peca.getY() == yO) {
            if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {
                for (CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino
                    capturarPeca(pieces,xD,yD);
                    jogadaVBranca++;
                    jogadaVPreta++;
                }
                peca.posicaoX(xD);
                peca.posicaoY(yD);
                jogadasSemCaptura++;
                if(peca.getIDEquipa() == 10){
                    jogadaVPreta++;
                }else{
                    jogadaVBranca++;
                }
                return true;
            }
        }
        return false;
    }
}
