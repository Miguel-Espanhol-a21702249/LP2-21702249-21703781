package pt.ulusofona.lp2.crazyChess;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulador {
    int sizeTabuleiro;
    int numeroDePecas;
    List<CrazyPiece> listaPecas = new ArrayList<>();
    int turno = 0;
    int vencedor = 3;
    int pecaComidaPreta= 0, pecaComidaBranca = 0;
    int jogadaVBranca = 0, jogadaVPreta = 0;
    int jogadaINVBranca = 0, jogadaINVPreta = 0;
    int jogadasSemCaptura= 0;
    boolean vitoriaSemJogar = false;
    String mensagem;


    public boolean iniciaJogo(File ficheiroInicial) {
        int count =0 ,linhaTabuleiro=0;

        try {

            Scanner leitorFicheiro = new Scanner(ficheiroInicial);

            while(leitorFicheiro.hasNextLine()) {

                String linha = leitorFicheiro.nextLine();
                String dados[] = linha.split(":");

                if (count < 2 ) { // tamanho do tabuleiro e numero de peças

                    if (count == 0){ // tamanho do tabuleiro

                        if (Integer.parseInt(dados[0])>=4 && Integer.parseInt(dados[0])<=12){
                            sizeTabuleiro = Integer.parseInt(dados[0]);
                        }

                    }else{ // numero de peças

                        if (Integer.parseInt(dados[0])<sizeTabuleiro*sizeTabuleiro){
                            numeroDePecas = Integer.parseInt(dados[0]);
                        }

                    }
                    count++;

                }else if (count < 2 + numeroDePecas) {// informaçoes sobre a peça

                    if(!listaPecas.contains(Integer.parseInt(dados[0])) && Integer.parseInt(dados[0])>=1) {

                        if(Integer.parseInt(dados[1])>=0 && Integer.parseInt(dados[1])<=10) {

                            if (Integer.parseInt(dados[2]) == 0 || Integer.parseInt(dados[2])==1) {

                                CrazyPiece piece = new CrazyPiece(Integer.parseInt(dados[0]),Integer.parseInt(dados[1]),Integer.parseInt(dados[2]),dados[3]);
                                listaPecas.add(piece);//adiciona a peça criada a lista

                            }
                        }
                    }
                    count++;

                }else {// disposicao do tabuleiro

                    for ( int coluna =0 ; coluna < sizeTabuleiro ; coluna++) {

                        if ( Integer.parseInt(dados[coluna]) != 0) {

                            for ( CrazyPiece listaPeca : listaPecas) {

                                if (listaPeca.getId() == Integer.parseInt(dados[coluna])) {

                                    listaPeca.posicaoX(coluna);
                                    listaPeca.posicaoY(linhaTabuleiro);
                                    listaPeca.morri = false;

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

    public int getTamanhoTabuleiro(){ //tamanho do tabuleiro

        return sizeTabuleiro;

    }



    public boolean processaJogada(int xO, int yO, int xD, int yD){

        int equipaAtual= getIDEquipaAJogar();

        if(xO != xD && yO != yD || xD < sizeTabuleiro && yD < sizeTabuleiro || xD > 0 && yD > 0) {

            for(CrazyPiece peca : listaPecas){

                if (peca.getX() == xO && peca.getY() == yO) { // procura por uma peça com as coordenadas xO e yO

                    if (peca.getIDEquipa() == equipaAtual) {// procura por uma peça da equipa atual

                        if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) { // distancia do movimento

                            for (CrazyPiece pieces : listaPecas) {

                                if (pieces.getX() == xD && pieces.getY() == yD) {// ve se ja existe uma peça com as coordenadas xD e yD

                                    if (pieces.getIDEquipa() != peca.getIDEquipa()) { // ve se a equipa das duas peças sao diferentes

                                        listaPecas.remove(pieces);//remove a peça comida d alista
                                        pieces.morri = true; // peça comida

                                        if (pieces.getIDEquipa() == 0) {//peça branca comida

                                            pecaComidaBranca++;
                                            jogadaVBranca++;

                                        }else{ //peçca preta comida

                                            pecaComidaPreta++;
                                            jogadaVPreta++;
                                        }

                                        //coloca a peça na nova posicao e muda o turno

                                        peca.posicaoX(xD);
                                        peca.posicaoY(yD);
                                        turno++;
                                        jogadasSemCaptura=0;
                                        return true;

                                    }
                                }
                            }

                            // se nao for nenhuma peça comida, coloca a peça na nova posicao e muda o turno

                            peca.posicaoX(xD);
                            peca.posicaoY(yD);
                            turno++;
                            jogadasSemCaptura++;

                            if(peca.getIDEquipa() == 0){

                                jogadaVPreta++;

                            }else{

                                jogadaVBranca++;

                            }

                            return true;
                            }

                        }

                        if(peca.getIDEquipa() == 0){

                        jogadaINVPreta++;

                    }else{

                        jogadaINVBranca++;
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

            if (peca.iDEquipa == 0 && !peca.morri){

                pecaPreta++;

            } else if (peca.iDEquipa == 1 && !peca.morri){

                pecaBranca++;

            }
        }

        if (pecaBranca == 0) {

            vencedor = 1;
            return true;
        }
        if (pecaPreta == 0){

            //vence branco
            vencedor = 0;
            return true;
        }

        if(pecaBranca == 1 && pecaPreta == 1){
            //vence preto
            vencedor = 3;
            return true;
        }

        if(pecaComidaPreta + pecaComidaBranca > 0 && jogadasSemCaptura == 10) {
            return true;
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
        if( vencedor ==3){

            mensagem = "EMPATE";

        }
        resultados.add("Resultado: " + mensagem );

        resultados.add("---");

        resultados.add("Equipa das Pretas");

        resultados.add("" + pecaComidaPreta);

        resultados.add("" + jogadaVPreta);

        resultados.add("" + jogadaINVPreta);

        resultados.add("Equipa das Brancas");

        resultados.add("" + pecaComidaBranca);

        resultados.add("" + jogadaVBranca);

        resultados.add("" + jogadaINVBranca);

        return resultados;

    }

    public int getIDPeca(int x, int y){

        for(int i=0;i<listaPecas.size();i++){

            if(listaPecas.get(i).x==x && listaPecas.get(i).y==y){

                return listaPecas.get(i).iDPeca;

            }
        }
        return 0;
    }
    public int getIDEquipaAJogar() {

        if (turno % 2 == 0) {

            return 0;

        } else {

            return 1;
        }
    }
    public void setTamanho(int sizeTabuleiro){

        this.sizeTabuleiro = sizeTabuleiro;
    }
    public void setCrazyPieces(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean morri){

        listaPecas.add(new CrazyPiece(iDPeca, tipoDePeca, iDEquipa, alcunha, x, y, morri));

    }
}
