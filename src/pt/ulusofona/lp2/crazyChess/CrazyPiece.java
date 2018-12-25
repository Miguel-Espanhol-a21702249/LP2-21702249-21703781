package pt.ulusofona.lp2.crazyChess;

abstract public class  CrazyPiece {
    int iDPeca;
    int tipoDePeca;
    String valorRelativo;
    int iDEquipa;
    String alcunha;
    int x;
    int y;
    boolean morri=true;

    public CrazyPiece(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = tipoDePeca;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    public CrazyPiece(){}

    public CrazyPiece(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean morri){
        this.iDPeca = iDPeca;
        this.tipoDePeca = tipoDePeca;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
        this.x=x;
        this.y=y;
        this.morri=morri;
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
    boolean getMorri(){
        return morri;
    }

    abstract public String getImagePNG();

    abstract public boolean movimento(int xO, int yO, int xD, int yD);


    public String toString() {
        if(!getMorri()) {
            return iDPeca + " | " + tipoDePeca + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoDePeca + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}