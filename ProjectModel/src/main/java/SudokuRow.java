import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuContainer {
    public SudokuRow(final List<SudokuField> elements) {
        super(elements);
    }

    public Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuRow(fields);
    }

}

