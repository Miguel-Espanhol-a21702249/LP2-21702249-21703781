package pt.ulusofona.lp2.crazyChess;

public class Jogada {
    private int turno;
    int xO;
    int yO;
    int xD;
    int yD;

    Jogada(int turno, int xO, int yO, int xD, int yD){
        this.turno = turno;
        this.xO = xO;
        this.yO = yO;
        this.xD = xD;
        this.yD = yD;
    }

    public int getxD() {
        return xD;
    }

    public int getxO() {
        return xO;
    }

    public int getyD() {
        return yD;
    }

    public int getyO() {
        return yO;
    }

    public int getTurno() {
        return turno;
    }
}
