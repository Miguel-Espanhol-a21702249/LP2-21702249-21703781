package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class Joker extends CrazyPiece {



    Joker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 7;
        this.valorRelativo = "4";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    Joker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha,int x, int y, boolean capturada){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 7;
        this.valorRelativo = "4";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }




    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "joker_black.png";
        }else{
            return "joker_white.png";
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD){
        return true;
    }
    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        int turnoJoker=0;
        int turno=0;
        if (peca.getIDEquipa() == equipaAtual) {
            if(turno>=6){
                turnoJoker = turno -6;
            }else{
                turnoJoker = turno;
            }
            switch (turnoJoker){
                case 0:
                    CrazyPiece rainha = new PoneiMagico(this.iDPeca,this.tipoDePeca , this.iDEquipa, this.alcunha);
                    break;
                case 1:
                    CrazyPiece poneiMagico = new PoneiMagico(this.iDPeca,this.tipoDePeca , this.iDEquipa, this.alcunha);
                    break;
                case 2:
                    CrazyPiece padreDaVila = new PadreDaVila(this.iDPeca , this.tipoDePeca, this.iDEquipa, this.alcunha);
                    break;
                case 3:
                    CrazyPiece torreHor = new TorreHor(this.iDPeca , this.tipoDePeca, this.iDEquipa, this.alcunha);
                    break;
                case 4:
                    if (peca.getX() == xO && peca.getY() == yO) {
                        if (peca.getIDEquipa() == equipaAtual) {
                            if (xO == xD && yO != yD) {

                                for(CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                                    if (yD == pieces.getY() && xD == pieces.getX() ) {
                                        if( pieces.getIDEquipa() != peca.getIDEquipa()) {

                                            capturarPeca(pieces, equipaAtual, xD, yD);

                                            if (!pieces.getCapturada()) {
                                                jogadasSemCaptura++;
                                            }
                                            break;

                                        } else {
                                            return false;
                                        }


                                    }
                                }



                                // verifica se passa por cima de peças
                                if(yO > yD) {
                                    do {
                                        for (CrazyPiece p : listaPecasAux) {

                                            if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO) {
                                                return false;
                                            }
                                        }
                                        yO--;
                                    } while (yO >= yD);

                                }else{

                                    do {
                                        for (CrazyPiece p : listaPecasAux) {

                                            if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO) {
                                                return false;
                                            }
                                        }
                                        yO++;
                                    } while (yO <= yD);
                                }




                                peca.posicaoX(xD);
                                peca.posicaoY(yD);
                                turno++;


                                if (peca.getIDEquipa() == 10) {
                                    jogadaVPreta++;
                                } else {
                                    jogadaVBranca++;
                                }

                                return true;

                            }else{// se o movimento for errado

                                if (peca.getIDEquipa() == 10) {
                                    jogadaINVPreta++;
                                } else {
                                    jogadaINVBranca++;
                                }
                                return false;
                            }
                        } else { // se nao for a vez da equipa jogar

                            if (peca.getIDEquipa() == 10) {
                                jogadaINVPreta++;
                            } else {
                                jogadaINVBranca++;
                            }
                            return false;
                        }
                    }

                    if (peca.getIDEquipa() == 10) {
                        jogadaINVPreta++;
                    } else {
                        jogadaINVBranca++;
                    }
                    break;
                case 5:
                    CrazyPiece lebre = new Lebre(this.iDPeca , this.tipoDePeca, this.iDEquipa, this.alcunha);
                    break;
            }
        }
        return true;
    }

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}