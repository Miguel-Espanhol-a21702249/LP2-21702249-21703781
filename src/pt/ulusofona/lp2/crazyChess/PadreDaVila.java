package pt.ulusofona.lp2.crazyChess;

public class PadreDaVila extends CrazyPiece {
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "padre_vila_black.png";
        }else{
            return "padre_vila_white.png";
        }
    }
}
