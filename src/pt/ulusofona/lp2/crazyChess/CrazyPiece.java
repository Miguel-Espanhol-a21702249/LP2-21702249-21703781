package pt.ulusofona.lp2.crazyChess;


import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class  CrazyPiece {
    int iDPeca;
    int tipoDePeca;
    String valorRelativo;
    int iDEquipa;
    String alcunha;
    String tipoString;
    int nrCapturas;
    int nrPontos;
    int x;
    int y;
    boolean capturada = false;

    public CrazyPiece(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = tipoDePeca;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    public CrazyPiece(){}

    public CrazyPiece(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        this.iDPeca = iDPeca;
        this.tipoDePeca = tipoDePeca;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
        this.x=x;
        this.y=y;
        this.capturada = capturada;
    }



    int posicaoX(int x){
        this.x=x;
        return x;
    }

    int posicaoY(int y) {
        this.y = y;
        return y;
    }

    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

    int getTipoDePeca(){
        return tipoDePeca;
    }
    int getIDEquipa(){
        return iDEquipa;
    }
    String getAlcunha(){
        return alcunha;
    }

    public int getId() {
        return iDPeca;
    }
    boolean getCapturada(){
        return capturada;
    }

    public int getNrPontos(){
        return nrPontos;
    }

    public int getNrCapturas(){
        return nrCapturas;
    }


    abstract public String getImagePNG();

    abstract public boolean movimento(CrazyPiece peca, int equipaAJogar,int xO, int yO, int xD, int yD);


    public static void capturarPeca(CrazyPiece peca, int xD, int yD){
        if (peca.getX() == xD && peca.getY() == yD) {
            peca.posicaoY(-1);
            peca.posicaoX(-1);
            peca.capturada = true;

            if( peca.getIDEquipa() == 10){
                pecaComidaPreta++;
            }else{
                pecaComidaBranca++;
            }

            jogadasSemCaptura=0;

        }
    }

    abstract public List<String> listaDeSugestoes(List<CrazyPiece> listaPecas, int xO, int yO, int sizeTabuleiro);
    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}
