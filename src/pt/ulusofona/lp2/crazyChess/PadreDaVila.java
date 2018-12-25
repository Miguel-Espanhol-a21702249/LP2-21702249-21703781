package pt.ulusofona.lp2.crazyChess;

public class PadreDaVila extends CrazyPiece {


    PadreDaVila(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 3;
        this.valorRelativo = "3";
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

        if(Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1){
            return true;
        }
        return false;
    }
}
