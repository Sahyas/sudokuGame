package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SudokuBoardTest {
    public SudokuBoardTest() {
    }

    @Test
    public void testSudoku() {
        SudokuBoard obj = new SudokuBoard();
        SudokuBoard obj2 = new SudokuBoard();
        obj.setNumber(0, 0, 5);
        boolean flag = false;
        int[][] firstTry = new int[9][9];
        int[][] secondTry = new int[9][9];
        //check row
        assertFalse(obj.canInsert(0, 8, 5));
        assertTrue(obj.canInsert(0, 8, 6));
        //check column
        assertFalse(obj.canInsert(7, 0, 5));
        assertTrue(obj.canInsert(7, 0, 6));
        //check box
        assertFalse(obj.canInsert(2, 2, 5));
        assertTrue(obj.canInsert(2, 2, 6));
        //check if random
        obj.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                firstTry[i][j] = obj.getNumber(i, j);
            }
        }
        obj2.fillBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                secondTry[i][j] = obj2.getNumber(i, j);
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
}