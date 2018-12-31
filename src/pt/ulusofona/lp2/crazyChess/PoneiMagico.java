package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 2;
        this.valorRelativo = "5";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }


    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "ponei_magico_black.png";
        }else{
            return "ponei_magico_white.png";
        }
    }

    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        int direcaoPonei=-2;
        // peça existente nas coordenandas origem
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (xO != xD && yO != yD && Math.abs(xO - xD) == 2 && Math.abs(yO - yD) == 2) {




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
                        direcaoPonei = -1;
                    }else{
                        if(xO > xD && yO < yD){
                            //diagonal para esquerda baixo
                            direcaoPonei = 2;
                        }else{
                            if(xO < xD && yO > yD){
                                //diagonal para direita cima
                                direcaoPonei = 0;
                            }else{
                                if(xO < xD && yO < yD){
                                    //diagonal para direita baixa
                                    direcaoPonei = 1;
                                }
                            }
                        }
                    }



                    if(direcaoPonei == -1 ) {

                        do {

                            for (CrazyPiece p : listaPecas) {
                                if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && p.tipoDePeca == 0) {
                                    return false;
                                }
                            }
                            xO--;
                            yO--;
                        } while (xO >= xD && yO >= yD);
                    }


                    if(direcaoPonei == 0){
                        do{
                            for(CrazyPiece p: listaPecas){
                                if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && p.tipoDePeca == 0) {
                                    return false;
                                }
                            }
                            xO++;
                            yO--;
                        }while( xO <= xD && yO >= yD);
                    }

                    if(direcaoPonei == 1){
                        do{
                            for(CrazyPiece p: listaPecas){
                                if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && p.tipoDePeca == 0) {
                                    return false;
                                }
                            }
                            xO++;
                            yO++;
                        }while(xO <= xD && yO <= yD);
                    }

                    if(direcaoPonei == 2){
                        do{
                            for(CrazyPiece p: listaPecas){
                                if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && p.tipoDePeca == 0) {
                                    return false;
                                }
                            }
                            xO--;
                            yO++;
                        }while(xO >= xD && yO <= yD);
                    }




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
            return iDPeca + " | " + "Pónei Mágico" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Pónei Mágico" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}