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

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuBoard board1 = new SudokuBoard(backtracking);
        board1.solveGame();
        SudokuBoard board2 = (SudokuBoard) board1.clone();

        assertTrue(board1.equals(board2)
                && board2.equals(board1));
    }
    @Test
    public void getBoardTest() {
        SudokuBoard testSudokuBoard = new SudokuBoard(backtracking);
        int[][] testTab = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(testTab[i][j], testSudokuBoard.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void clearBoardTest() {
        SudokuBoard testSudokuBoard = new SudokuBoard(backtracking);
        testSudokuBoard.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertNotEquals(0, testSudokuBoard.get(i, j));
            }
        }
        testSudokuBoard.clearBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(0, testSudokuBoard.get(i, j));
            }
        }
    }

    @Test
    public void changeBoardTest() {
        SudokuBoard testSudokuBoard = new SudokuBoard(backtracking);
        Difficulty easyDifficulty = Difficulty.Easy;
        Difficulty mediumDifficulty = Difficulty.Medium;
        Difficulty hardDifficulty = Difficulty.Hard;
        testSudokuBoard.solveGame();
        testSudokuBoard.changeBoard(easyDifficulty);
        int blanks = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testSudokuBoard.get(i, j) == 0) {
                    blanks++;
                }
            }
        }
        assertEquals(25, blanks);
        testSudokuBoard = new SudokuBoard(backtracking);
        testSudokuBoard.solveGame();
        testSudokuBoard.changeBoard(mediumDifficulty);
        blanks = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testSudokuBoard.get(i, j) == 0) {
                    blanks++;
                }
            }
        }
        assertEquals(35, blanks);
        testSudokuBoard = new SudokuBoard(backtracking);
        testSudokuBoard.solveGame();
        testSudokuBoard.changeBoard(hardDifficulty);
        blanks = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testSudokuBoard.get(i, j) == 0) {
                    blanks++;
                }
            }
        }
        assertEquals(45, blanks);
    }

    @Test
    public void checkTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(backtracking);
        assertTrue(board1.check(0,1,1, board1));
        board1.set(0,1,1);
        assertFalse(board1.check(0,1,1, board1));
        board1.set(0,1,0);
        assertTrue(board1.check(1,0,1, board1));
        board1.set(1,0,1);
        assertFalse(board1.check(1,0,1, board1));
        board1.set(1,0,0);

    }
}


