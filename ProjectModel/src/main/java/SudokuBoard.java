import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuBoard implements Serializable, Cloneable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuBoard.class);
    private List<List<SudokuField>> board;
    private final SudokuSolver sudokuSolver;
    public static final int SIZE = 9;

    public SudokuBoard(SudokuSolver sudokuSolver1) {
        sudokuSolver = sudokuSolver1;
        board = Arrays.asList(new List[SIZE]);
        for (int i = 0; i < SIZE; i++) {
            board.set(i, Arrays.asList(new SudokuField[SIZE]));
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public int rng() {
        return ThreadLocalRandom.current().nextInt(0, 9);
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }


    //GETTER-DO TESTOW//
    public int get(int a, int b) {
        return board.get(a).get(b).getFieldValue();
    }

    //SETTER-DO TESTOW//
    public void set(int a, int b, int number) {
        if (number >= 0 && number < 10) {
            board.get(a).get(b).setFieldValue(number);
        }
    }

    public boolean check(int row, int column, int number, SudokuBoard board) {
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


    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!getRow(i).verify()) {
                    return false;
                }
                if (!getColumn(j).verify()) {
                    return false;
                }
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuContainer.size]);
        int index = 0;
        int startingRow = x - x % 3;
        int startingColumn = y - y % 3;
        for (int i = 0; i < SudokuBox.sizeBox; i++) {
            for (int j = 0; j < SudokuBox.sizeBox; j++) {
                fields.set(index++, board.get(startingRow + i).get(startingColumn + j));
            }
        }

        return new SudokuBox(fields);
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> elements = Arrays.asList(new SudokuField[SudokuContainer.size]);
        for (int i = 0; i < 9; i++) {
            elements.set(i, board.get(y).get(i));
        }
        return new SudokuRow(elements);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> elements = Arrays.asList(new SudokuField[SudokuContainer.size]);
        for (int i = 0; i < 9; i++) {
            elements.set(i, board.get(i).get(x));
        }

        return new SudokuColumn(elements);
    }

    public int[][] getBoard() {
        int[][] tab = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tab[i][j] = board.get(i).get(j).getFieldValue();
            }
        }
        return tab;
    }

    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                set(i, j, 0);
            }
        }
    }

    public void changeBoard(Difficulty difficulty) {
        if (difficulty.equals(Difficulty.Easy)) {
            int i = 25;
            while (i != 0) {
                int a;
                int b;
                a = rng();
                b = rng();
                if (get(a, b) != 0) {
                    set(a, b, 0);
                    i--;
                }
            }
        } else if (difficulty.equals(Difficulty.Medium)) {
            int i = 35;
            while (i != 0) {
                int a;
                int b;
                a = rng();
                b = rng();
                if (get(a, b) != 0) {
                    set(a, b, 0);
                    i--;
                }
            }
        } else if (difficulty.equals(Difficulty.Hard)) {
            int i = 45;
            while (i != 0) {
                int a;
                int b;
                a = rng();
                b = rng();
                if (get(a, b) != 0) {
                    set(a, b, 0);
                    i--;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board, sudokuSolver);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("board", board)
                .add("sudokuSolver", sudokuSolver)
                .toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtracking);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }

        return sudokuBoard;
    }

}


