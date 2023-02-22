package game.engine.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Minesweeper extends Application{
  private int ROWS = 15;
  private int COLUMNS = 15;
  private double SIZE = 400 / ROWS;
  private double DIFFICULTY = 0.2;
  private GridPane grid = new GridPane();

  public void start(Stage stage) throws Exception {
    stage.setTitle("Minesweeper by Derek Porcelli");
    stage.getIcons().add(new Image("Flagged.png"));
    
    VBox vbox = new VBox();

    Label rowsText = new Label("Rows");
    TextField rows = new TextField();
    rows.setText("15");
    Label columnsText = new Label("Columns");
    TextField columns = new TextField();
    columns.setText("15");
    Button copy = new Button("Copy");
    copy.setOnAction(e -> {
      columns.setText((String)String.valueOf(rows.getText()));
    });
    Label difficultyText = new Label("Difficulty (0-1)");
    TextField difficulty = new TextField();
    difficulty.setText("0.2");
    Button update = new Button("Update");
    update.setOnAction(e -> {
      grid.getChildren().clear(); 

      ROWS = Integer.parseInt(rows.getText());
      COLUMNS = Integer.parseInt(columns.getText());
      DIFFICULTY = Double.parseDouble(difficulty.getText());
      SIZE = 400/ROWS;

      Minefield minefield = new Minefield(ROWS, COLUMNS, SIZE, DIFFICULTY);

      for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLUMNS; j++) {
          grid.add(minefield.getTile(i, j), i, j);
        }
      }
      stage.sizeToScene();
    });

    vbox.getChildren().addAll(
      grid,
      rowsText,
      rows, 
      copy,
      columnsText,
      columns, 
      difficultyText,
      difficulty, 
      update
      );

    Scene scene = new Scene(vbox);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}

//minesweeper made by Derek Porcelli