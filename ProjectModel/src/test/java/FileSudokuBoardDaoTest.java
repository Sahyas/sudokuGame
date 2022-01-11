import exceptions.LoadFromFileException;
import exceptions.WriteToFileException;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSudokuBoardDaoTest {

    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private final ResourceBundle bundle = ResourceBundle.getBundle("bundles.exceptions");

    @Test
    public void writeReadTest() throws Exception {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        try (
                FileSudokuBoardDao dao = (FileSudokuBoardDao) factory.getFileDao("test");
                FileSudokuBoardDao dao2 = (FileSudokuBoardDao) factory.getFileDao("test2")
        ) {
            board1.set(3, 3, 3);
            dao.write(board1);
            SudokuBoard board2 = dao.read();
            assertEquals(board2.get(3, 3), board1.get(3,3));
            assertEquals(board2.get(1, 1), board1.get(1,1));
            assertThrows(LoadFromFileException.class, dao2::read);
        }
    }

    @Test
    public void readExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("name2");
        assertThrows(LoadFromFileException.class, () -> {fileSudokuBoardDao.read();});
    }

    @Test
    public void writeExceptionTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(solver);
        fileSudokuBoardDao = factory.getFileDao("?;/:");
        assertThrows(WriteToFileException.class, () -> {fileSudokuBoardDao.write(board1);});
    }
}

