package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int iDPeca;
    int tipoDePeca;
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


    public String getImagePNG() {
        if(iDEquipa == 0 ){
            return "black.png";
        }else{
            return "white.png";
        }
    }

    public String toString() {
        if(getMorri() == false) {
            //É MAIS OU MENOS ISTO
            return iDPeca + " | " + tipoDePeca + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoDePeca + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}