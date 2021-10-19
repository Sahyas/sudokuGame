package pl.first.firstjava;

import java.util.concurrent.ThreadLocalRandom;

public class SudokuBoard {
    private final int[][] board = new int[9][9];

    //GENERATOR LICZB LOSOWYCH//
    public int rng() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }

    //GETTER-DO TESTOW//
    public int getNumber(int a, int b) {
        return board[a][b];
    }

    //SETTER-DO TESTOW//
    public void setNumber(int a, int b, int number) {
        board[a][b] = number;
    }

    //CZY MOGE WSTAWIC LICZBE//
    //W RZAD//
    public boolean canInsert(int row, int column, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }
        //W KOLUMNE//
        for (int i = 0; i < 9; i++) {
            if (board[i][column] == number) {
                return false;
            }
        }
        int sqrt = (int) Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = column - column % sqrt;
        //W KWADRAT//
        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++) {
                if (board[r][d] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    //WYPELNIA TABLICE LOSOWYMI LICZBAMI//
    public boolean fillBoard() {
        int row = 0; //to nie ma znaczenia
        int col = 0; // -||-
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) { //na poczatku cala tabilca to zera
                    row = i;            //jak znajdzie 0 to lapie wspolrzedne
                    col = j;            //rzedu i kolumny
                    isEmpty = false;    //a pole zmienia na nie puste
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 0; num <= 9; num++) {
            num = rng();
            if (canInsert(row, col, num)) {
                board[row][col] = num;
                if (fillBoard()) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    //WYSWIETLA TABLICE//
    public void print() {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    //MAIN//
    public static void main(String[] args) {
        SudokuBoard obj = new SudokuBoard();
        if (obj.fillBoard()) {
            obj.print();
        }
    }
}
