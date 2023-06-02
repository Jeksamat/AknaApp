package com.example.msspartialtest;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class AknaController {
    @FXML
    public GridPane gameGrid;
    private final int cellSize=40;

    public static Text[][] text = new Text[AknaApp.numX][AknaApp.numY];
    public static Rectangle[][] rectangles = new Rectangle[AknaApp.numX][AknaApp.numY];
    public void initialize() {
        for (int y = 0; y < AknaApp.numY; y++) {
            for (int x = 0; x < AknaApp.numX; x++) {
                Cell cell = AknaApp.grid[x][y];
                rectangles[x][y] = createCellRectangle();
                Pane onClick = createOnClickFunction(cell);
                // Add cell components to the grid pane
                gameGrid.add(rectangles[x][y], x, y);
                gameGrid.add(onClick, x, y);
            }
        }
        AknaApp.countOfBombs();
        for (int y = 0; y < AknaApp.numY; y++) {
            for (int x = 0; x < AknaApp.numX; x++) {
                Cell cell = AknaApp.grid[x][y];
                text[x][y] = createCellText(cell);
                gameGrid.add(text[x][y], x, y);
            }
        }
    }

    public Rectangle createCellRectangle() {
        Rectangle rectangle = new Rectangle(cellSize-2, cellSize-2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        return rectangle;
    }
    public Pane createOnClickFunction(Cell cell) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> open(cell));
        return pane;
    }
    public Text createCellText(Cell cell) {
        Text cellText = new Text();
        cellText.setFill(Color.BLACK);
        cellText.setFont(Font.font(18));
        cellText.setText(cell.getText());
        cellText.setVisible(false);
        return cellText;
    }
    public void open(Cell cell) {
        if(cell.isOpen){
            return;
        }
        if(cell.value == 9){
            System.out.println("Game Over");
            return;
        }
        cell.isOpen = true;
        AknaController.text[cell.x][cell.y].setVisible(true);
        AknaController.rectangles[cell.x][cell.y].setFill(null);
        if (AknaController.text[cell.x][cell.y].getText().isEmpty()) {
            for (Cell neighbor : AknaApp.Neighbors(cell)) {
                open(neighbor);
            }
        }
    }
}