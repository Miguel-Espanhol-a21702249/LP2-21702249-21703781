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
        // peça existente nas coordenandas origem
        int idComida = 0;
        int y= yO;
        int x= xO;
        int idPeca = peca.getId();
        int yFim= yD;
        int xFim = xD;
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (xO != xD && yO != yD && Math.abs(xO - xD) <= 3 && Math.abs(yO - yD) <= 3) {
                    if(Math.abs(xO - xD) == Math.abs(yO-yD)){
                        for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                            if (xD == pieces.getX() && yD == pieces.getY()) {
                                if (pieces.getIDEquipa() != peca.getIDEquipa()) {
                                    idComida = pieces.getId();
                                    capturarPeca(pieces, xD, yD);
                                } else {
                                    return false;
                                }
                            }
                        }

                        UndoHelp jogadaAnterior = new UndoHelp(idPeca,  x , y, idComida , xFim , yFim,turnoA);
                        listaDasJogadas.add(jogadaAnterior);
                        return true;
                    }else{
                        return false; // se o turno for impar
                    }
                }else{ // se a distancia for maior
                    return false;
                }
            } else { // se nao for a vez da equipa jogar
                return false;
            }
        }
        return false;
    }


}