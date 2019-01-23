package pt.ulusofona.lp2.crazyChess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static pt.ulusofona.lp2.crazyChess.CrazyPiece.*;

public class Simulador {
    public static int turnoAJogar;
    static int sizeTabuleiro;
    private int numeroDePecas;
    private List<CrazyPiece> listaPecas = null;
    static List<CrazyPiece> listaPecasAux = null;
    static List<String> listaJogadaSugeridaRei = new ArrayList<>();
    static List<UndoHelp> listaDasJogadas = new ArrayList<>();
    static List<Comparable> listaSugetoesAux = null;
    private int vencedor = 3;
    public static int pecaComidaPreta = 0, pecaComidaBranca = 0;
    private int jogadaVBranca = 0;
    private int jogadaVPreta = 0;
    private int jogadaINVBranca = 0, jogadaINVPreta = 0;
    static int jogadasSemCaptura = 0;
    private String mensagem;
    private int turno = 0;
    static int turnoA = 0;
    static int countLebre = 0;
    static int countJoker = 0;
    static int countLinha = 1;

    public void iniciaJogo(File ficheiroInicial) throws InvalidSimulatorInputException,IOException{
        int linhaTabuleiro = 0;
        sizeTabuleiro = 0;
        numeroDePecas = 0;
        listaPecas = new ArrayList<>();
        listaPecasAux = new ArrayList<>();
        listaJogadaSugeridaRei = new ArrayList<>();
        listaDasJogadas = new ArrayList<>();
        pecaComidaBranca = 0;
        pecaComidaPreta = 0;
        jogadaVBranca = 0;
        jogadaVPreta = 0;
        jogadaINVPreta = 0;
        jogadaINVBranca = 0;
        jogadasSemCaptura = 0;
        turno = 0;
        turnoA = 0;
        countJoker = 0;
        countLebre = 0;
        vencedor = 3;
        countLinha = 1;
        try {

            Scanner leitorFicheiro = new Scanner(ficheiroInicial);
            sizeTabuleiro = Integer.parseInt(leitorFicheiro.nextLine());
            if (!(sizeTabuleiro >= 4 && sizeTabuleiro <= 12)) {
                throw new IOException();
            }
            countLinha++;
            numeroDePecas = Integer.parseInt(leitorFicheiro.nextLine());
            if (!(numeroDePecas < sizeTabuleiro * sizeTabuleiro)) {
                throw new IOException();
            }
            countLinha++;
            for (int i = 0; i < numeroDePecas; i++) {
                String[] dados = leitorFicheiro.nextLine().split(":");

                if(dados.length !=4){
                    throw new InvalidSimulatorInputException(countLinha,dados.length);
                }
                countLinha++;
                if (!listaPecas.contains(Integer.parseInt(dados[0])) && Integer.parseInt(dados[0]) >= 1) { // peça repetida
                     if (Integer.parseInt(dados[1]) >= 0 && Integer.parseInt(dados[1]) <= 10) { // tipo peça
                        if (Integer.parseInt(dados[2]) == 10 || Integer.parseInt(dados[2]) == 20) { // equipa
                            switch (Integer.parseInt(dados[1])) {
                                case 0:
                                    CrazyPiece rei = new Rei(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), dados[3]);
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
                countLinha++;
            }
            //por as peças nas posiçoes
            for (int linha = 0; linha < sizeTabuleiro; linha++) {
                String[] dados = leitorFicheiro.nextLine().split(":", sizeTabuleiro);
                for (int coluna = 0; coluna < sizeTabuleiro; coluna++) {
                    if (Integer.parseInt(dados[coluna]) != 0) {
                        for (CrazyPiece listaPeca : listaPecas) {
                            if (listaPeca.getId() == Integer.parseInt(dados[coluna])) {
                                listaPeca.posicaoX(coluna);
                                listaPeca.posicaoY(linhaTabuleiro);
                                listaPeca.capturada = false;
                                System.out.println(listaPeca);
                            }
                        }
                    }
                }
                linhaTabuleiro++;
            }

            // ler jogo gravado
            if (leitorFicheiro.hasNextLine()) {
                String[] dados = leitorFicheiro.nextLine().split(":");
                String[] dadosVirgula  = leitorFicheiro.nextLine().split(",");
                if (Integer.parseInt(dados[0]) == 10 || Integer.parseInt(dados[0]) == 20 && Integer.parseInt(dados[1]) >= 0 && Integer.parseInt(dados[2]) >= 0 && Integer.parseInt(dados[3]) >= 0 && Integer.parseInt(dados[4]) >= 0 && Integer.parseInt(dados[5]) >= 0 && Integer.parseInt(dados[6]) >= 0) {
                    turno = Integer.parseInt(dados[1]) + Integer.parseInt(dados[4]);
                    jogadaVPreta = Integer.parseInt(dados[1]);
                    pecaComidaPreta = Integer.parseInt(dados[2]);
                    jogadaINVPreta = Integer.parseInt(dados[3]);
                    jogadaVBranca = Integer.parseInt(dados[4]);
                    pecaComidaBranca = Integer.parseInt(dados[5]);
                    jogadaINVBranca = Integer.parseInt(dados[6]);
                    jogadasSemCaptura = Integer.parseInt(dadosVirgula[1]);
                }

            }

            listaPecasAux.addAll(listaPecas);
            for (CrazyPiece listaPeca : listaPecasAux) {
                System.out.println(listaPeca);
            }

            leitorFicheiro.close();

        } catch (FileNotFoundException exception) {
            throw new IOException();
        }
        catch(InvalidSimulatorInputException e){
            System.out.println(e.getDescricaoProblema());
        }
    }

    public int getTamanhoTabuleiro() {
        return sizeTabuleiro;
    }


    public void jogadaInvalida() {
        if (getIDEquipaAJogar() == 10) {
            jogadaINVPreta++;
        } else {
            jogadaINVBranca++;
        }
    }

    public void jogadaValida() {
        if (getIDEquipaAJogar() == 10) {
            jogadaVPreta++;
        } else {
            jogadaVBranca++;
        }
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {
        int equipaAtual = getIDEquipaAJogar();
        CrazyPiece pecaMexe;

        if (xD < 0 || yD < 0 || xD > sizeTabuleiro - 1 || yD > sizeTabuleiro - 1) {
            jogadaInvalida();
            return false;
        }

        if (xD == xO && yD == yO) {
            jogadaInvalida();
            return false;
        }


        pecaMexe = pecaNaPosicao(xO, yO);
        // nao encontrou peça
        if (pecaMexe == null) {
            jogadaInvalida();
            return false;
        }

        if (pecaMexe.getIDEquipa() != equipaAtual) {
            jogadaInvalida();
            return false;
        }

        if (pecaMexe.movimento(pecaMexe, equipaAtual, xO, yO, xD, yD)) {
            //verifica se ha peça para comer
            CrazyPiece pecaNoDestino = pecaNaPosicao(xD, yD);
            jogadasSemCaptura++;
            if (pecaNoDestino != null) {
                if (pecaNoDestino.getIDEquipa() == pecaMexe.getIDEquipa()) {
                    jogadaInvalida();
                    return false;
                } else {
                    capturarPeca(pecaNoDestino, xD, yD);
                }
            }
            jogadaValida();
            pecaMexe.posicaoX(xD);
            pecaMexe.posicaoY(yD);
            turno++;
            turnoA++;
            countJoker++;
            countLebre++;
            return true;
        }
        jogadaInvalida();
        return false;


    }

    public List<CrazyPiece> getPecasMalucas() {
        return listaPecas;
    }

    public boolean jogoTerminado() {
        int pecaPreta = 0, pecaBranca = 0, reiBranco = 0, reiPreto = 0;
        for (CrazyPiece peca : listaPecas) {
            if (peca.iDEquipa == 10 && !peca.capturada) {
                pecaPreta++;
                if (peca.tipoDePeca == 0) {
                    reiPreto++;
                }
            } else if (peca.iDEquipa == 20 && !peca.capturada) {
                pecaBranca++;
                if (peca.tipoDePeca == 0) {
                    reiBranco++;
                }
            }
        }


        if (reiPreto == 0) {
            //vence branco
            vencedor = 0;
            return true;
        }
        if (reiBranco == 0) {
            //vence preto
            vencedor = 1;
            return true;
        }

        if (pecaBranca == 1 && pecaPreta == 1) {
            if (reiBranco == 1 && reiPreto == 1) {
                vencedor = 3;
                return true;
            }

        }

        if (pecaComidaPreta + pecaComidaBranca > 0 && jogadasSemCaptura == 10) {
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

    public List<String> getResultados() {
            List<String> resultados = new ArrayList<>();
            resultados.add("JOGO DE CRAZY CHESS");
        if (vencedor == 0) {
            mensagem = "VENCERAM AS BRANCAS";
        }
        if (vencedor == 1) {
            mensagem = "VENCERAM AS PRETAS";

        }
        if (vencedor == 3) {
            mensagem = "EMPATE";
        }
        resultados.add("Resultado: " + mensagem);
        resultados.add("---");
        resultados.add("Equipa das Pretas");
        resultados.add(" Capturas: " + pecaComidaBranca);
        resultados.add(" Jogadas válidas: " + jogadaVPreta);
        resultados.add(" Tentativas inválidas: " + jogadaINVPreta);
        resultados.add("Equipa das Brancas");
        resultados.add(" Capturas: " + pecaComidaPreta);
        resultados.add(" Jogadas válidas: " + jogadaVBranca);
        resultados.add(" Tentativas inválidas: " + jogadaINVBranca);
        return resultados;

    }

    public CrazyPiece pecaNaPosicao(int xO, int yO) {
        CrazyPiece pecaNaPosicao = null;
        for (CrazyPiece p : listaPecasAux) {
            if (p.getX() == xO && p.getY() == yO) {
                pecaNaPosicao = p;
            }
        }
        return pecaNaPosicao;
    }

    public int getIDPeca(int x, int y) {
        for (CrazyPiece listaPeca : listaPecas) {
            if (listaPeca.getX() == x && listaPeca.getY() == y) {
                return listaPeca.iDPeca;
            }
        }
        return 0;
    }

    public int getIDEquipaAJogar() {
        if (turno % 2 == 0) {
            turnoAJogar = 10;
            return 10;
        } else {
            turnoAJogar = 20;
            return 20;
        }
    }

    public boolean processaSugestao(int xO, int yO, int xD, int yD){
        int equipaAJogar = getIDEquipaAJogar();
        if (xO != xD && yO != yD || xD < 0 || yD < 0 || xD > sizeTabuleiro - 1 || yD > sizeTabuleiro - 1 ) {
            for(CrazyPiece peca:listaPecas){
                if(peca.getX() == xO && peca.getY() == yO){
                    if(peca.getIDEquipa() == equipaAJogar){
                        if(peca.movimento(peca,equipaAJogar,xO,yO,xD,yD)){
                            for(CrazyPiece piece : listaPecas){
                                if(piece.getX() == xD && piece.getY() == yD) {
                                    if (piece.getIDEquipa() != peca.getIDEquipa()) {
                                        return true;
                                    }
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    List<Comparable> obterSugestoesJogada(int xO, int yO){
    listaSugetoesAux = new ArrayList<>();
        for (CrazyPiece piece : listaPecasAux) {
            if (piece.getX() == xO && piece.getY() == yO ) {
                if (piece.getIDEquipa() == getIDEquipaAJogar()) {
                    for (int y = 0; y < sizeTabuleiro; y++) {
                        for (int x = 0; x < sizeTabuleiro; x++) {
                            if (processaSugestao(xO, yO, x, y)) {
                                listaSugetoesAux.add(x + "," + y);
                            }
                        }
                    }
                } else {

                    listaSugetoesAux.add("Pedido inválido");
                }
            }
        }
        return listaSugetoesAux;
    }


    public void anularJogadaAnterior() {
        for (UndoHelp undo : listaDasJogadas) {
            if (undo.getTurnoAnterior() == turnoA - 1) {
                for (CrazyPiece peca : listaPecas) {
                    if (undo.getiDPecaQueAnda() == peca.getId()) {
                        peca.posicaoX(undo.xDaQueAnda);
                        peca.posicaoY(undo.yDaQueAnda);
                        turno--;
                        if (peca.getIDEquipa() == 10) {
                            jogadaVPreta--;
                        } else {
                            jogadaVBranca--;
                        }
                    }
                    if (undo.getiDPecaQueMorre() != 0) {
                        if (undo.getiDPecaQueMorre() == peca.getId()) {
                            peca.posicaoX(undo.xDaQueMorre);
                            peca.posicaoY(undo.yDaQueMorre);
                            if (peca.getIDEquipa() == 10) {
                                pecaComidaPreta--;
                            } else {
                                pecaComidaBranca--;
                            }
                        }
                    }

                }
            }
        }

    }

    public boolean gravarJogo(File ficheiroDestino) {
        String newLine = System.getProperty("line.separator");
        try {
            File output = new File("teste.txt");
            FileWriter writer = new FileWriter(output);
            writer.write(getTamanhoTabuleiro() + "");
            writer.write(newLine);
            writer.write(numeroDePecas + "");
            writer.write(newLine);
            for (CrazyPiece peca : listaPecas) {
                writer.write(peca.getId() + ":" + peca.getTipoDePeca() + ":" + peca.getIDEquipa() + ":" + peca.getAlcunha());
                writer.write(newLine);
            }

            System.out.println(listaPecas.size());
            for (int coluna = 0; coluna < sizeTabuleiro; coluna++) {
                for (int linha = 0; linha < sizeTabuleiro; linha++) {
                    boolean pecaEncontrada = true;
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
                        writer.write(idEncontrado + "");
                    } else {
                        writer.write("0");
                    }
                    if (linha < sizeTabuleiro - 1) {
                        writer.write(":");
                    }
                }

                writer.write(newLine);

            }
            writer.write(getIDEquipaAJogar() + ":" + jogadaVPreta + ":" + pecaComidaBranca + ":" + jogadaINVPreta + ":" + jogadaVBranca + ":" + pecaComidaPreta + ":" + jogadaINVBranca + "," + jogadasSemCaptura);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            return false;
        }

    }



    public Map<String,List<String>> getEstatisticas(){
        Map<String,List<String>> mapaEstatisticas = new HashMap<>();

        mapaEstatisticas.put("top5Capturas",top5Capturas());
        mapaEstatisticas.put("top5Pontos", top5Pontos());
        mapaEstatisticas.put("pecasMais5Capturas",pecasMais5Capturas());


        return mapaEstatisticas;
    }

    public List<String> top5Capturas(){
        List<String> top5capturas;

        top5capturas = listaPecasAux.stream()
                .sorted((p1,p2) -> p2.getNrCapturas() - p1.getNrCapturas())
                .limit(5)
                .map((p1) -> p1.getIDEquipa() + ":"+ p1.getAlcunha()+ ":" + p1.getNrPontos()+ ":"+ p1.getNrCapturas())
                .collect(Collectors.toList());

        return top5capturas;
    }

    public List<String>  top5Pontos(){
        List<String> top5Pontos;

        top5Pontos = listaPecasAux.stream()
                .sorted((p1,p2)-> p2.getNrPontos() - p1.getNrPontos())
                .limit(5)
                .map((p1) -> p1.getIDEquipa() + ":"+ p1.getAlcunha()+ ":" + p1.getNrPontos()+ ":"+ p1.getNrCapturas())
                .collect(Collectors.toList());

        return top5Pontos;
    }

    public List<String> pecasMais5Capturas(){
        List<String> pecasMais5Capturas;

        pecasMais5Capturas = listaPecasAux.stream()
                .filter((p1)-> p1.getNrCapturas()>5)
                .map((p1) -> p1.getIDEquipa() + ":"+ p1.getAlcunha()+ ":" + p1.getNrPontos()+ ":"+ p1.getNrCapturas())
                .collect(Collectors.toList());
        return pecasMais5Capturas;
    }






    public void setTamanho(int sizeTabuleiro){
        this.sizeTabuleiro = sizeTabuleiro;
    }

    public void setRei(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new Rei(iDPeca,tipoDePeca,iDEquipa, x, y, capturada));
    }
    public void setRainha(int iDPeca, int tipoDePeca, int iDEquipa ,  String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new Rainha(iDPeca, tipoDePeca, iDEquipa, x, y, capturada));
    }
    public void setPoneiMagicos(int iDPeca, int tipoDePeca, int iDEquipa, int x, int y, boolean capturada){
        listaPecasAux.add(new PoneiMagico(iDPeca, tipoDePeca, iDEquipa, x, y, capturada));
    }
    public void setPadredaVila(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new PadreDaVila(iDPeca, tipoDePeca, iDEquipa, x, y, capturada));
    }
    public void setLebre(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new Lebre(iDPeca, tipoDePeca, iDEquipa, x, y, capturada));
    }
    public void setTorreV(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new TorreVert(iDPeca, tipoDePeca, iDEquipa, x, y, capturada));
    }
    public void setTorreH(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new TorreHor(iDPeca, tipoDePeca, iDEquipa, x, y, capturada));
    }
    public void setJoker(int iDPeca, int tipoDePeca, int iDEquipa, String alcunha, int x, int y, boolean capturada){
        listaPecasAux.add(new Joker(iDPeca, tipoDePeca, iDEquipa,alcunha, x, y, capturada));
    }

}