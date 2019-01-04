package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class Lebre extends CrazyPiece {

    Lebre(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 6;
        this.tipoString= "Lebre";
        this.valorRelativo = "2";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    Lebre(int iDPeca, int tipoDePeca, int iDEquipa,int x, int y, boolean capturada ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 6;
        this.tipoString= "Lebre";
        this.valorRelativo = "2";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }


    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "lebre_black.png";
        }else{
            return "lebre_white.png";
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }

    public boolean movimento(CrazyPiece peca,int equipaAtual,int xO, int yO, int xD, int yD) {
        // peça existente nas coordenandas origem
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (xO != xD && yO != yD && Math.abs(xO - xD) == 1 && Math.abs(yO - yD) == 1) {
                    if(countLebre % 2 == 0) {

                        for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                            if(xD == pieces.getX() && yD == pieces.getY()) {
                                if (pieces.getIDEquipa() != peca.getIDEquipa()) {
                                    capturarPeca(pieces,  xD, yD);
                                } else{
                                    return false;
                                }
                            }
                        }


                        return true;
                    }else{
                        return false; // se o turno for impar
                    }
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
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}