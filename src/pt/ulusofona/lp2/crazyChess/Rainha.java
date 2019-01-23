package pt.ulusofona.lp2.crazyChess;

import java.util.List;
import java.util.Map;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

//rainha certa falta ver se passa por cima de peças
public class Rainha extends CrazyPiece {
    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, int x, int y, boolean capturada) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rainha";
        this.valorRelativo = 8;
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }

    Rainha(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 1;
        this.tipoString = "Rainha";
        this.valorRelativo = 8;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }




    @Override
    public String getImagePNG() {
        if (iDEquipa == 10) {
            return "rainha_black.png";
        } else {
            return "rainha_white.png";
        }
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


        if ((distanciaX >= 5) || (distanciaY >= 5)) {
            return false;
        } else {
            if (xO == xD || yO == yD) {
                if (xO == xD) {
                    if (yO - yD < 0) {
                        for (int i = yO + 1; i <= yD; i++) {
                            for (CrazyPiece p : listaPecasAux) {
                                if (p.getY() == i && p.getX() == xO && p.getY() != yD) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        for (int i = yO - 1; i >= yD; i--) {
                            for (CrazyPiece p : listaPecasAux) {
                                if (p.getY() == i && p.getX() == xO && p.getY() != yD) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                //horizontal
                else {
                    if (xO - xD < 0) {
                        for (int i = xO + 1; i <= xD; i++) {
                            for (CrazyPiece p : listaPecasAux) {
                                if (p.getX() == i && p.getY() == yO && p.getX() != xD) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        for (int i = xO - 1; i >= xD; i--) {
                            for (CrazyPiece p : listaPecasAux) {
                                if (p.getX() == i && p.getY() == yO && p.getX() != xD) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            } else {
                //diagonal
                if (distanciaX == distanciaY) {
                    //diagonal baixo direita
                    if (xO < xD && yO < yD) {
                        for (int xCount = xO + 1; xCount <= xD; xCount++) {
                            for (int yCount = yO + 1; yCount <= yD; y++) {
                                if (xCount == yCount) {
                                    for (CrazyPiece p : listaPecasAux) {
                                        if (p.getX() == xCount && p.getY() == y && p.getX() != xD) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        //diagonal baixo esquerda
                        if (xO > xD && yO < yD) {
                            for (int xCount = xO - 1; xCount >= xD; xCount--) {
                                for (int yCount = yO + 1; yCount <= yD; yCount++) {
                                    if (Math.abs(xO - xCount) == Math.abs(yO - yCount)) {
                                        for (CrazyPiece p : listaPecasAux) {
                                            if (p.getX() == xCount && p.getY() == yCount && p.getX() != xD) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            //diagonal cima esquerda
                            if (xO > xD && yO > yD) {
                                for (int xCount = xO - 1; xCount >= xD; xCount--) {
                                    for (int yCount = yO - 1; yCount >= yD; yCount--) {
                                        if (xCount == yCount) {
                                            for (CrazyPiece p : listaPecasAux) {
                                                if (p.getX() == xCount && p.getY() == yCount && p.getX() != xD) {
                                                    return false;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                //diagonal cima direita
                                if (xO < xD && yO > yD) {
                                    for (int xCount = xO + 1; xCount <= xD; xCount++) {
                                        for (int yCount = yO - 1; yCount >= yD; yCount--) {
                                            if (Math.abs(xO - xCount) == Math.abs(yO - yCount)) {
                                                for (CrazyPiece p : listaPecasAux) {
                                                    if (p.getX() == xCount && p.getY() == yCount && p.getX() != xD) {
                                                        return false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    return false;
                }
                for (CrazyPiece p : listaPecasAux) {
                    if (peca.getTipoDePeca() == 1) {
                        if (peca.getX() == xD && peca.getY() == yD) {
                            return false;
                        }
                    } else {
                        if (peca.getTipoDePeca() == 3) {
                            if (Math.abs(peca.getX() - xD) == 1 && Math.abs(peca.getY() - yD) == 1) {
                                if (peca.getIDEquipa() != getIDEquipa()) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            UndoHelp jogadaAnterior = new UndoHelp(idPeca, x, y, idComida, xFim, yFim, turnoA);
            listaDasJogadas.add(jogadaAnterior);
            return true;
        }
    }
}

