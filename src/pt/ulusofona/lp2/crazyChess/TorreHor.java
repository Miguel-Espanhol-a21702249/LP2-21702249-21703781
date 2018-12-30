package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVBranca;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVPreta;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadasSemCaptura;
import static pt.ulusofona.lp2.crazyChess.Simulador.turno;


//torre certa
public class TorreHor extends CrazyPiece {

    TorreHor(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 4;
        this.valorRelativo = "3";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }



    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "torre_h_black.png";
        }else{
            return "torre_h_white.png";
        }
    }

    public boolean movimento(CrazyPiece peca,int equipaAtual,int xO, int yO, int xD, int yD) {
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (yO == yD) {

                    for(CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino
                        if (xD == pieces.getX() && pieces.getIDEquipa() != peca.getIDEquipa()) {
                            capturarPeca(pieces, equipaAtual, xD, yD);
                            jogadaVPreta++;
                            jogadaVBranca++;
                        }
                    }

                    if(xO > xD) {
                        do {
                            for (CrazyPiece p : listaPecas) {
                                if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                                    return false;
                                }
                            }
                            xO--;
                        } while (xO >= xD);

                    }else{

                        do {
                            for (CrazyPiece p : listaPecas) {

                                if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                                    return false;
                                }
                            }
                            xO++;
                        } while (xO <= xD);
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
                }else{ // se o movimento for errado
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
            return iDPeca + " | " + "Torre Horizontal" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Torre Horizontal" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }

}