

import java.io.FileReader;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class Game {

    static {
        System.loadLibrary("libGame");
    };

    private static final int kolumny = 5;
    private static final int wierszy = 5;
    private static final int rozmiar = kolumny * wierszy;
    static ScriptEngine engine;
    private boolean algInC = false;

    private Scanner scanner;
    private int[] gameBoard;
    int steps = 0;
    Invocable invocable;
    String fileName = "alg2rand.js";
    public static void main(String[] args) throws Exception {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("alg2rand.js"));


        new Game((Invocable) engine).start();

    }

    public Game(Invocable invocable) {
        gameBoard = new int[rozmiar];
        for(int i = 0; i < rozmiar; i++) {
            gameBoard[i] = Player.Puste.getValue();
        }
        scanner = new Scanner(System.in);
        this.invocable = invocable;
    }

    private native int nextStep(int[] gameBoard);

    public void start() throws Exception {

        Player player = Player.Gracz;
        while(true) {

            int move = 0;
            String alg;
            if(steps == rozmiar) {
                write();
                System.out.println("Remis");
                break;
            }

            if(player == Player.Gracz) {
                write();
                System.out.print("Tura "+ player +  "'a:");
                move = scanner.nextInt();
                if(move == -1) {
                    algInC = !algInC;
                    continue;
                }
                if(move == -2){
                    engine.eval(new FileReader("alg1.js"));
                    continue;
                }
                if(move == -3){
                    engine.eval(new FileReader("alg2rand.js"));
                    continue;
                }
                if(!step(player, move)) {
                    continue;
                }

            } else {
                if(algInC) {
                    System.out.println("c++");
                    step(player, nextStep(gameBoard));
                } else {
                    System.out.println("js");
                    double tmp = (Double)(invocable.invokeFunction("nextStep", gameBoard));
                    move = (int) tmp;
                    step(player, move);
                }
            }
            if(won(player, move)) {
                write();
                System.out.println(player + " zdobył zwycięstwo");
                break;
            }
            player = player == Player.Gracz ? Player.Komputer : Player.Gracz;
        }
    }

    private boolean step(Player player, int move) {
        if(move >= rozmiar || move < 0)
            return false;
        if(gameBoard[move] != Player.Puste.getValue()) {
            return false;
        }
        gameBoard[move] = player.getValue();
        steps++;
        return true;
    }



    private void write() {

        for(int i = 0; i < wierszy; i++) {
            String line = "";
            for(int j = 0; j < kolumny; j++) {
                line += gameBoard[i * kolumny + j] == 0 ? "* " : gameBoard[i * kolumny + j] == 1 ? "X " : "O ";
            }
            System.out.println(line);
        }
    }

    private boolean won(Player player, int move) {
        int kolumna = move % kolumny;
        int counter = 0;
        for(int i = 0; i < kolumny; i++) {
            if(gameBoard[kolumna + i * kolumny] == player.getValue()) {
                counter++;
                if(counter >= 4) {
                    return true;
                }
            }
            else {
                counter = 0;
            }
        }

        int wiersz = (int)(move / wierszy);
        counter = 0;

        for(int i = 0; i < wierszy; i++) {
            if(gameBoard[wiersz * kolumny + i] == player.getValue()) {
                counter++;
                if(counter >= 4) {
                    return true;
                }
            }
            else {
                counter = 0;
            }
        }

        int tmpK = kolumna;
        int tmpW = wiersz;

        if(tmpK < tmpW) {
            tmpW -= tmpK;
            tmpK = 0;
        } else {
            tmpK -= tmpW;
            tmpW = 0;
        }

        if(Math.abs(tmpK - tmpW) <= kolumny - 4) {
            counter = 0;
            while(tmpK < kolumny && tmpW < wierszy) {
                if(gameBoard[tmpW * kolumny + tmpK] == player.getValue()) {
                    counter++;
                    if(counter == 4) {
                        return true;
                    }
                }
                else {
                    counter = 0;
                }
                tmpK--;
                tmpW++;
            }
        }

        tmpK = kolumny - kolumna - 1;
        tmpW = wiersz;

        int tmp = Math.min(tmpK, tmpW);

        tmpK = kolumna + tmp;
        tmpW -= tmp;

        if(Math.abs(tmpK - tmpW) >= 3) {
            counter = 0;
            while(tmpK >= 0 && tmpW < wierszy) {
                if(gameBoard[tmpW * kolumny + tmpK] == player.getValue()) {
                    if(++counter == 4) {
                        return true;
                    }
                }
                else {
                    counter = 0;
                }
                tmpK--;
                tmpW++;
            }
        }
        return false;
    }


}
