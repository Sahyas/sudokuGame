package pl.first.firstjava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuField {
    private int value;
    private SudokuRow[] row = new SudokuRow[9];
    private SudokuColumn[] column = new SudokuColumn[9];
    private SudokuBox[] box = new SudokuBox[9];

    public SudokuField() {

    }

    public SudokuField(int value) {
        if(value>=1 && value <=9){
            this.value = value;
        }
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(final int value) {
        if (value >= 0 && value < 10) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
