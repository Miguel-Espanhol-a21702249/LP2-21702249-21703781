package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

//rainha certa falta ver se passa por cima de peças
public class Rainha extends CrazyPiece {

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rainha";
        this.valorRelativo = "8";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, int x, int y, boolean capturada ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rainha";
        this.valorRelativo = "8";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }



    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "rainha_black.png";
        }else{
            return "rainha_white.png";
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }

    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        int direcaoRainha = -2;
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (xO != xD || yO != yD) {
                    if (Math.abs(xO - xD) <= 5 && Math.abs(yO - yD) <= 5) {

                        for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                            if (xD == pieces.getX() && yD == pieces.getY() && pieces.getIDEquipa() != peca.getIDEquipa() && pieces.getTipoDePeca() != peca.getTipoDePeca()) { // a rainha nao pode comer rainha
                                capturarPeca(pieces,  xD, yD);
                            }
                        }



                        if (xO > xD && yO > yD) {
                            //diagonal para esquerda cima
                            direcaoRainha = -1;
                        } else {
                            if (xO > xD && yO < yD) {
                                //diagonal para esquerda baixo
                                direcaoRainha = 2;
                            } else {
                                if (xO < xD && yO > yD) {
                                    //diagonal para direita cima
                                    direcaoRainha = 0;
                                } else {
                                    if (xO < xD && yO < yD) {
                                        //diagonal para direita baixa
                                        direcaoRainha = 1;
                                    } else {
                                        if (xO == xD && yO > yD) {
                                            //movimento para cima
                                            direcaoRainha = 3;
                                        } else {
                                            if (xO > xD && yO == yD) {
                                                //movimento para esquerda
                                                direcaoRainha = 4;
                                            } else {
                                                if (xO == xD && yO < yD) {
                                                    //movimento para baixo
                                                    direcaoRainha = 5;
                                                } else {
                                                    if (xO < xD && yO == yD) {
                                                        //movimento para direita
                                                        direcaoRainha = 6;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }


                        if (direcaoRainha == -1) {
                            do {

                                for (CrazyPiece p : listaPecasAux) {
                                    if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == peca.getX() && p.getX() == xO && peca.getX() != p.getX()) {
                                        return false;
                                    }
                                }
                                xO--;
                                yO--;
                            } while (xO >= xD && yO >= yD);
                        }
                        if (direcaoRainha == 0) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {
                                    if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO) {
                                        return false;
                                    }
                                }
                                xO++;
                                yO--;
                            } while (xO <= xD && yO >= yD);
                        }

                        if (direcaoRainha == 1) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {
                                    if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO) {
                                        return false;
                                    }
                                }
                                xO++;
                                yO++;
                            } while (xO <= xD && yO <= yD);
                        }

                        if (direcaoRainha == 2) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {
                                    if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO) {
                                        return false;
                                    }
                                }
                                xO--;
                                yO++;
                            } while (xO >= xD && yO <= yD);
                        }

                        if (direcaoRainha == 3) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {

                                    if (p.getY() == yO && peca.getY() != p.getY() && p.getX() == peca.getX()) {
                                        return false;
                                    }
                                }
                                yO--;
                            } while (yO >= yD);
                        }

                        if (direcaoRainha == 4) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {
                                    if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                                        return false;
                                    }
                                }
                                xO--;
                            } while (xO >= xD);
                        }

                        if (direcaoRainha == 5) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {

                                    if (p.getY() == yO && peca.getY() != p.getY() && p.getX() == peca.getX()) {
                                        return false;
                                    }
                                }
                                yO++;
                            } while (yO <= yD);
                        }

                        if (direcaoRainha == 6) {
                            do {
                                for (CrazyPiece p : listaPecasAux) {

                                    if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                                        return false;
                                    }
                                }
                                xO++;
                            } while (xO <= xD);
                        }

                        //ve se a distancia entre rainha e padre e maior que dois
                        for(CrazyPiece padre : listaPecasAux) {
                            if (padre.getTipoDePeca() == 3 && padre.getIDEquipa() != peca.getIDEquipa()) {
                                if (Math.abs(padre.getX() - xD) <= 2 && Math.abs(padre.getY() - yD) <= 2){
                                    return false;
                                }
                            }
                        }


                        return true;
                    } else { // se a distancia for maior
                        return false;
                    }
                } else { // se nao for a vez da equipa jogar
                    return false;
                }
            }
        }
        return false;
    }



    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }

}