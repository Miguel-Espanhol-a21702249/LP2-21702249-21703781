package pt.ulusofona.lp2.crazyChess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulador {
    int sizeTabuleiro;
    int numeroDePecas;
    static List<CrazyPiece> listaPecas = new ArrayList<>();
    int turno = 0;
    int vencedor = 3;
    int pecaComidaPreta= 0, pecaComidaBranca = 0;
    int jogadaVBranca = 0, jogadaVPreta = 0;
    int jogadaINVBranca = 0, jogadaINVPreta = 0;
    int jogadasSemCaptura= 0;
    boolean vitoriaSemJogar = false;
    String mensagem;

    public void capturarPeca(CrazyPiece peca, int xD, int yD){
        if (peca.getX() == xD && peca.getY() == yD) {
            if (peca.getIDEquipa() != peca.getIDEquipa()) {
                listaPecas.remove(peca);
                peca.morri = true;
                if(peca.getIDEquipa() == 10) {
                    pecaComidaBranca++;

                }else{
                    pecaComidaPreta++;
                }
                peca.posicaoX(xD);
                peca.posicaoY(yD);
                turno++;
                jogadasSemCaptura=0;
            }
        }
    }

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

                    if(!listaPecas.contains(Integer.parseInt(dados[0])) && Integer.parseInt(dados[0])>=1) { // peça repetida
                        if(Integer.parseInt(dados[1])>=0 && Integer.parseInt(dados[1])<=10) { // tipo peça
                            if (Integer.parseInt(dados[2]) == 10 || Integer.parseInt(dados[2])==20) { // equipa
                                switch(Integer.parseInt(dados[1])){
                                    case 0:
                                        CrazyPiece rei= new Rei(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(rei);
                                        break;
                                    case 1:
                                        CrazyPiece rainha = new Rainha(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(rainha);
                                        break;
                                    case 2:
                                        CrazyPiece poneiMagico = new PoneiMagico(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(poneiMagico);
                                        break;
                                    case 3:
                                        CrazyPiece padreDaVila = new PadreDaVila(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(padreDaVila);
                                        break;
                                    case 4:
                                        CrazyPiece torreHor = new TorreHor(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(torreHor);
                                        break;
                                    case 5:
                                        CrazyPiece torreV = new TorreVert(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(torreV);
                                        break;
                                    case 6:
                                        CrazyPiece lebre = new Lebre(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(lebre);
                                        break;
                                    case 7:
                                        CrazyPiece joker = new Joker(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
                                        listaPecas.add(joker);
                                        break;

                                }
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
                                    listaPeca.morri = false;
                                    System.out.println(listaPeca);
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
        int equipaAtual= getIDEquipaAJogar();
        if(xO != xD && yO != yD || xD < sizeTabuleiro && yD < sizeTabuleiro || xD > 0 && yD > 0) {
            for(CrazyPiece peca : listaPecas){ // peça existente nas coordenandas origem
                if (peca.getX() == xO && peca.getY() == yO) {
                    if (peca.getIDEquipa() == equipaAtual) {
                        if (peca.movimento(xO,yO,xD,yD)) {
                            for (CrazyPiece pieces : listaPecas) { // peça existente nas coordenadas destino
                                capturarPeca(pieces,xD,yD);
                                jogadaVPreta++;
                                jogadaVBranca++;
                            }
                            peca.posicaoX(xD);
                            peca.posicaoY(yD);
                            turno++;
                            jogadasSemCaptura++;
                            if(peca.getIDEquipa() == 10){
                                jogadaVPreta++;
                            }else{
                                jogadaVBranca++;
                            }
                            return true;
                        }

                    }
                    if(peca.getIDEquipa() == 10){
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
            if (peca.iDEquipa == 10 && !peca.morri){
                pecaPreta++;
            } else if (peca.iDEquipa == 20 && !peca.morri){
                pecaBranca++;
            }
        }

        if(pecaBranca == 0) {
            vencedor = 1;
            return true;
        }
        if(pecaPreta == 0){
            //vence branco
            vencedor = 0;
            return true;
        }

        if(pecaBranca == 1 && pecaPreta == 1){
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
        for (CrazyPiece listaPeca : listaPecas) {
            if (listaPeca.getX() == x && listaPeca.getY() == y) {
                return listaPeca.iDPeca;
            }
        }
        return 0;
    }
    public int getIDEquipaAJogar() {
        if (turno % 2 == 0) {
            return 10;
        } else {
            return 20;
        }
    }

    public List<String> obterSugestoesJogada(int xO, int yO){

        int yD, xD;
        for(CrazyPiece peca : listaPecas){
            if(peca.getX() == xO && peca.getY() == yO){

            }
        }
        return null;
    }

    public void anularJogadaAnterior(){

    }
    public boolean gravarJogo(File ficheiroDestino){
        String newLine = System.getProperty("line.separator");
        try {
            File output = new File("teste.txt");
            FileWriter writer = new FileWriter(output);
            writer.write(getTamanhoTabuleiro()+ "");
            writer.write(newLine);
            writer.write(numeroDePecas + "");
            writer.write(newLine);
            for(CrazyPiece peca : listaPecas) {
                writer.write(peca.getId() + ":" + peca.getTipoDePeca() + ":" +  peca.getIDEquipa() + ":" + peca.getAlcunha());
                writer.write(newLine);
            }

            System.out.println(listaPecas.size());
            for (int coluna =0 ;coluna < sizeTabuleiro ; coluna++) {
                for(int linha = 0; linha <sizeTabuleiro ; linha++) {
                    boolean pecaEncontrada =true;
                    int idEncontrado = 0;
                    for (CrazyPiece piece : listaPecas) {
                        if (piece.getX() == linha && piece.getY() == coluna) {
                            pecaEncontrada = true;
                            idEncontrado = piece.getId();
                            System.out.println(idEncontrado);
                            break;
                        } else {
                            pecaEncontrada = false;
                        }
                    }
                    System.out.println(pecaEncontrada);
                    if (pecaEncontrada == true) {
                        writer.write( idEncontrado+"");
                    } else {
                        writer.write("0");
                    }
                    if (linha < sizeTabuleiro - 1) {
                        writer.write(":");
                    }
                }

                writer.write(newLine);

            }

            writer.close();
            return true;
        }
        catch(IOException e) {
            System.out.println("Ocorreu um erro.");
            return false;
        }

    }


    public void setTamanho(int sizeTabuleiro){
        this.sizeTabuleiro = sizeTabuleiro;
    }
    /*public void setCrazyPieces(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean morri){
        listaPecas.add(new CrazyPiece(iDPeca, tipoDePeca, iDEquipa, alcunha, x, y, morri));
    }*/



}


