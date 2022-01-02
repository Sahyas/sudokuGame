import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MenuController menuController = new MenuController();
        menuController.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}