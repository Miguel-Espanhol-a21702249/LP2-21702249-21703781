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
    int turno = 0;
    int vencedor;
    String mensagem;


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
                    for (int coluna =0 ;coluna < sizeTabuleiro ; coluna++) {
                        if( Integer.parseInt(dados[coluna]) != 0) {
                            for (CrazyPiece listaPeca : listaPecas) {
                                if (listaPeca.getId() == Integer.parseInt(dados[coluna])) {
                                    listaPeca.posicaoX(coluna);
                                    listaPeca.posicaoY(linhaTabuleiro);
                                }
                            }
                        }
                    }
                    linhaTabuleiro++;
                }
            }

            leitorFicheiro.close();
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
        if(xO != xD && yO != yD || xD < sizeTabuleiro && yD < sizeTabuleiro || xD > 0 && yD > 0) {
        for(CrazyPiece listaPeca : listaPecas){
                if (listaPeca.getX() == xO && listaPeca.getY() == yO) {
                    if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {
                                for (CrazyPiece pieces: listaPecas) {
                                    if (pieces.getX() == xD && pieces.getY() == yD) {
                                        if (pieces.getIDEquipa() == listaPeca.getIDEquipa()) {
                                            return false;
                                        } else {
                                            listaPecas.remove(pieces);
                                            listaPeca.posicaoX(xD);
                                            listaPeca.posicaoY(yD);
                                            turno++;
                                            return true;

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
        int pecaPreta=0, pecaBranca=0;
        for (CrazyPiece peca : listaPecas){
            if (peca.IDEquipa == 0){
                pecaPreta++;
            } else {
                pecaBranca++;
            }
        }
        if(pecaBranca == 0) {
            //vence preto
            return true;
        }
        if(pecaPreta == 0){
            //vence branco
            return true;
        }

        if(pecaBranca == 1 && pecaPreta == 1){
            return true;
        }

        if(turno > 10){
            return false;
        }


        return false;
    }


    public List<String> getAutores() {
        List<String> dados = new ArrayList<>();
        dados.add("a21702249 - Miguel Espanhol");
        dados.add("a21703781 - Rui Prata");
        return dados;
    }
    public List<String> getResultados(){
        List<String> resultados = new ArrayList<>();
        resultados.add("JOGO DE CRAZY CHESS");
        if ( vencedor == 0 ){
            mensagem = "VENCERAM AS BRANCAS";
        }
        if( vencedor == 1 ){
            mensagem = "VENCERAM AS PRETAS";

        }
        resultados.add("Resultado: " + mensagem );
        resultados.add("---");
        resultados.add("Equipa das Pretas ");
        /*
        resultados.add(numero de pretas comidas);
        resultados.add(jogadas validas de pretas);
        resultados.add(jogadas invalidas de pretas);
        resultados.add("Equipa das Brancas ");
        resultados.add(numero de brancas comidas);
        resultados.add(jogadas validas de brancas);
        resultados.add(jogadas invalidas de brancas);*/

        return resultados;

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
