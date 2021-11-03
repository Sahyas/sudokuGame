package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuContainerTest {
    @Test
    public void verifyValidTest() {
        SudokuRow testSudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertTrue(testSudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        SudokuRow testSudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertFalse(testSudokuRow.verify());
    }
}