package pt.ulusofona.lp2.crazyChess;


import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class  CrazyPiece {
    int iDPeca;
    int tipoDePeca;
    String valorRelativo;
    int iDEquipa;
    String alcunha;
    int x;
    int y;
    boolean capturada =true;

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

    int posicaoY(int y){
        this.y=y;
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

    abstract public String getImagePNG();

    abstract public boolean movimento(CrazyPiece peca, int equipaAJogar,int xO, int yO, int xD, int yD);


    public void capturarPeca(CrazyPiece peca,int equipaAJogar, int xD, int yD){
        if (peca.getX() == xD && peca.getY() == yD) {
            if (peca.getIDEquipa() != equipaAJogar) {
                peca.posicaoY(-1);
                peca.posicaoX(-1);
                peca.capturada = true;
                listaPecasComida.add(peca);
                jogadasSemCaptura=0;
            }
        }
    }

    abstract public String toString();

}