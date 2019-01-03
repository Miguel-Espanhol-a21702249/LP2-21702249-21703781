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

    Joker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha,int x, int y, boolean capturada){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 5;
        this.valorRelativo = "4";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }




    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "joker_white.png";
        }else{
            return "joker_black.png";
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }
    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {return true;}

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}