import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BacktrackingSudokuSolverTest {
    public BacktrackingSudokuSolverTest() {
    }

    @Test
    public void solveTest() {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
//        assertTrue(board.solveGame());
    }
}
