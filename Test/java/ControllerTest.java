import com.example.msspartialtest.AknaApp;
import com.example.msspartialtest.AknaController;
import com.example.msspartialtest.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest {
    private AknaController controller;

    @BeforeEach
    public void setup() {
        controller = new AknaController();
        controller.gameGrid = new GridPane();
        AknaApp.numX = 3;
        AknaApp.numY = 3;
        AknaApp.grid = new Cell[AknaApp.numX][AknaApp.numY];
        AknaApp.fillGrid();
        AknaController.text = new Text[AknaApp.numX][AknaApp.numY];
        AknaController.rectangles = new Rectangle[AknaApp.numX][AknaApp.numY];
    }

    @Test
    public void testInitialize() {
        controller.initialize();
        //*3 children for there are 3 nodes in 1 cell
        Assertions.assertEquals((AknaApp.numX * AknaApp.numY)*3, controller.gameGrid.getChildren().size());
    }

    @Test
    public void testCreateCellRectangle() {
        Rectangle rectangle = controller.createCellRectangle();

        Assertions.assertEquals(Color.WHITE, rectangle.getFill());
        Assertions.assertEquals(Color.BLACK, rectangle.getStroke());
        Assertions.assertEquals(1, rectangle.getStrokeWidth());
    }

    @Test
    public void testOpenCellWithBomb() {
        Cell[][] grid = AknaApp.grid;
        grid[0][0].value = 9;

        controller.open(grid[0][0]);

        Assertions.assertFalse(grid[0][0].isOpen);
    }

    @Test
    public void testOpenCellWithoutBomb() {
        Cell[][] grid = AknaApp.grid;
        grid[0][0].value = 3;
        grid[0][1].value = 9;
        grid[1][0].value = 9;
        grid[1][1].value = 9;
        AknaController.text[0][0].setText(grid[0][0].getText());
        AknaController.text[2][0].setText(grid[2][0].getText());

        controller.open(grid[0][0]);
        controller.open(grid[2][0]);

        Assertions.assertTrue(grid[0][0].isOpen);
        Assertions.assertTrue(grid[2][0].isOpen);
    }
    @Test
    public void testCreateCellText() {
        Cell cell = new Cell(0, 0);
        Text cellText = controller.createCellText(cell);

        Assertions.assertEquals(Color.BLACK, cellText.getFill());
        Assertions.assertEquals(18, cellText.getFont().getSize());
        Assertions.assertEquals(cell.getText(), cellText.getText());
        Assertions.assertFalse(cellText.isVisible());
    }
}