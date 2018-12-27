package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.listaPecas;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVBranca;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadaVPreta;
import static pt.ulusofona.lp2.crazyChess.Simulador.jogadasSemCaptura;
import static pt.ulusofona.lp2.crazyChess.Simulador.turno;

//rainha certa falta ver se passa por cima de peças
public class Rainha extends CrazyPiece {

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.valorRelativo = "8";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }



    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return null;
        }else{
            return null;
        }
    }

    //falta ver se ha peças no caminho
    public boolean movimento(CrazyPiece peca,int equipaAtual,int xO, int yO, int xD, int yD) {
        if (peca.getX() == xO && peca.getY() == yO) {
            if (peca.getIDEquipa() == equipaAtual) {
                if (Math.abs(xO - xD) <= 5 && Math.abs(yO - yD) <= 5) {
                    for (CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino

                        if(pieces.getTipoDePeca()!= peca.getTipoDePeca()){ // a rainha nao pode comer rainha
                            capturarPeca(pieces, xD, yD);
                        }
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

}