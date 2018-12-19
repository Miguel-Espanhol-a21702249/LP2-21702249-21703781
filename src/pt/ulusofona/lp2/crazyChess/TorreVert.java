package pt.ulusofona.lp2.crazyChess;

public class TorreVert extends CrazyPiece {
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "torre_v_black.png";
        }else{
            return "torre_v_white.png";
        }
    }
}
