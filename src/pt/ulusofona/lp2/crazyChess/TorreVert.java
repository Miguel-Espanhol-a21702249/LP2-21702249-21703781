package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVBranca;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVPreta;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadasSemCaptura;
import static pt.ulusofona.lp2.crazyChess.Simulador.turno;


//torre certa
public class TorreVert extends CrazyPiece {

    TorreVert(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 5;
        this.valorRelativo = "3";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    public String getImagePNG(){
        if(iDEquipa == 10){
            return "torre_v_black.png";
        }else{
            return "torre_v_white.png";
        }
    }

    public boolean movimento(CrazyPiece peca,int equipaAtual,int xO, int yO, int xD, int yD) {
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (xO == xD  && yO!=yD) {

                    // verifica se passa por cima de peças
                    if(yO > yD) {
                        do {
                            for (CrazyPiece p : listaPecas) {

                                if (p.getY() == yO && peca.getY() != p.getY() && p.getX() == peca.getX()) {
                                    return false;
                                }
                            }
                            yO--;
                        } while (yO >= yD);

                    }else{

                        do {
                            for (CrazyPiece p : listaPecas) {

                                if (p.getY() == yO && peca.getY() != p.getY() && p.getX() == peca.getX()) {
                                    return false;
                                }
                            }
                            yO++;
                        } while (yO <= yD);
                    }

                    // peça existente nas coordenadas destino




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
            return iDPeca + " | " + "Torre Vertical" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Torre Vertical" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}
