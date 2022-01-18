import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;


public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;

    private boolean isEditable = false;

    public SudokuField() {
    }

    public SudokuField(int value) {
        if (value >= 1 && value <= 9) {
            this.value = value;
        }
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
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
                .add("isEditable", isEditable)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuField)) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value)
                .append(isEditable, that.isEditable).isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value, isEditable);
    }

    @Override
    public SudokuField clone() {
        try {
            SudokuField clone = (SudokuField) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    @Override
    public int compareTo(SudokuField o) {
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
