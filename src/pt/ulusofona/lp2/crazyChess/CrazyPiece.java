package pt.ulusofona.lp2.crazyChess;

import javax.swing.*;

public class CrazyPiece {
    int IDPeca;
    int tipoDePeca;
    int IDEquipa;
    String alcunha;
    int x;
    int y;

    CrazyPiece(int IDPeca, int tipoDePeca, int IDEquipa, String alcunha){
        this.IDPeca=IDPeca;
        this.tipoDePeca=tipoDePeca;
        this.IDEquipa=IDEquipa;
        this.alcunha=alcunha;
    }
    CrazyPiece() {

    }

    int posicaoX(int x) {
        this.x = x;
        return x;
    }

    int posicaoY(int y) {
        this.y = y;
        return y;
    }

    public int getId() {
        return IDPeca;
    }


    public String getImagePNG() {
        if(IDEquipa == 0 ){
            return "black.png";
        }else{
            return "white.png";
        }
    }

    public String toString() {
        //É MAIS OU MENOS ISTO
        return IDPeca + " " + tipoDePeca + " " + IDEquipa + " " + alcunha + " @ (" + x + ", " + y + ")";

    }
}