import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SudokuController implements Initializable {
    @FXML
    private GridPane sudokuBoardGrid;
    private final BacktrackingSudokuSolver test2 = new BacktrackingSudokuSolver();
    private final SudokuBoard test = new SudokuBoard(test2);
    private final MenuController menuController;
    private final Stage stage;
    private Difficulty difficulty;
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;
    private static SudokuBoard sudokuBoardFromFile;
    private static String level;
    private String language;
    private ResourceBundle bundle;
    @FXML
    Button buttonOne;
    @FXML
    Button buttonTwo;
    @FXML
    Button buttonThree;
    @FXML
    Button buttonFour;
    @FXML
    Button buttonFive;
    @FXML
    Button buttonSix;
    @FXML
    Button buttonSeven;
    @FXML
    Button buttonEight;
    @FXML
    Button buttonNine;
    @FXML
    Canvas canvas;
    @FXML
    Button exitButton;
    @FXML
    Button saveButton;
    SudokuBoard gameboard;
    SudokuSolver solver;
    int playerSelectedRow;
    int playerSelectedCol;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (menuController.getLevelFlag() == 1) {
            difficulty = Difficulty.Easy;
        }
        if (menuController.getLevelFlag() == 2) {
            difficulty = Difficulty.Medium;
        }
        if (menuController.getLevelFlag() == 3) {
            difficulty = Difficulty.Medium;
        }
        java.awt.Color awtColor = Color.WHITE;
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        solver = new BacktrackingSudokuSolver();
        gameboard = new SudokuBoard(solver);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int positionY = row * 50 + 2;
                int positionX = col * 50 + 2;
                int width = 46;
                context.setFill(fxColor);
                context.fillRoundRect(positionX, positionY, width, width, 10, 10);
            }
        }
        if (menuController.getLevelFlag() == 0) {
            try {
                gameboard = this.loadGame(menuController.getLoadFilePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public SudokuController(MenuController menuController) {

        this.menuController = menuController;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
            loader.setController(this);
            bundle = ResourceBundle.getBundle("Language", menuController.getLocale());
            loader.setResources(bundle);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Sudoku");

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (difficulty != null) {
            gameboard.clearBoard();
            gameboard.solveGame();
            gameboard.changeBoard(difficulty);
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
        if (menuController.getLevelFlag() == 0) {
            drawOnCanvas(canvas.getGraphicsContext2D());
        }
    }

    public void drawOnCanvas(GraphicsContext context) {
        java.awt.Color awtColor = Color.WHITE;
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int positionY = row * 50 + 2;
                int positionX = col * 50 + 2;
                int width = 46;
                context.setFill(fxColor);
                context.fillRoundRect(positionX, positionY, width, width, 10, 10);
            }
        }
        canvas.setStyle("-fx-background-color: white;");
        awtColor = Color.RED;
        r = awtColor.getRed();
        g = awtColor.getGreen();
        b = awtColor.getBlue();
        a = awtColor.getAlpha();
        opacity = a / 255.0;
        fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        context.setStroke(fxColor);
        context.setLineWidth(5);
        context.strokeRoundRect(playerSelectedCol * 50 + 2,
                playerSelectedRow * 50 + 2, 46, 46, 10, 10);
        int[][] initial = gameboard.getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int positionY = row * 50 + 30;
                int positionX = col * 50 + 20;
                java.awt.Color awtColor2 = Color.BLACK;
                int r2 = awtColor.getRed();
                int g2 = awtColor.getGreen();
                int b2 = awtColor.getBlue();
                int a2 = awtColor.getAlpha();
                double opacity2 = a / 255.0;
                javafx.scene.paint.Color black = javafx.scene.paint.Color.rgb(r, g, b, opacity);
                context.setFill(black);
                if (initial[row][col] != 0) {
                    context.fillText(initial[row][col] + "", positionX, positionY);
                }
            }
        }
    }

    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();
                playerSelectedRow = (int) (mouseY / 50);
                playerSelectedCol = (int) (mouseX / 50);
                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }

    public SudokuBoard loadGame(String name) throws IOException {
        SudokuBoardDaoFactory daoFactory = new SudokuBoardDaoFactory();
        return daoFactory.getFileDao(name).read();
    }

    public void exitGame() {
        menuController.showStage();
        this.stage.close();
    }

    public void badNumberWarning() {
        PopOut.messageBox(bundle.getString("_warning"),
                bundle.getString("_badNumber"), Alert.AlertType.WARNING);
    }


    public void showStage() {
        stage.show();
    }

    public void saveClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("jd");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".save", "*.save"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            saveToFile(file.getAbsolutePath());
        }
    }

    public void saveToFile(String name) {
        SudokuBoardDaoFactory daoFactory = new SudokuBoardDaoFactory();
        daoFactory.getFileDao(name).write(gameboard);
    }

    public void oneClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 1, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 1);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void twoClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 2, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 2);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void threeClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 3, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 3);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void fourClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 4, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 4);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void fiveClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 5, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 5);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void sixClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 6, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 6);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void sevenClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 7, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 7);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void eightClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 8, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 8);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }

    public void nineClicked() {
        if (gameboard.get(playerSelectedRow, playerSelectedCol) == 0
                && gameboard.check(playerSelectedRow, playerSelectedCol, 9, gameboard)) {
            gameboard.set(playerSelectedRow, playerSelectedCol, 9);
            drawOnCanvas(canvas.getGraphicsContext2D());
        } else {
            badNumberWarning();
        }
    }
}
