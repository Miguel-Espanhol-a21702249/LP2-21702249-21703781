package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

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
            return "padre_vila_black.png";
        }else{
            return "padre_vila_white.png";
        }
    }

    public boolean movimento(CrazyPiece peca,int equipaAtual,int xO, int yO, int xD, int yD) {
        int direcaoBispo= -2;
        // peça existente nas coordenandas origem
        //peca = peca que esta a ser jogada
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (xO != xD && yO != yD && Math.abs(xO - xD) <= 3 && Math.abs(yO - yD) <= 3) {

                    for (CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino
                        //pieces  = peça que vai ser comida
                        if (xD == pieces.getX() && yD == pieces.getY() ) {
                            if( pieces.getIDEquipa() != peca.getIDEquipa()) {
                                capturarPeca(pieces, equipaAtual, xD, yD);
                                jogadaVPreta++;
                                jogadaVBranca++;
                            }else{
                                return false;
                            }
                        }
                    }

                    if(xO > xD && yO > yD){
                        //diagonal para esquerda cima
                        direcaoBispo = -1;
                    }else{
                        if(xO > xD && yO < yD){
                            //diagonal para esquerda baixo
                            direcaoBispo = 2;
                        }else{
                            if(xO < xD && yO > yD){
                                //diagonal para direita cima
                                direcaoBispo = 0;
                            }else{
                                if(xO < xD && yO < yD){
                                    //diagonal para direita baixa
                                    direcaoBispo = 1;
                                }
                            }
                        }
                    }

                    if(direcaoBispo == -1 ) {

                        do {

                            for (CrazyPiece p : listaPecas) {
                                if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == peca.getX() && p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                                    return false;
                                }
                            }
                            xO--;
                            yO--;
                        } while (xO >= xD && yO >= yD);
                    }


                    if(direcaoBispo == 0){
                        do{
                            for(CrazyPiece p: listaPecas){
                                if(peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO){
                                    return false;
                                }
                            }
                            xO++;
                            yO--;
                        }while( xO <= xD && yO >= yD);
                    }

                    if(direcaoBispo == 1){
                        do{
                            for(CrazyPiece p: listaPecas){
                                if(peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO){
                                    return false;
                                }
                            }
                            xO++;
                            yO++;
                        }while(xO <= xD && yO <= yD);
                    }

                    if(direcaoBispo == 2){
                        do{
                            for(CrazyPiece p: listaPecas){
                                if(peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO){
                                    return false;
                                }
                            }
                            xO--;
                            yO++;
                        }while(xO >= xD && yO <= yD);
                    }



                    for (CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino
                        capturarPeca(pieces,equipaAtual,xD, yD);
                        jogadaVPreta++;
                        jogadaVBranca++;
                    }
                    peca.posicaoX(xD);
                    peca.posicaoY(yD);
                    turno++;
                    jogadasSemCaptura++;
                    if (peca.getIDEquipa() == 10) {
                        jogadaVPreta++;
                    } else {
                        jogadaVBranca++;
                    }
                    return true;
                }else{ // se a distancia for maior
                    return false;
                }
            } else { // se nao for a vez da equipa jogar
                return false;
            }
        }
        return false;
    }

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Padre da Vila" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Padre da Vila" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}