package pl.first.firstjava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;


public class SudokuBoard {
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

    //WYSWIETLA TABLICE//
    public void print() {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(get(r, d));
                System.out.print("|");
            }
            System.out.print("\n");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board) && Objects.equal(sudokuSolver, that.sudokuSolver);
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
}


