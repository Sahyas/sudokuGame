package pl.first.firstjava;

import java.util.concurrent.ThreadLocalRandom;

public class SudokuBoard {
    private final int[][] board = new int[9][9];

    public void solveGame() {
        SudokuSolver obj = new SudokuSolver() {
            @Override
            public boolean solve(SudokuBoard board) {
                return false;
            }
        };
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
}
