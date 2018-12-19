package pt.ulusofona.lp2.crazyChess;

public class Lebre extends CrazyPiece {
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "lebre_black.png";
        }else{
            return "lebre_white.png";
        }
    }
}
