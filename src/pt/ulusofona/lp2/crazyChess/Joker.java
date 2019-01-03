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
        boolean jogadaValida=false;
        if(countJoker >= 6){
            countJoker = 0;
        }
        if (peca.getIDEquipa() == equipaAtual) {
                switch (countJoker) {
                    case 0:
                        peca = new Rainha(iDPeca,1,equipaAtual,peca.getX(),peca.getY(),false);
                        jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                        break;
                    case 1:
                        peca = new PoneiMagico(iDPeca,1,equipaAtual,peca.getX(),peca.getY(),false);
                        jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                        break;
                    case 2:
                        peca = new PadreDaVila(iDPeca,1,equipaAtual,peca.getX(),peca.getY(),false);
                        jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                        break;
                    case 3:
                        peca = new TorreHor(iDPeca,1,equipaAtual,peca.getX(),peca.getY(),false);
                        jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                        break;
                    case 4:
                        peca = new TorreVert(iDPeca,1,equipaAtual,peca.getX(),peca.getY(),false);
                        jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                        break;
                    case 5:
                        peca = new Lebre(iDPeca,1,equipaAtual,peca.getX(),peca.getY(),false);
                        jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                        break;

                    default:
                        break;


                }
            }

        return jogadaValida;
    }

    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Joker" + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}