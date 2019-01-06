package pt.ulusofona.lp2.crazyChess;

import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class PoneiMagico extends CrazyPiece {

    PoneiMagico(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 2;
        this.tipoString = "Pónei Mágico";
        this.valorRelativo = "5";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }
    PoneiMagico(int iDPeca, int tipoDePeca, int iDEquipa,int x, int y, boolean capturada){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 2;
        this.valorRelativo = "5";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }


    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "ponei_magico_black.png";
        }else{
            return "ponei_magico_white.png";
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }
    public boolean cantonumero1parte1 (int xO, int yO) {
        int x3 = 0;
        for (int x2 = xO - 1; x2 > xO - 2; x2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == yO) {
                    return false;
                }
            }
            x3 = x2;
        }

        for (int y2 = yO - 1; y2 > yO - 2; y2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x3 && pecas.getY() == y2) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cantonumero1parte2 (int xO, int yO) {
        int y3 = 0;
        for (int y2 = yO - 1; y2 > yO - 2; y2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == xO && pecas.getY() == y2) {
                    return false;
                }
            }
            y3 = y2;
        }
        for (int x2 = xO - 1; x2 > xO - 2; x2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == y3) {
                    return false;
                }
            }
        }


        return true;
    }

    public boolean cantonumero2parte1(int xO,int yO){
        int y3 = 0;
        for (int y2 = yO - 1; y2 > yO - 2; y2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == xO && pecas.getY() == y2) {
                    return false;
                }
            }
            y3 = y2;
        }
    for(int x2 = xO + 1 ; x2 < xO +2; x2++){
        for (CrazyPiece pecas : listaPecasAux) {
            if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == y3) {
                return false;
            }
        }
    }
    return true;
    }

    public boolean cantonumero2parte2(int xO, int yO){
        int x3 = 0;
        for(int x2 = xO + 1 ; x2 < xO +2; x2++){
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == yO) {
                    return false;
                }
            }
            x3= x2;
        }
        for (int y2 = yO - 1; y2 > yO - 2; y2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x3 && pecas.getY() == y2) {
                    return false;
                }
            }
        }
        return true;

    }
    public boolean cantonumero3parte1(int xO, int yO) {
        int x3 = 0;
        for (int x2 = xO - 1; x2 > xO - 2; x2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == yO) {
                    return false;
                }
            }
            x3 = x2;
        }
        for (int y2 = yO + 1; y2 < yO + 2; y2++) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x3 && pecas.getY() == y2) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cantonumero3parte2(int xO, int yO){
        int y3=0;
        for (int y2 = yO + 1; y2 < yO + 2; y2++) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == xO && pecas.getY() == y2) {
                    return false;
                }
            }
            y3 = y2;
        }
        for (int x2 = xO - 1; x2 > xO - 2; x2--) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == yO) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cantonumero4parte1(int xO, int yO){
        int y3=0;
        for (int y2 = yO + 1; y2 < yO + 2; y2++) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == xO && pecas.getY() == y2) {
                    return false;
                }
            }
            y3 = y2;
        }
        for(int x2 = xO + 1 ; x2 < xO +2; x2++){
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == y3) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cantonumero4parte2(int xO, int yO){
        int x3 = 0;
        for(int x2 = xO + 1 ; x2 < xO +2; x2++){
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x2 && pecas.getY() == yO) {
                    return false;
                }
            }
            x3= x2;
        }
        for (int y2 = yO + 1; y2 < yO + 2; y2++) {
            for (CrazyPiece pecas : listaPecasAux) {
                if (pecas.tipoDePeca == 0 && pecas.getX() == x3 && pecas.getY() == y2) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        int direcaoPonei=-2;
        int idComida= 0 ;
        int y= yO;
        int x= xO;
        int idPeca = peca.getId();
        int yFim=yD;
        int xFim = xD;
        if (xO != xD && yO != yD && Math.abs(xO - xD) == 2 && Math.abs(yO - yD) == 2) {

            for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                //pieces  = peça que vai ser comida
                if (xD == pieces.getX() && yD == pieces.getY() ) {
                    if( pieces.getIDEquipa() != peca.getIDEquipa()) {
                        idComida=pieces.getId();
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
                if (cantonumero1parte1(xO, yO) || cantonumero1parte2(xO, yO)) {
                    return true;
                }
                return false;
            }

            if(direcaoPonei == 0) {
                if (cantonumero2parte1(xO, yO) || cantonumero2parte2(xO, yO)) {
                    return true;
                }
                return false;
            }
            if(direcaoPonei == 1){
               if(cantonumero4parte1(xO, yO) || cantonumero4parte2(xO, yO)){
                   return true;
               }
               return false;
            }

            if(direcaoPonei == 2){
                if(cantonumero3parte1(xO, yO) || cantonumero3parte2(xO, yO)){
                    return true;
                }
                return false;
            }
            UndoHelp jogadaAnterior = new UndoHelp(idPeca,  x , y, idComida , xFim , yFim,turnoA);
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

