package pt.ulusofona.lp2.crazyChess;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Simulador {
    int sizeTabuleiro;
    int numeroDePecas;
    List<CrazyPiece> listaPecas = new ArrayList<>();


    public boolean iniciaJogo(File ficheiroInicial) {
        int count =0 ,linhaTabuleiro=0;
        try {
            Scanner leitorFicheiro = new Scanner(ficheiroInicial);


            while(leitorFicheiro.hasNextLine()) {
                String linha = leitorFicheiro.nextLine();
                String dados[] = linha.split(":");
                if (count < 2 ) {
                    if (count == 0){
                        if (Integer.parseInt(dados[0])>=4 && Integer.parseInt(dados[0])<=12){
                            sizeTabuleiro = Integer.parseInt(dados[0]);
                        }
                    }else{
                        if (Integer.parseInt(dados[0])<sizeTabuleiro*sizeTabuleiro){
                            numeroDePecas = Integer.parseInt(dados[0]);
                        }
                    }
                    count++;
                }else if (count < 2 + numeroDePecas) {

                    if(!listaPecas.contains(Integer.parseInt(dados[0])) && Integer.parseInt(dados[0])>=1) {
                        if(Integer.parseInt(dados[1])>=0 && Integer.parseInt(dados[1])<=10) {
                            if (Integer.parseInt(dados[2]) == 0 || Integer.parseInt(dados[2])==1) {
                                CrazyPiece piece = new CrazyPiece(Integer.parseInt(dados[0]),Integer.parseInt(dados[1]),Integer.parseInt(dados[2]),dados[3]);

                                listaPecas.add(piece);
                            }
                        }
                    }
                    count++;
                }else {
                    CrazyPiece piece = new CrazyPiece();
                    for (int coluna =0 ;coluna < sizeTabuleiro ; coluna++) {
                        if( Integer.parseInt(dados[coluna]) != 0) {
                            for (int i = 0; i < listaPecas.size(); i++) {
                                if (listaPecas.get(i).getId() == Integer.parseInt(dados[coluna])) {
                                        listaPecas.get(i).posicaoX(coluna);
                                        listaPecas.get(i).posicaoY(linhaTabuleiro);
                                        System.out.println(listaPecas.get(i).toString());

                                }
                            }
                        }
                    }
                    linhaTabuleiro++;
                }
            }

            leitorFicheiro.close();
            System.out.println();
            for(CrazyPiece p :listaPecas){

                System.out.println(p.toString());
            }
            return true;

        } catch(FileNotFoundException exception) {
            String mensagem = "Erro: o ficheiro " + ficheiroInicial.getName() + " nao foi encontrado.";
            System.out.println(mensagem);
            return false;
        }
    }

    public int getTamanhoTabuleiro(){
        return sizeTabuleiro;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD){
        int equipaAtual=0;
        for(int i = 0; i < listaPecas.size(); i++){
            if(xO != xD && yO!=yD) {
                if (listaPecas.get(i).x == xO && listaPecas.get(i).y == yO) {
                    if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {
                        if (xD < sizeTabuleiro && yD < sizeTabuleiro) {
                            if (0 < xD && 0 < yD) {
                                for (int j = 0; j < listaPecas.size(); j++) {
                                    if (listaPecas.get(j).x == xD && listaPecas.get(j).y == yD) {
                                        if (listaPecas.get(j).IDEquipa == listaPecas.get(i).IDEquipa) {
                                            return false;
                                        } else {
                                            listaPecas.remove(listaPecas.get(j));
                                            listaPecas.get(i).x = xD;
                                            listaPecas.get(i).y = yD;
                                            return true; //true
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;

    }

    public List<CrazyPiece> getPecasMalucas(){
        return listaPecas;
    }

    public boolean jogoTerminado(){
        return false;
    }
    public List<String> getAutores() {
        List<String> dados = new ArrayList<>();
        dados.add("a21702249 - Miguel Espanhol");
        dados.add("a21703781 - Rui Prata");
        return dados;
    }
    public List<String> getResultados(){
        return null;
    }

    public int getIDPeca(int x, int y){
        for(int i=0;i<listaPecas.size();i++){
            if(listaPecas.get(i).x==x && listaPecas.get(i).y==y){
                return listaPecas.get(i).IDPeca;
            }
        }
        return 0;
    }
    public int getIDEquipaAJogar() {
        int turno = 0;
        if (turno % 2 == 0) {
            turno++;
            return 0;
        } else {
            turno++;
            return 1;
        }
    }
}
