import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;

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

    @Test
    public void toStringTest() {
        ToStringVerifier.forClass(SudokuField.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();
    }

    @Test
    public void EqualsTest() {
        EqualsVerifier.simple().forClass(SudokuField.class).verify();

    }

    @Test
    public void HashcodeTest() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setFieldValue(4);
        SudokuField field3 = field1;

        assertEquals(field1.equals(field3), true);
        assertEquals(field1.hashCode(), field3.hashCode());

    }

    @Test
    void compareToTest() {
        SudokuField testSudokuField2 = new SudokuField();

        testSudokuField.setFieldValue(4);
        testSudokuField2.setFieldValue(4);
        assertEquals(testSudokuField.compareTo(testSudokuField2), 0);

        testSudokuField.setFieldValue(8);
        testSudokuField2.setFieldValue(4);
        assertEquals(testSudokuField.compareTo(testSudokuField2), 1);

        testSudokuField.setFieldValue(4);
        testSudokuField2.setFieldValue(8);
        assertEquals(testSudokuField.compareTo(testSudokuField2), -1);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        SudokuField testSudokuField2 = (SudokuField) testSudokuField.clone();

        assertTrue(testSudokuField.equals(testSudokuField2)
                && testSudokuField2.equals(testSudokuField));
    }

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(SudokuField.class).verify();

    }
}