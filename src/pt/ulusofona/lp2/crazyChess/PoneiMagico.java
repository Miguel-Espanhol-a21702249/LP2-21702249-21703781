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
    public boolean movimentoVertical(CrazyPiece peca, int xO, int yO, int distanciaX, boolean vertical) {

        if (vertical) {
            for (int y = yO + 1; y <= yO + 2; y++) {
                for (CrazyPiece p : listaPecasAux) {
                    if (p.getY() == y && p.getX()==xO+distanciaX && p.getTipoDePeca() == 0) {
                        return true;
                    }
                }
            }
        } else{
            for( int y = yO - 1; y <= yO - 2; y--){
                for(CrazyPiece p : listaPecasAux){
                    if(p.getY() == y && p.getX() == xO+distanciaX && p.getTipoDePeca() == 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean movimentoHorizontal(CrazyPiece peca, int xO, int yO, int distanciaY,boolean horizontal){
        if(horizontal){
            for(int x=xO+1; x<= xO+2; x++){
                for(CrazyPiece p: listaPecasAux){
                    if(p.getTipoDePeca() == 0  && p.getY()== yO+distanciaY && p.getX() == x){
                        return true;
                    }
                }
            }

        } else{
            for(int x= xO-1; x<= xO-2 ;x--){
                for(CrazyPiece p: listaPecasAux){
                    if(p.getTipoDePeca() == 0 && p.getY()== yO + distanciaY && p.getX() == x  ){
                        return true;
                    }

                }
            }
        }
        return false;
    }
    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        int idComida = 0;
        int y = yO;
        int x = xO;
        int idPeca = peca.getId();
        int yFim = yD;
        int xFim = xD;
        int distanciaX = Math.abs(xO - xD);
        int distanciaY = Math.abs(yO - yD);
        boolean vertical = false;
        boolean passaPorCimaRei = false;
        if (xO != xD && yO != yD && Math.abs(xO - xD) == 2 && Math.abs(yO - yD) == 2) {

            for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                //pieces  = peça que vai ser comida
                if (xD == pieces.getX() && yD == pieces.getY()) {
                    if (pieces.getIDEquipa() != peca.getIDEquipa()) {
                        idComida = pieces.getId();
                        capturarPeca(pieces, xD, yD);
                    } else {
                        return false;
                    }
                }
            }

            if (movimentoVertical(peca, xO, yO, distanciaX, true) && movimentoHorizontal(peca, xO, yO, distanciaY, true)) {
                passaPorCimaRei = true;
            }
            UndoHelp jogadaAnterior = new UndoHelp(idPeca, x, y, idComida, xFim, yFim, turnoA);
            listaDasJogadas.add(jogadaAnterior);
            return passaPorCimaRei;
        }
        return false;
    }



            @Override
    public List<String> listaDeSugestoes(List<CrazyPiece> listaPecas, int xO, int yO, int sizeTabuleiro) {
        return null;
    }

}