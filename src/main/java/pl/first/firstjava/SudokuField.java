package pl.first.firstjava;

public class SudokuField {
    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(final int value) {
        if (value >= 0 && value < 10) {
            this.value = value;
        }
    }
}
