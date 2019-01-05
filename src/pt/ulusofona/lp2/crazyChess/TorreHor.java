package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;


//torre certa
public class TorreHor extends CrazyPiece {

    TorreHor(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 4;
        this.tipoString = "TorreH";
        this.valorRelativo = "3";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    TorreHor(int iDPeca, int tipoDePeca, int iDEquipa,int x, int y, boolean capturada ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 4;
        this.tipoString = "TorreH";
        this.valorRelativo = "3";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }

    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "torre_h_black.png";
        }else{
            return "torre_h_white.png";
        }
    }


    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }

    public boolean movimento(CrazyPiece peca,int equipaAtual,int xO, int yO, int xD, int yD) {
        int idComida = 0 ;
        int y= yO;
        int x= xO;
        int idPeca = peca.getId();
        int yFim=yD;
        int xFim = xD;
            if (peca.getIDEquipa() == equipaAtual) {
                if (yO == yD && xO != xD) {

                    for(CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                        if (xD == pieces.getX() && yO == pieces.getY() && pieces.getIDEquipa() != peca.getIDEquipa()) {
                            idComida = pieces.getId();
                            capturarPeca(pieces, xD, yD);
                        }
                    }

                    if(xO > xD) {
                        do {
                            for (CrazyPiece p : listaPecasAux) {
                                if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                                    return false;
                                }
                            }
                            xO--;
                        } while (xO >= xD);

                    }else{

                        do {
                            for (CrazyPiece p : listaPecasAux) {

                                if (peca.getX() != p.getX() && p.getX() == xO && p.getY() == peca.getY()) {
                                    return false;
                                }
                            }
                            xO++;
                        } while (xO <= xD);
                    }



                    peca.posicaoX(xD);
                    peca.posicaoY(yD);

                    jogadasSemCaptura++;
                    UndoHelp jogadaAnterior = new UndoHelp(idPeca,  x , y, idComida , xFim , yFim,turnoA);
                    listaDasJogadas.add(jogadaAnterior);
                    return true;

                }else{ // se o movimento for errado
                    return false;
                }
            } else { // se nao for a vez da equipa jogar
                return false;
            }

    }


}