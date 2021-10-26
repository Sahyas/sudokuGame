package pl.first.firstjava;

import java.util.concurrent.ThreadLocalRandom;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public int rng() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }

    public boolean solve(SudokuBoard board) {
        if (board.getNumber(0, 0) == 0) {
            board.setNumber(0, 0, rng());
            int random = rng();
            if (board.getNumber(0, 0) != random) {
                board.setNumber(0, 5, random);
            }
        }
        if (board.getNumber(8, 8) == 0) {
            board.setNumber(8, 8, rng());
        }
        int row = 0; //to nie ma znaczenia
        int col = 0; // -||-
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getNumber(i, j) == 0) { //na poczatku cala tabilca to zera
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
            if (board.canInsert(row, col, num)) {
                board.setNumber(row, col, num);
                if (solve(board)) {
                    return true;
                } else {
                    board.setNumber(row, col, 0);
                }
            }
        }
        return false;
    }
}
