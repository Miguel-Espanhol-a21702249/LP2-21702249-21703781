package pt.ulusofona.lp2.crazyChess;

import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class Joker extends CrazyPiece {
    private String designacao= designacaoJoker();


    Joker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha,int x, int y, boolean capturada){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 7;
        this.valorRelativo = 4;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }
    Joker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 7;
        this.valorRelativo = 4;
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }



    public String designacaoJoker() {
        if (countJoker >= 6) {
            countJoker = 0;
        }
            switch (countJoker) {
                case 0:
                    return "Rainha";
                case 1:
                    return "Ponei Mágico";
                case 2:
                    return "Padre da Vila";
                case 3:
                    return "TorreH";
                case 4:
                    return "TorreV";
                case 5:
                    return "Lebre";
                default:
                    break;
            }
        return null;
    }


    @Override
    public String getImagePNG(){
        if(iDEquipa == 10){
            return "joker_black.png";
        }else{
            return "joker_white.png";
        }
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
                    peca = new PoneiMagico(iDPeca,2,equipaAtual,peca.getX(),peca.getY(),false);
                    jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                    break;
                case 2:
                    peca = new PadreDaVila(iDPeca,3,equipaAtual,peca.getX(),peca.getY(),false);
                    jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                    break;
                case 3:
                    peca = new TorreHor(iDPeca,4,equipaAtual,peca.getX(),peca.getY(),false);
                    jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                    break;
                case 4:
                    peca = new TorreVert(iDPeca,5,equipaAtual,peca.getX(),peca.getY(),false);
                    jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                    break;
                case 5:
                    peca = new Lebre(iDPeca,6,equipaAtual,peca.getX(),peca.getY(),false);
                    jogadaValida=peca.movimento(peca,equipaAtual,xO,yO,xD,yD);
                    break;

                default:
                    break;


            }
        }

        return jogadaValida;
    }

    @Override
    public List<String> listaDeSugestoes(List<CrazyPiece> listaPecas, int xO, int yO, int sizeTabuleiro) {
        return null;
    }


    @Override
    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + "Joker/" + designacaoJoker() +  " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + "Joker/" + designacaoJoker() + " | " + valorRelativo + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }

}