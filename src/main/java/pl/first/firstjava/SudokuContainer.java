package pl.first.firstjava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.List;

public abstract class SudokuContainer {
    public static final int size = 9;
    private List<SudokuField> elements;

    public SudokuContainer(final List<SudokuField> elements) {
        if (elements.size() != size) {
            throw new BadContainerSizeException("Length must be 9");
        } else {
            this.elements = elements;
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (elements.get(i).getFieldValue() == elements.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuContainer that = (SudokuContainer) o;
        return Objects.equal(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elements);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("elements", elements)
                .toString();
    }
}
