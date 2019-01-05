package pt.ulusofona.lp2.crazyChess;


import static pt.ulusofona.lp2.crazyChess.Simulador.*;


public class Rei extends CrazyPiece {


    Rei(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha) {
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
        this.tipoString = "Rei";
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.alcunha = alcunha;
    }
    Rei(int iDPeca, int tipoDePeca, int iDEquipa, int x, int y, boolean capturada ){
        this.iDPeca = iDPeca;
        this.tipoDePeca = 0;
        this.tipoString = "Rei";
        this.valorRelativo = "infinito";
        this.iDEquipa = iDEquipa;
        this.x = x;
        this.y = y;
        this.capturada = capturada;
    }


    public String getImagePNG() {
        if (iDEquipa == 10) {
            return "crazy_emoji_black.png";
        } else {
            return "crazy_emoji_white.png";
        }
    }
    public boolean anularJogada(CrazyPiece p, int xO, int xD,int yO,int yD){return true;}
    public boolean movimento(CrazyPiece peca, int equipaAtual, int xO, int yO, int xD, int yD) {
        // peça existente nas coordenandas origem
        int idComida = 0;
        int y = yO;
        int x = xO;
        int idPeca = peca.getId();
        int yFim = yD;
        int xFim = xD;
        for(CrazyPiece pecas : listaPecasAux){
        if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {

            //comer pieces
            for (CrazyPiece pieces : listaPecasAux) { // peça existente nas coordenadas destino
                if (pieces.getX() == xD && pieces.getY() == yD && pieces.getIDEquipa() != equipaAtual) {
                        idComida = pieces.getId();
                    }
                }
            }

            listaJogadaSugeridaRei.add("[" + xD + "," + yD + "]");
            UndoHelp jogadaAnterior = new UndoHelp(idPeca, x, y, idComida, xFim, yFim, turnoA);
            listaDasJogadas.add(jogadaAnterior);


        }
        return Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1;
    }
    @Override
    public String toString(){
        if(!getCapturada()) {
            return iDPeca + " | " + tipoString + " | " + "(" + valorRelativo + ")" + " | " + iDEquipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else{
            return iDPeca + " | " + tipoString + " | " + "(" + valorRelativo + ")" + " | " + iDEquipa + " | " + alcunha + " @ (n/a)";
        }
    }
}