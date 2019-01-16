package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class Lebre extends CrazyPiece {

    Lebre(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 6;
        this.tipoString= "Lebre";
        this.valorRelativo = "2";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    Lebre(int iDPeca, int tipoDePeca, int iDEquipa,int x, int y, boolean capturada ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 6;
        this.tipoString= "Lebre";
        this.valorRelativo = "2";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }


    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "lebre_black.png";
        }else{
            return "lebre_white.png";
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
                if (xO != xD && yO != yD && Math.abs(xO - xD) == 1 && Math.abs(yO - yD) == 1) {
                    if(Math.abs(xO - xD) == Math.abs(yO-yD)){
                    if(countLebre % 2 != 0) {
                        return false;
                    }
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

    @Override
    public List<String> listaDeSugestoes(List<CrazyPiece> listaPecas, int xO, int yO, int sizeTabuleiro) {
        List<String> sugestoesDaLebre = new ArrayList<>();


        if (x-1 >= 0 && y+1 < sizeTabuleiro) {
            sugestoesDaLebre.add((x-1) + ", " + (y+1));
        }
        if(x+1 < sizeTabuleiro && y+1 < sizeTabuleiro) {
            sugestoesDaLebre.add((x+1) + ", " + (y+1));
        }
        if (x+1 < sizeTabuleiro && y-1 >= 0) {
            sugestoesDaLebre.add((x+1) + ", " + (y-1));
        }
        if (x-1 >= 0 && y-1 >= 0 ) {
            sugestoesDaLebre.add((x-1) + ", " + (y-1));
        }
        //remove sugestao se tiver outra peça no mesmo sitio

        for(CrazyPiece pecas : listaPecas) {
            for (int ocupado=0; ocupado < sugestoesDaLebre.size(); ocupado++) {
                if (Simulador.turnoAJogar == pecas.getIDEquipa() && sugestoesDaLebre.get(ocupado).equals(pecas.getX() + ", " + pecas.getY())) {
                    sugestoesDaLebre.remove(ocupado);
                    ocupado=0;
                }
            }
        }
        return sugestoesDaLebre;
    }
}