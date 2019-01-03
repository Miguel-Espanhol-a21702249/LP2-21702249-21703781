package pt.ulusofona.lp2.crazyChess;

public class UndoHelp {
    int iDPecaQueAnda;
    int xDaQueAnda;
    int yDaQueAnda;
    int iDPecaQueMorre;
    int xDaQueMorre;
    int yDaQueMorre;

    UndoHelp(int iDPecaQueAnda, int xDaQueAnda, int yDaQueAnda,int iDPecaQueMorre, int xDaQueMorre, int yDaQueMorre){
        this.iDPecaQueAnda = iDPecaQueAnda;
        this.xDaQueAnda = xDaQueAnda;
        this.yDaQueAnda = yDaQueAnda;
        this.iDPecaQueMorre = iDPecaQueMorre;
        this.xDaQueMorre = xDaQueMorre;
        this.yDaQueMorre = yDaQueMorre;
    }

    public int getxDaQueMorre() {
        return xDaQueMorre;
    }

    public int getxDaQueAnda() {
        return xDaQueAnda;
    }

    public int getyDaQueMorre() {
        return yDaQueMorre;
    }

    public int getyDaQueAnda() {
        return yDaQueAnda;
    }

    public int getiDPecaQueAnda() {
        return iDPecaQueAnda;

    }

    public int getiDPecaQueMorre() {
        return iDPecaQueMorre;
    }
}
