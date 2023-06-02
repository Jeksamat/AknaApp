package com.example.msspartialtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AknaApp extends Application {
    public static int numX = 10;
    public static int numY = 10;
    public static Cell[][] grid = new Cell[numX][numY];
    public static List<Cell> Neighbors(Cell cell){
        List<Cell> neighbors = new ArrayList<>();
        int[] coordX = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] coordY = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            int dx = coordX[i];
            int dy = coordY[i];

            int relX = cell.x + dx;
            int relY = cell.y + dy;

            if(relX >= 0 && relX < numX && relY >= 0 && relY < numY) {
                neighbors.add(grid[relX][relY]);
            }
        }
        return neighbors;
    }
    public static void fillGrid() {
        for (int y = 0; y < AknaApp.numY; y++) {
            for (int x = 0; x < AknaApp.numX; x++) {
                AknaApp.grid[x][y] = new Cell(x, y);
            }
        }
    }
    public static void countOfBombs(){
        for (int y = 0; y < AknaApp.numY; y++) {
            for (int x = 0; x < AknaApp.numX; x++) {
                Cell cell = grid[x][y];
                if(cell.value == 9){
                    continue;
                }
                cell.value = (int) Neighbors(cell).stream().filter(nCell -> nCell.value == 9).count();
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        fillGrid();
        FXMLLoader fxmlLoader = new FXMLLoader(AknaApp.class.getResource("Akna-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("MineSeeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}