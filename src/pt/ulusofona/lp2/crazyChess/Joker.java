package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece {
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "joker_black.png";
        }else{
            return "joker_white.png";
        }
    }
}
