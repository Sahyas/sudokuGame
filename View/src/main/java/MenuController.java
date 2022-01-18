import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class MenuController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(MenuController.class);
    private Stage stage;
    private int levelFlag;
    private Scene scene;
    Locale locale = new Locale("pl");
    private ResourceBundle bundle = ResourceBundle.getBundle("Language", locale);
    private ResourceBundle bundle2 = ResourceBundle.getBundle("Authors", locale);
    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    JdbcSudokuBoardDao base;
    int chosenIndex;
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
    @FXML
    private Button deleteButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteButton.setOnAction(actionEvent -> {
            try {
                delButtonPress();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        easyButton.setOnAction(actionEvent -> {
            try {
                openBoard(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mediumButton.setOnAction(actionEvent -> {
            try {
                openBoard(2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        hardButton.setOnAction(actionEvent -> {
            try {
                openBoard(3);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        loadButton.setOnAction(actionEvent -> {
            try {
                loadClicked();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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


    public MenuController() throws SQLException {
        stage = new Stage();
        base = factory.getDataBaseDao("DataBase");
        try {
            LOGGER.info("Setting scene");
            bundle = ResourceBundle.getBundle("Language", locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
            loader.setController(this);
            loader.setResources(bundle);
            scene = new Scene(loader.load(), 300, 600);
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

    public void openBoard(int levelFlag) throws SQLException {
        setLevelFlag(levelFlag);
        SudokuController sudokuController = new SudokuController(this);
        stage.close();
        sudokuController.showStage();
    }

    public void loadClicked() throws SQLException {
        List<String> names = base.getBoardNames();
        ChoiceDialog<String> chd = new ChoiceDialog<String>(names.get(0), names);
        chd.setHeaderText(bundle.getString("_loading"));
        Optional<String> result = chd.showAndWait();
        if (result.isPresent()) {
            List<Integer> indexes = base.getBoardIndexes();
            chosenIndex = indexes.get(names.indexOf(result.get()));
            openBoard(0);
            LOGGER.info(bundle.getString("_Load"));
        } else {
            LOGGER.warn("Failed during loading file - file is null");
        }

    }

    public void delButtonPress() throws SQLException {
        List<Integer> indexes = base.getBoardIndexes();
        List<String> names = base.getBoardNames();
        ChoiceDialog<String> chd = new ChoiceDialog<String>(names.get(0), names);
        chd.setHeaderText(bundle.getString("_delete"));
        Optional<String> result = chd.showAndWait();
        if (result.isPresent()) {
            base.delete(indexes.get(names.indexOf(result.get())));
        } else {
            LOGGER.warn("Failed during deleting file - file is null");
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

    public int getChosenIndex() {
        return chosenIndex;
    }

    public void exitClicked() {
        Platform.exit();
        System.exit(0);
    }

    public Locale getLocale() {
        return locale;
    }


}
