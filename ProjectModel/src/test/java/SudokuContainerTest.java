import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuContainerTest {
    @Test
    public void verifyValidTest() {
        SudokuRow testSudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        assertTrue(testSudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        SudokuRow testSudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(8)));
        assertFalse(testSudokuRow.verify());
    }

    @Test
    public void verifySizeTest() {
        SudokuRow testSudokuRow = new SudokuRow(Arrays.asList(
                    new SudokuField(1),
                    new SudokuField(2),
                    new SudokuField(3),
                    new SudokuField(4),
                    new SudokuField(5),
                    new SudokuField(6),
                    new SudokuField(7),
                    new SudokuField(8),
                    new SudokuField(10)));
    }
    @Test
    public void toStringTest() {
        ToStringVerifier.forClass(SudokuRow.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();

        ToStringVerifier.forClass(SudokuColumn.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();

        ToStringVerifier.forClass(SudokuBox.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();
    }

    @Test
    public void EqualsTest() {
        EqualsVerifier.simple().forClass(SudokuRow.class).verify();
        EqualsVerifier.simple().forClass(SudokuColumn.class).verify();
        EqualsVerifier.simple().forClass(SudokuBox.class).verify();
    }

    @Test
    public void HashcodeTest(){
        BacktrackingSudokuSolver backtracking = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtracking);
        board.solveGame();
        SudokuContainer con1 = board.getColumn(1);
        SudokuContainer con2 = board.getRow(1);
        SudokuContainer con3 = con1;

        assertEquals(con1.equals(con3),true);
        assertEquals(con1.hashCode(),con3.hashCode());

    }
}
