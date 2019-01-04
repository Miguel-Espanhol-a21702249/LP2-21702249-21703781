package pt.ulusofona.lp2.crazyChess;
import static pt.ulusofona.lp2.crazyChess.Simulador.*;


public class UmaPecaMesmoMaluca extends CrazyPiece {


    UmaPecaMesmoMaluca(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 8;
        this.tipoString = "Peça Mesmo Maluca";
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    UmaPecaMesmoMaluca(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha,int x, int y, boolean capturada) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 8;
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }

    public String getImagePNG(){
        if(iDEquipa == 10){
            return null;
        }else{
            return null;
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }

    public boolean movimento(CrazyPiece peca,int equipaAJogar,int xO, int yO, int xD, int yD) {
        if (peca.getX() == xO && peca.getY() == yO) {
            if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {
                for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                    capturarPeca(pieces,xD, yD);

                }
                return true;
            }
        }
        return false;
    }

}