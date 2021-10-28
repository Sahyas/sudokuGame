package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SudokuBoardTest {
    public SudokuBoardTest() {
    }

    @Test
    public void canInsertTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard obj = new SudokuBoard(backtracking);
        obj.set(0, 0, 5);
        //check row
        assertFalse(obj.canInsert(0, 8, 5));
        assertTrue(obj.canInsert(0, 8, 6));
        //check column
        assertFalse(obj.canInsert(7, 0, 5));
        assertTrue(obj.canInsert(7, 0, 6));
        //check box
        assertFalse(obj.canInsert(2, 2, 5));
        assertTrue(obj.canInsert(2, 2, 6));
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
        board.solveGame();
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
        assertTrue(board.solveGame());
        if (board.solveGame()) {
            board.print();
        }
    }

    @Test
    public void setterTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
        board.set(0, 0, 5);
        //wstawiona liczba spoza zakresu [0,9]
        board.set(0, 0, 10);
        assertEquals(board.get(0, 0), 5);
        //wybrana nieodpowiednia kolumna
        board.set(10, 1, 5);
        //wybrany nieodpowiedni rząd
        board.set(1, 10, 5);
    }

}


