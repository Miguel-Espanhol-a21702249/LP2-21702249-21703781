package pt.ulusofona.lp2.crazyChess;

public class PóneiMágico extends CrazyPiece {
     public String getImagePNG(){
        if(iDEquipa == 10){
            return "ponei_magico_black.png";
        }else{
            return "ponei_magico_white.png";
        }
    }
}
