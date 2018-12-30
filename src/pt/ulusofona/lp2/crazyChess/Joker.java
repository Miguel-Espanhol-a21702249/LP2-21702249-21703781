package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class Joker extends CrazyPiece {



    Joker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 5;
        this.valorRelativo = "4";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }




    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "joker_white.png";
        }else{
            return "joker_black.png";
        }
    }

    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        // peça existente nas coordenandas origem
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {
                    for (CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino
                        capturarPeca(pieces,equipaAtual,xD, yD);
                        jogadaVPreta++;
                        jogadaVBranca++;
                    }
                    peca.posicaoX(xD);
                    peca.posicaoY(yD);
                    turno++;
                    jogadasSemCaptura++;
                    if (peca.getIDEquipa() == 10) {
                        jogadaVPreta++;
                    } else {
                        jogadaVBranca++;
                    }
                    return true;
                }else{ // se a distancia for maior
                    return false;
                }
            } else { // se nao for a vez da equipa jogar
                return false;
            }
        }
        return false;
    }

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}
