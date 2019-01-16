package pt.ulusofona.lp2.crazyChess;

import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class PadreDaVila extends CrazyPiece {


    PadreDaVila(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 3;
        this.tipoString = "Padre da Vila";
        this.valorRelativo = "3";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }
    PadreDaVila(int iDPeca, int tipoDePeca, int iDEquipa,int x, int y, boolean capturada){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 3;
        this.tipoString = "Padre da Vila";
        this.valorRelativo = "3";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
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
        int idComida =  0;
        int y= yO;
        int x= xO;
        int idPeca = peca.getId();
        int yFim=yD;
        int xFim = xD;
                if (xO != xD && yO != yD && Math.abs(xO - xD) <= 3 && Math.abs(yO - yD) <= 3) {




                    //verifica se ha peça para ser comida
                    for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                        //pieces  = peça que vai ser comida
                        if (xD == pieces.getX() && yD == pieces.getY() ) {
                            if( pieces.getIDEquipa() != peca.getIDEquipa()) {
                                idComida = pieces.getId();
                                capturarPeca(pieces, xD, yD);
                            }else{
                                return false;
                            }
                        }
                    }


                    //ve se ha salta por cima de peças
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

                            for (CrazyPiece p : listaPecasAux) {
                                if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO ) {
                                    return false;
                                }
                            }
                            xO--;
                            yO--;
                        } while (xO >= xD && yO >= yD);
                    }




                    if(direcaoBispo == 0){
                        do{
                            for(CrazyPiece p: listaPecasAux){
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
                            for(CrazyPiece p: listaPecasAux){
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
                            for(CrazyPiece p: listaPecasAux){
                                if(peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO){
                                    return false;
                                }
                            }
                            xO--;
                            yO++;
                        }while(xO >= xD && yO <= yD);
                    }


                    //ve se a distancia entre rainha e padre e maior que dois
                    for(CrazyPiece rainha : listaPecasAux) {
                        if (rainha.getTipoDePeca() == 1 && rainha.getIDEquipa() != peca.getIDEquipa()) {
                            if (Math.abs(rainha.getX() - xD) == 1 && Math.abs(rainha.getY() - yD) == 1) {
                                return false;
                            }
                        }

                    }
                    UndoHelp jogadaAnterior = new UndoHelp(idPeca,  x , y, idComida , xFim, yFim,turnoA);
                    listaDasJogadas.add(jogadaAnterior);
                    return true;
                }else{ // se a distancia for maior
                    return false;
                }
    }

    @Override
    public List<String> listaDeSugestoes(List<CrazyPiece> listaPecas, int xO, int yO, int sizeTabuleiro) {
        return null;
    }

}