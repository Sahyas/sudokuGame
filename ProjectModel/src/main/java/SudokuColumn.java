import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuContainer {
    public SudokuColumn(final List<SudokuField> elements) {
        super(elements);
    }

    public Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuColumn(fields);
    }
}
