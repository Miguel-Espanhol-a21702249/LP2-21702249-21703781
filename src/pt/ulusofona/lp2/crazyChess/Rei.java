package pt.ulusofona.lp2.crazyChess;

public class Rei extends CrazyPiece {
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "rei_black.png";
        }else{
            return "rei_white.png";
        }
    }
}
