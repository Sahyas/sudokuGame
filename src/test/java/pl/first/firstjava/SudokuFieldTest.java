package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class SudokuFieldTest {
    private SudokuField testSudokuField;

    @BeforeEach
    void setUp() {
        testSudokuField = new SudokuField();
    }

    @Test
    public void getFieldValueTest() {
        assertEquals(testSudokuField.getFieldValue(), 0);
    }

    @Test
    public void setFieldValueTest() {
        testSudokuField.setFieldValue(5);
        assertEquals(testSudokuField.getFieldValue(), 5);
        testSudokuField.setFieldValue(10);
        assertEquals(testSudokuField.getFieldValue(), 5);
        testSudokuField.setFieldValue(-1);
        assertEquals(testSudokuField.getFieldValue(), 5);
        testSudokuField.setFieldValue(4);
        assertEquals(testSudokuField.getFieldValue(), 4);
    }
}