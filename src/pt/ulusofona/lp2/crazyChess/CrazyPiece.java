package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {
    int IDPeca;
    int tipoDePeca;
    int IDEquipa;
    String alcunha;
    int x;
    int y;
    boolean morri=true;

    public CrazyPiece(int IDPeca, int tipoDePeca, int idEquipa, String alcunha){
        this.IDPeca = IDPeca;
        this.tipoDePeca = tipoDePeca;
        this.IDEquipa = idEquipa;
        this.alcunha = alcunha;
    }

    public CrazyPiece(){}



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
        return IDEquipa;
    }
    String getAlcunha(){
        return alcunha;
    }

    public int getId() {
        return IDPeca;
    }
    boolean getMorri(){
        return morri;
    }


    public String getImagePNG() {
        if(IDEquipa == 0 ){
            return "black.png";
        }else{
            return "white.png";
        }
    }

    public String toString() {
        if(getMorri() == false) {
            //É MAIS OU MENOS ISTO
            return IDPeca + " | " + tipoDePeca + " | " + IDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return IDPeca + " | " + tipoDePeca + " | " + IDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}