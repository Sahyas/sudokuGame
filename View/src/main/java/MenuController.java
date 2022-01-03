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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    private Stage stage;
    private String loadFilePath;
    private int levelFlag;
    public Button startButton;
    private Scene scene;
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;
    private static SudokuBoard sudokuBoardFromFile;
    private static String level;
    private String language;
    Locale locale = new Locale("pl");
    private ResourceBundle bundle = ResourceBundle.getBundle("Language", locale);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        easyButton.setOnAction(actionEvent -> openBoard(1));
        mediumButton.setOnAction(actionEvent -> openBoard(2));
        hardButton.setOnAction(actionEvent -> openBoard(3));
        loadButton.setOnAction(actionEvent -> loadClicked());
        final ToggleGroup group = new ToggleGroup();
        buttonLanguagePolish.setToggleGroup(group);
        buttonLanguagePolish.setSelected(true);
        buttonLanguageEnglish.setToggleGroup(group);

        buttonLanguagePolish.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            changeLanguage("pl");
            stage.close();
            stage = new Stage();
            try {
                bundle = ResourceBundle.getBundle("Language", locale);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
                loader.setController(this);
                loader.setResources(bundle);
                scene = new Scene(loader.load(), 300, 450);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("applicationTitle");
            } catch (IOException e) {
                e.printStackTrace();
            }
            showStage();
        }));

        buttonLanguageEnglish.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            changeLanguage("en");
            stage.close();
            stage = new Stage();
            try {
                bundle = ResourceBundle.getBundle("Language", locale);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
                loader.setController(this);
                loader.setResources(bundle);
                scene = new Scene(loader.load(), 300, 450);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("applicationTitle");
            } catch (IOException e) {
                e.printStackTrace();
            }
            showStage();
        }));
    }


    public MenuController() {
        stage = new Stage();
        try {
            bundle = ResourceBundle.getBundle("Language", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
            loader.setController(this);
            loader.setResources(bundle);
            scene = new Scene(loader.load(), 300, 450);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("applicationTitle");
        } catch (IOException e) {
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("jd");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".save", "*.save"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            loadFilePath = file.getAbsolutePath();
            openBoard(0);
        }
    }

    public void changeLanguage(String languageCode) {
        if (languageCode.length() == 2) {
            this.locale = new Locale(languageCode);
        }
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
