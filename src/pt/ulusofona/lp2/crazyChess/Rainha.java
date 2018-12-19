package pt.ulusofona.lp2.crazyChess;

public class Rainha extends CrazyPiece {

    public String getImagePNG(){
        if(iDEquipa == 10){
            return "rainha_black.png";
        }else{
            return "rainha_white.png";
        }
    }
}
