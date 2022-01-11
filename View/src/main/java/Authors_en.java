import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"Title", "Authors: "},
                {"1", "Szymon Zakrzewski ",},
                {"2", "Piotr Skonieczny "}
        };
    }
}