package pt.ulusofona.lp2.crazyChess;


import static pt.ulusofona.lp2.crazyChess.Simulador.*;


public class Rei extends CrazyPiece {


    Rei(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
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
    public boolean anularJogada(CrazyPiece peca,int xD,int yD,int xO,int yO){return true;}

    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        // peça existente nas coordenandas origem
        if (peca.getIDEquipa() == equipaAtual) {
            if (Math.abs(xO - xD) == 1 || Math.abs(yO - yD) == 1) {

                //comer pieces
                for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                    if(xD == pieces.getX() && yD == pieces.getY()) {

                        if (pieces.getIDEquipa() != peca.getIDEquipa()) {

                            capturarPeca(pieces, equipaAtual, xD, yD);

                            if(!pieces.getCapturada()){
                                jogadasSemCaptura++;
                            }

                            break;

                        } else{
                            return false;
                        }
                    }

                }
                peca.posicaoX(xD);
                peca.posicaoY(yD);
                turno++;

                if (peca.getIDEquipa() == 10) {
                    jogadaVPreta++;
                } else {
                    jogadaVBranca++;
                }

                return true;


            }else{ // se a distancia for maior

                if (peca.getIDEquipa() == 10) {
                    jogadaINVPreta++;
                } else {
                    jogadaINVBranca++;
                }
                return false;
            }

        } else { // se nao for a vez da equipa jogar

            if (peca.getIDEquipa() == 10) {
                jogadaINVPreta++;
            } else {
                jogadaINVBranca++;
            }

            return false;
        }
    }

    public String toString(){
        if(getCapturada()) {
            return iDPeca + " | " + "Rei" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Rei" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}