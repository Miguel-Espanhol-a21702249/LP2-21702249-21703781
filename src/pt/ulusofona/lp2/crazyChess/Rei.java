package pt.ulusofona.lp2.crazyChess;


import static pt.ulusofona.lp2.crazyChess.Simulador.*;


public class Rei extends CrazyPiece {


    Rei(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rei";
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
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
        // peça existente nas coordenandas origem
                if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {

                    //comer pieces
                    for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                        if(xD == pieces.getX() && yD == pieces.getY()) {
                            if (pieces.getIDEquipa() != peca.getIDEquipa()) {
                                capturarPeca(pieces, xD, yD);
                            } else{
                                return false;
                            }
                        }

                    }
                    return true;


                }else{// se a distancia for maior
                    return false;
                }
    }

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + tipoString + " | " + "(" + valorRelativo + ")" + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + "(" + valorRelativo + ")" + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}