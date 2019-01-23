package pt.ulusofona.lp2.crazyChess;


import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {
    int linhaErro;
    int dados;
    int x=4;

    InvalidSimulatorInputException(int linhaErro) {
       this.linhaErro = linhaErro;
    }

    InvalidSimulatorInputException(int linhaErro,int dados){
        this.linhaErro = linhaErro;
        this.dados = dados;
    }

    int getLinhaErro() {
        return linhaErro;
    }
    String getDescricaoProblema(){
        if(this.dados >= 5){
            return "DADOS A MAIS (Esperava: "+ x +" ; Obtive: "+ dados +")";
        } else{
            return "DADOS A MENOS (ESPERAVA: "+ x +" ; Obtive: "+ dados +")";
        }
    }

}
