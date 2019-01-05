package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

//rainha certa falta ver se passa por cima de peças
public class Rainha extends CrazyPiece {

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rainha";
        this.valorRelativo = "8";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, int x, int y, boolean capturada) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rainha";
        this.valorRelativo = "8";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }


    @Override
    public String getImagePNG() {
        if (iDEquipa == 10) {
            return "rainha_black.png";
        } else {
            return "rainha_white.png";
        }
    }

    public boolean anularJogada(CrazyPiece peca, int xO, int yO, int xD, int yD) {
        return true;
    }

    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        int distanciaX = Math.abs(xO - xD);
        int idComida = 0;
        int y = yO;
        int x = xO;
        int idPeca = peca.getId();
        int yFim = yD;
        int xFim = xD;
        int distanciaY = Math.abs(yO - yD);
        boolean distanciaMaxima = distanciaX <= 5 && distanciaY <= 5;
        boolean distanciaMaximaHorizontal = distanciaX <= sizeTabuleiro - 1 && distanciaY == 0;
        boolean distanciaMaximaVertical = distanciaY <= sizeTabuleiro - 1 && distanciaX == 0;
        boolean distanciaMaximaDiagonal = distanciaY == distanciaX;
        boolean passaPorCimaPecas = false;
        int direcaoRainha = -2;

        for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
            if (xD == pieces.getX() && yD == pieces.getY() && pieces.getIDEquipa() != peca.getIDEquipa() && pieces.getTipoDePeca() != peca.getTipoDePeca()) { // a rainha nao pode comer rainha
                idComida = pieces.getId();

                capturarPeca(pieces, xD, yD);
            }
        }


        if (xO > xD && yO > yD) {
            //diagonal para esquerda cima
            direcaoRainha = -1;
        }
        if (xO > xD && yO < yD) {
            //diagonal para esquerda baixo
            direcaoRainha = 2;
        }
        if (xO < xD && yO > yD) {
            //diagonal para direita cima
            direcaoRainha = 0;
        }
        if (xO < xD && yO < yD) {
            //diagonal para direita baixa
            direcaoRainha = 1;
        }
        if (xO == xD && yO > yD) {
            //movimento para cima
            direcaoRainha = 3;
        }
        if (xO > xD && yO == yD) {
            //movimento para esquerda
            direcaoRainha = 4;
        }
        if (xO == xD && yO < yD) {
            //movimento para baixo
            direcaoRainha = 5;
        }
        if (xO < xD && yO == yD) {
            //movimento para direita
            direcaoRainha = 6;
        }


        if (direcaoRainha == -1) {
            do {
                for (CrazyPiece p : listaPecasAux) {
                    if (distanciaX == distanciaY) {
                        if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && peca.getX() != p.getX()) {
                            return false;
                        }
                    }
                }
                xO--;
                yO--;
            } while (xO >= xD && yO >= yD);
        }
        if (direcaoRainha == 0) {
            do {
                for (CrazyPiece p : listaPecasAux) {
                    if (distanciaX == distanciaY) {
                        if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && peca.getX() != p.getX()) {
                            return false;
                        }
                    }
                }
                xO++;
                yO--;
            } while (xO <= xD && yO >= yD);
        }

        if (direcaoRainha == 1) {
            do {
                for (CrazyPiece p : listaPecasAux) {
                    if (distanciaX == distanciaY) {
                        if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && peca.getX() != p.getX()) {
                            return false;
                        }
                    }
                }
                xO++;
                yO++;
            } while (xO <= xD && yO <= yD);
        }

        if (direcaoRainha == 2) {
            do {
                for (CrazyPiece p : listaPecasAux) {
                    if (distanciaX == distanciaY) {
                        if (peca.getY() != p.getY() && p.getY() == yO && p.getX() == xO && peca.getX() != p.getX()) {
                            return false;
                        }
                    }
                }
                xO--;
                yO++;
            } while (xO >= xD && yO <= yD);
        }

        if (direcaoRainha == 3) {
            do {
                for (CrazyPiece p : listaPecasAux) {

                    if (p.getY() == yO && peca.getY() != p.getY() && p.getX() == peca.getX()) {
                        return false;
                    }
                }
                yO--;
            } while (yO >= yD);
        }

        if (direcaoRainha == 4) {
            do {
                for (CrazyPiece p : listaPecasAux) {
                    if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                        return false;
                    }
                }
                xO--;
            } while (xO >= xD);
        }

        if (direcaoRainha == 5) {
            do {
                for (CrazyPiece p : listaPecasAux) {

                    if (p.getY() == yO && peca.getY() != p.getY() && p.getX() == peca.getX()) {
                        return false;
                    }
                }
                yO++;
            } while (yO <= yD);
        }

        if (direcaoRainha == 6) {
            do {
                for (CrazyPiece p : listaPecasAux) {

                    if (p.getX() == xO && peca.getX() != p.getX() && p.getY() == peca.getY()) {
                        return false;
                    }
                }
                xO++;
            } while (xO <= xD);
        }

        //ve se a distancia entre rainha e padre e maior que dois
        for (CrazyPiece padre : listaPecasAux) {
            if (padre.getTipoDePeca() == 3 && padre.getIDEquipa() != peca.getIDEquipa()) {
                if (Math.abs(padre.getX() - xD) <= 2 && Math.abs(padre.getY() - yD) <= 2) {
                    return false;
                }
            }
        }

        UndoHelp jogadaAnterior = new UndoHelp(idPeca, x, y, idComida, xFim, yFim, turnoA);
        listaDasJogadas.add(jogadaAnterior);
        return true;

    }
}