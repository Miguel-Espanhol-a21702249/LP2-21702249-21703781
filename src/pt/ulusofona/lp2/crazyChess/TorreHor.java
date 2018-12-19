package pt.ulusofona.lp2.crazyChess;

public class TorreHor extends CrazyPiece {
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "torre_h_black.png";
        }else{
            return "torre_h_white.png";
        }
    }
}
