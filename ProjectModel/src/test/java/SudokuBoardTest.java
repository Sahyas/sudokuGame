import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;

public class SudokuBoardTest {
    private BacktrackingSudokuSolver backtracking;

    @BeforeEach
    public void setUp() {
        backtracking = new BacktrackingSudokuSolver();
    }

    @Test
    public void canInsertTest() {
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
    public void checkBoardTest() {
        SudokuBoard board = new SudokuBoard(backtracking);
        assertFalse(board.checkBoard());
        board.solveGame();
        assertTrue(board.checkBoard());
        board.set(1, 1, 1);
        board.set(1, 2, 1);
        assertFalse(board.checkBoard());
        SudokuBoard test2 = new SudokuBoard(backtracking);
        test2.solveGame();
        test2.set(0, 0, 1);
        test2.set(0, 1, 2);
        test2.set(0, 2, 3);
        test2.set(0, 3, 4);
        test2.set(0, 4, 5);
        test2.set(0, 5, 6);
        test2.set(0, 6, 7);
        test2.set(0, 7, 8);
        test2.set(0, 8, 9);
        test2.set(1, 0, 1);
        test2.set(1, 1, 1);
        assertFalse(test2.checkBoard());
        SudokuBoard test3 = new SudokuBoard(backtracking);
        test3.set(0, 0, 1);
        test3.set(0, 1, 2);
        test3.set(0, 2, 3);
        test3.set(0, 3, 4);
        test3.set(0, 4, 5);
        test3.set(0, 5, 6);
        test3.set(0, 6, 7);
        test3.set(0, 7, 8);
        test3.set(0, 8, 9);
        test3.set(1, 0, 3);
        test3.set(1, 1, 1);
        assertFalse(test3.checkBoard());
        SudokuBoard test4 = new SudokuBoard(backtracking);
        if (board.solveGame()) {
            board.print();
        }
    }

    @Test
    public void setterTest() {
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
    public void hashCodeTest() {
        SudokuBoard board1 = new SudokuBoard(backtracking);
        SudokuBoard board2 = new SudokuBoard(backtracking);
        SudokuBoard board3 = board1;

        assertEquals(board1.equals(board3), true);
        assertEquals(board1.hashCode(), board3.hashCode());
    }

    @Test
    public void equalsTest() {
        SudokuBoard board1 = new SudokuBoard(backtracking);
        SudokuBoard board2 = new SudokuBoard(backtracking);

        SudokuBoard board3 = board1;
        SudokuField field1 = new SudokuField();
        assertEquals(board1.hashCode(), board3.hashCode());
        assertEquals(board1.equals(field1), false);
        assertEquals(board1.equals(board3), true);
        assertEquals(board1.equals(board2),true);
        SudokuBoard board4 = null;
        assertFalse(board3.equals(board4));
    }


    @Test
    public void testToString() {
        ToStringVerifier.forClass(SudokuBoard.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .withIgnoredFields("board", "sudokuSolver")
                .verify();
    }
}


