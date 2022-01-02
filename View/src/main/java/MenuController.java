import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.A;

public class MenuController implements Initializable {
    private final Stage thisStage;
    private int levelFlag;
    public Button startButton;
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;
    private static SudokuBoard sudokuBoardFromFile;
    private static String level;
    private String language;
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    @FXML
    private ComboBox comboBoxSystemLang;
    @FXML
    private ComboBox comboBoxSystemDifficult;
    @FXML
    Button easyButton;
    @FXML
    Button mediumButton;
    @FXML
    Button hardButton;
    @FXML
    Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        easyButton.setOnAction(actionEvent -> openBoard(1));
        mediumButton.setOnAction(actionEvent -> openBoard(2));
        hardButton.setOnAction(actionEvent -> openBoard(3));
    }


    public MenuController() {
        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Menu Glowne");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.show();
    }

    public int getLevelFlag() {
        return levelFlag;
    }

    public void setLevelFlag(int levelFlag) {
        this.levelFlag = levelFlag;
    }

    public void openBoard(int levelFlag) {
        setLevelFlag(levelFlag);
        SudokuController sudokuController = new SudokuController(this);
        thisStage.close();
        sudokuController.showStage();
    }

    public void loadClicked(ActionEvent actionEvent) {
    }

    public void exitClicked() {
        Platform.exit();
        System.exit(0);
    }

}
