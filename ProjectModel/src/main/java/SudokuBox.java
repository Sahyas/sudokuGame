import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuContainer {
    public static final int sizeBox = 3;

    public SudokuBox(final List<SudokuField> elements) {
        super(elements);
    }

    public Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuBox(fields);
    }
}
