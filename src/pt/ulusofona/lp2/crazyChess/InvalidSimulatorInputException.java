package pt.ulusofona.lp2.crazyChess;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;
import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {
    int linhaErro;
    boolean informacaoErrada;

    InvalidSimulatorInputException(int linhaErro) {
       this.linhaErro = linhaErro;
    }

    int getLinhaErro() {
        return linhaErro;
    }
    String getDescricaoProblema(){
        return "";
    }

}
