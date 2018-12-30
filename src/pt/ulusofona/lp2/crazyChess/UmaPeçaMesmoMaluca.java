package pt.ulusofona.lp2.crazyChess;
import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVBranca;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVPreta;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadasSemCaptura;

public class UmaPeçaMesmoMaluca extends CrazyPiece {


    UmaPeçaMesmoMaluca(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

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
                    capturarPeca(pieces,equipaAJogar,xD, yD);
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

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Rei" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Rei" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}
