package pl.first.firstjava;

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
}
