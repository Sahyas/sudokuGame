import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public void randomBoard(SudokuBoard board) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for(int i = 0; i < 5; i++) {
            if(board.get(0,i) == 0 && canInsert(0,i,list.get(i), board)) {
                board.set(0, i, list.get(i));
            }
        }

    }

    public int rng() {
        return ThreadLocalRandom.current().nextInt(1, 10);
    }

    public boolean solve(SudokuBoard board) {
        randomBoard(board);
        int row = 0;
        int col = 0;
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) { //na poczatku cala tabilca to zera
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
            if (canInsert(row, col, num, board)) {
                board.set(row, col, num);
                if (solve(board)) {
                    return true;
                } else {
                    board.set(row, col, 0);
                }
            }
        }
        return false;
    }

    public boolean canInsert(int row, int column, int number, SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            if (board.get(row, i) == number) {
                return false;
            }
        }
        //W KOLUMNE//
        for (int i = 0; i < 9; i++) {
            if (board.get(i, column) == number) {
                return false;
            }
        }
        int sqrt = (int) Math.sqrt(9);
        int boxRowStart = row - row % sqrt;
        int boxColStart = column - column % sqrt;
        //W KWADRAT//
        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++) {
                if (board.get(r, d) == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
