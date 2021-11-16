package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    @Test
    public void canInsertTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard obj = new SudokuBoard(backtracking);
        obj.set(0, 0, 5);
        //check row
        assertFalse(backtracking.canInsert(0, 8, 5, obj));
        assertTrue(backtracking.canInsert(0, 8, 6, obj));
        //check column
        assertFalse(backtracking.canInsert(7, 0, 5, obj));
        assertTrue(backtracking.canInsert(7, 0, 6, obj));
        //check box
        assertFalse(backtracking.canInsert(2, 2, 5, obj));
        assertTrue(backtracking.canInsert(2, 2, 6, obj));
    }

    @Test
    public void randomNumberTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
        SudokuBoard board2 = new SudokuBoard(backtracking);
        boolean flag = false;
        int[][] firstTry = new int[9][9];
        int[][] secondTry = new int[9][9];
        //check if random
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                firstTry[i][j] = board.get(i, j);
            }
        }
        board2.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                secondTry[i][j] = board2.get(i, j);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (firstTry[i][j] != secondTry[i][j]) {
                    flag = true;
                    break;
                }
            }
        }
        assertTrue(flag);
    }

    @Test
    public void printTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
        assertFalse(board.checkBoard());
        if (board.solveGame()) {
            board.print();
        }
        assertTrue(board.checkBoard());
    }

    @Test
    public void setterTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
        board.set(0, 0, 5);
        assertEquals(board.get(0, 0), 5);
        //wstawiona liczba spoza zakresu [0,9]
        board.set(0, 0, 10);
        assertEquals(board.get(0, 0), 5);
        board.set(0, 0, 6);
        assertEquals(board.get(0, 0), 6);
        board.set(0, 0, -1);
        assertEquals(board.get(0, 0), 6);
    }

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
        assertFalse(board.checkBoard());
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            board.set(0, i, i + 1);
        }
        assertFalse(board.checkBoard());
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            board.set(0, i, 0);
        }
        for (int j = 0; j < SudokuBoard.SIZE; j++) {
            board.set(j, 0, j + 1);
        }
        assertFalse(board.checkBoard());
        for (int j = 0; j < SudokuBoard.SIZE; j++) {
            board.set(j, 0, 0);
        }
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.set(i, j, num);
                num++;
            }
        }
        assertFalse(board.checkBoard());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.set(i, j, 0);
            }
        }
        board.solveGame();
        assertTrue(board.checkBoard());
    }
}
