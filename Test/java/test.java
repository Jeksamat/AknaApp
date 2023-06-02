import com.example.msspartialtest.AknaController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.msspartialtest.AknaApp;
import com.example.msspartialtest.Cell;

public class test {
    private AknaApp game;

    @BeforeEach
    public void setup() {
        game = new AknaApp();
        game.fillGrid();
    }

    @Test
    public void testNeighbors() {
        Cell[][] grid = game.grid;
        Cell centerCell = grid[5][5];
        int expectedNeighbors = 8;
        Assertions.assertEquals(expectedNeighbors, game.Neighbors(centerCell).size());
    }

    @Test
    public void testCountOfBombs() {
        Cell[][] grid = game.grid;
        grid[0][0].value = 9;
        grid[0][1].value = 9;
        grid[1][0].value = 9;

        game.countOfBombs();

        Assertions.assertEquals(9, grid[0][0].value);
        Assertions.assertEquals(9, grid[0][1].value);
        Assertions.assertEquals(9, grid[1][0].value);
        Assertions.assertEquals(0, grid[9][9].value);
        Assertions.assertEquals(3, grid[1][1].value);
    }
}