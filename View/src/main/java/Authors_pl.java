import java.util.ListResourceBundle;

public class Authors_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"Title", "Autorzy: "},
                {"1", "Szymon Zakrzewski ",},
                {"2", "Piotr Skonieczny "}
        };
    }
}