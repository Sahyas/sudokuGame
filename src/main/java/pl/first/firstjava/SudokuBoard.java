package pl.first.firstjava;

public class SudokuBoard {
    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;
    public static final int SIZE = 9;

    public SudokuBoard(SudokuSolver sudokuSolver1) {
        sudokuSolver = sudokuSolver1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }


    //GETTER-DO TESTOW//
    public int get(int a, int b) {
        return board[a][b].getFieldValue();
    }

    //SETTER-DO TESTOW//
    public void set(int a, int b, int number) {
        if (number >= 0 && number < 10) {
            board[a][b].setFieldValue(number);
        }
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!getRow(i).verify() || !getColumn(j).verify() || !getBox(i, j).verify()) {
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
                System.out.print(board[r][d].getFieldValue());
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] fields = new SudokuField[SudokuContainer.size];
        int index = 0;
        int startingRow = x - x % 3;
        int startingColumn = y - y % 3;
        for (int i = 0; i < SudokuBox.sizeBox; i++) {
            for (int j = 0; j < SudokuBox.sizeBox; j++) {
                fields[index++] = board[startingRow + i][startingColumn + j];
            }
        }

        return new SudokuBox(fields);
    }

    public SudokuRow getRow(int y) {
        SudokuField[] elements = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            elements[i] = board[y][i];
        }
        return new SudokuRow(elements);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] elements = new SudokuField[SudokuContainer.size];
        for (int i = 0; i < 9; i++) {
            elements[i] = board[i][x];
        }

        return new SudokuColumn(elements);
    }
}
