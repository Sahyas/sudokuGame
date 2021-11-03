package pl.first.firstjava;

public abstract class SudokuContainer {
    public static final int size = 9;
    protected SudokuField[] elements;

    public SudokuContainer(final SudokuField[] elements) {
        if (elements.length == size) {
            this.elements = elements;
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (elements[i].getFieldValue() == elements[j].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
