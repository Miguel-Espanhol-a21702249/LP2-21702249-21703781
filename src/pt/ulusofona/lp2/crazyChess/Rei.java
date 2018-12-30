package pt.ulusofona.lp2.crazyChess;


import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVBranca;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVPreta;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadasSemCaptura;
import static pt.ulusofona.lp2.crazyChess.Simulador.turno;

//rei certo
public class Rei extends CrazyPiece {


    Rei(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }


    public String getImagePNG() {
        if (iDEquipa == 10) {
            return "icons8-king-50-black.png";
        } else {
            return "icons8-king-50-white.png";
        }
    }

    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        // peça existente nas coordenandas origem
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {


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
            return iDPeca + " | " + "Rei" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Rei" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}