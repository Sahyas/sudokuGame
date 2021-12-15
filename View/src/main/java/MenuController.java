import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {

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
    Button buttonEasy;
    @FXML
    Button buttonMedium;
    @FXML
    Button buttonHard;
    @FXML
    Button buttonNewGame;
    SudokuBoard gameboard;
    SudokuSolver solver;
    Difficulty difficulty;
    boolean flag = true;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
    }

    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 450, 450);
        canvas.setStyle("-fx-background-color: white;");
        java.awt.Color awtColor = Color.RED;
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        context.setStroke(fxColor);
        context.setLineWidth(5);
        context.strokeRoundRect(playerSelectedCol * 50 + 2,
                playerSelectedRow * 50 + 2, 46, 46, 10, 10);
        int[][] initial = gameboard.getBoard();
        // for loop is the same as before
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

    int playerSelectedRow;
    int playerSelectedCol;

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

    public void easyClicked() {
        difficulty = Difficulty.Easy;
    }

    public void mediumClicked() {
        difficulty = Difficulty.Medium;
    }

    public void hardClicked() {
        difficulty = Difficulty.Hard;
    }

    public void newGameClicked() {
        gameboard.clearBoard();
        drawOnCanvas(canvas.getGraphicsContext2D());
        gameboard.solveGame();
        gameboard.changeBoard(difficulty);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }
}