package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;

public class Rainha extends CrazyPiece {

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.valorRelativo = "8";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }



    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return null;
        }else{
            return null;
        }
    }

    public boolean movimento(int xO, int yO, int xD, int yD){
        for(CrazyPiece peca : listaPecas){
            if(peca.getX()== Math.abs(xO - xD) || peca.getY() == Math.abs(yO - yD) ){}
        }
        if(Math.abs(xO - xD) <= 5 && Math.abs(yO - yD) <= 5){
            return true;
        }
        return false;
    }
}