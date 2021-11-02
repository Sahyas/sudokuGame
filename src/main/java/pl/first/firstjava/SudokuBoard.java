package pl.first.firstjava;

public class SudokuBoard {
    private final int[][] board = new int[9][9];
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
    }

    public boolean solveGame() {
        return sudokuSolver.solve(this);
    }

    //GETTER-DO TESTOW//
    public int get(int a, int b) {
        return board[a][b];
    }

    //SETTER-DO TESTOW//
    public void set(int a, int b, int number) {
        if (number >= 0 && number < 10) {
            board[a][b] = number;
        }
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
/*
    public SudokuBox getBox(int x, int y){
        int[] box = new int[9];

    }

    public SudokuBox getRow(int y){
        int[] row = new int[9];
        for(int i=0; i<board.length; i++){
            row[i] = board[i][y];
        }
    }

    public SudokuColumn getColumn(int x){
        int[] column = new int[9];
        for(int i=0; i<board.length; i++){
            column[i] = board[x][i];
        }
    }

 */
}
