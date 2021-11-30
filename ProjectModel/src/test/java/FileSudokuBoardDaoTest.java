import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSudokuBoardDaoTest {

    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    public void writeReadTest() throws Exception {
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(backtracking);
        try (
                FileSudokuBoardDao dao = (FileSudokuBoardDao) factory.getFileDao("test")
        ) {
            board1.set(3, 3, 3);
            dao.write(board1);
            SudokuBoard board2 = dao.read();
            assertEquals(board2.get(3, 3), board1.get(3,3));
            assertEquals(board2.get(1, 1), board1.get(1,1));
        }
    }
}

