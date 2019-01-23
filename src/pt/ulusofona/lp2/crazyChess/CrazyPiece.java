package pt.ulusofona.lp2.crazyChess;


import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class  CrazyPiece {
    int iDPeca;
    int tipoDePeca;
    int valorRelativo;
    int iDEquipa;
    String alcunha;
    String tipoString;
    int nrCapturas;
    int nrPontos;
    int jogadaInvalida;
    int jogadaValida;
    int nrTotalJogadas;
    int ratioJogadas;
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

    public void adicionaPontos(int pontos){
        nrPontos += pontos;
    }
    public int getValorRelativo(){
        return valorRelativo;
    }
    public int getRatioJogadas(){
        if(nrTotalJogadas>=1){
            return jogadaInvalida/nrTotalJogadas;
        }else{
            return 0;
        }

    }
    public void jogadaInvalida(){
        jogadaInvalida++;
    }
    public void jogadaValida(){
        jogadaValida++;
    }
    public void nrTotalJogadas(){
        nrTotalJogadas++;
    }
    public int getJogadaValida(){
        return jogadaValida;
    }
    public int getJogadaInvalida(){
        return jogadaInvalida;
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

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}
