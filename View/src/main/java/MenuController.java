import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class MenuController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(MenuController.class);
    private Stage stage;
    private String loadFilePath;
    private int levelFlag;
    private Scene scene;
    Locale locale = new Locale("pl");
    private ResourceBundle bundle = ResourceBundle.getBundle("Language", locale);
    private ResourceBundle bundle2 = ResourceBundle.getBundle("Authors", locale);
    @FXML
    Button easyButton;
    @FXML
    Button mediumButton;
    @FXML
    Button hardButton;
    @FXML
    Button exitButton;
    @FXML
    Button loadButton;
    @FXML
    private ToggleButton buttonLanguageEnglish;
    @FXML
    private ToggleButton buttonLanguagePolish;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        easyButton.setOnAction(actionEvent -> openBoard(1));
        mediumButton.setOnAction(actionEvent -> openBoard(2));
        hardButton.setOnAction(actionEvent -> openBoard(3));
        loadButton.setOnAction(actionEvent -> loadClicked());
        label1.setText(bundle2.getString("Title"));
        label2.setText(bundle2.getString("1"));
        label3.setText(bundle2.getString("2"));

        buttonLanguagePolish.selectedProperty().addListener((observable, oldValue, newValue) -> {
            changeLanguage("pl");
        });

        buttonLanguageEnglish.selectedProperty().addListener((observable, oldValue, newValue) -> {
            changeLanguage("en");
        });
    }


    public MenuController() {
        stage = new Stage();
        try {
            LOGGER.info("Setting scene");
            bundle = ResourceBundle.getBundle("Language", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
            loader.setController(this);
            loader.setResources(bundle);
            scene = new Scene(loader.load(), 300, 550);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("applicationTitle");
        } catch (IOException e) {
            LOGGER.error("Error while setting scene", e);
            e.printStackTrace();
        }

    }

    public void showStage() {
        stage.show();
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
        stage.close();
        sudokuController.showStage();
    }

    public void loadClicked() {
        LOGGER.info("Loading file");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(bundle.getString("_loading"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".save", "*.save"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            LOGGER.info("File succesfully loaded");
            loadFilePath = file.getAbsolutePath();
            openBoard(0);
        } else {
            LOGGER.warn("Failed during loading file - file is null");
        }
    }

    public void changeLanguage(String languageCode) {
        this.locale = new Locale(languageCode);
        try {
            LOGGER.info("Chaning language - setting new scene");
            bundle = ResourceBundle.getBundle("Language", locale);
            bundle2 = ResourceBundle.getBundle("Authors", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
            loader.setController(this);
            loader.setResources(bundle);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Sudoku");
        } catch (IOException e) {
            LOGGER.error("Error while setting scene", e);
            e.printStackTrace();
        }
        showStage();
    }

    public String getLoadFilePath() {
        return loadFilePath;
    }

    public void exitClicked() {
        Platform.exit();
        System.exit(0);
    }

    public Locale getLocale() {
        return locale;
    }


}
