package pt.ulusofona.lp2.crazyChess;




public class Rei extends CrazyPiece {


    Rei(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
        this.tipoString = "Rei";
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }
    Rei(int iDPeca, int tipoDePeca, int iDEquipa, int x, int y, boolean capturada ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
        this.tipoString = "Rei";
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }


    public String getImagePNG() {
        if (iDEquipa == 10) {
            return "crazy_emoji_black.png";
        } else {
            return "crazy_emoji_white.png";
        }
    }
    public boolean anularJogada(CrazyPiece p, int xO, int xD,int yO,int yD){return true;}
    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        return Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1;
    }
    @Override
    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + tipoString + " | " + "(" + valorRelativo + ")" + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + "(" + valorRelativo + ")" + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}