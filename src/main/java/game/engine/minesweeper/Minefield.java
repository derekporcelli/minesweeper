package game.engine.minesweeper;

import javafx.scene.input.MouseButton;

public class Minefield{
  private int rows;
  private int columns;
  private Tile[][] field;
  private boolean isGameWon = false;

  public Minefield(int rows, int columns, double size, double DIFFICULTY) {
    this.rows = rows;
    this.columns = columns;
    this.field = new Tile[rows][columns];
    
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Tile tile = new Tile(DIFFICULTY);
        tile.setRow(i);
        tile.setCol(j);
        tile.setPrefSize(size, size);
        tile.setFocusTraversable(false);

        tile.setOnMouseClicked(event -> {
          if(event.getButton() == MouseButton.PRIMARY&&!tile.isRevealed()) {
            search(tile);
            if(tile.isMine()) {
              for (int k = 0; k < rows; k++) {
                for (int l = 0; l < columns; l++) {
                  field[k][l].wonFlag(false);
                  field[k][l].setDisable(true);
                  if (field[k][l].isMine()) {
                    field[k][l].reveal();
                  }
                }
              }
            }
            isGameWon = checkWin();
          } else if (event.getButton() == MouseButton.SECONDARY&&!tile.isRevealed()) {
            tile.flag();
            isGameWon = checkWin();
          }
          if(isGameWon) {
            for(int k = 0; k < rows; k++) {
              for(int l = 0; l < columns; l++) {
                field[k][l].wonFlag(true);
                if(field[k][l].isMine()) {
                  field[k][l].reveal();
                field[k][l].setDisable(true);
                }
              }
            }
          }
        });

        field[i][j]=tile;
        field[i][j].render();
        }
      }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        field[i][j].setAdjacentMines(countAdjacentMines(i, j));
      }
    }
  }
  
  public int countAdjacentMines(int row, int column) {
    int count = 0;
    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = column - 1; j <= column + 1; j++) {
        if (!(i >= 0 && i < rows && j >= 0 && j < columns)) {
          continue;
        }
        if (field[i][j].isMine()) {
          count++;
        }
      }
    }
    return count;
  }

  public Tile getTile(int row, int column) {
    return field[row][column];
  }

  public void search(Tile tile) {
    int row = tile.getRow();
    int column = tile.getCol();
    if (row < 0 || row >= rows || column < 0 || column >= columns) {
      return;
    }
    if (field[row][column].isRevealed()) {
      return;
    }
    field[row][column].reveal();
    if (field[row][column].getAdjacentMines() == 0) {
      try{search(field[row-1][column]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row+1][column]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row][column-1]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row][column+1]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row+1][column+1]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row-1][column+1]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row+1][column-1]);}catch(IndexOutOfBoundsException e){}
      try{search(field[row-1][column-1]);}catch(IndexOutOfBoundsException e){}
    }
  }

  public boolean checkWin() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if ((
        !field[i][j].isRevealed() && !field[i][j].isFlagged())||
        (!field[i][j].isRevealed()&&!field[i][j].isMine())||
        (field[i][j].isFlagged()&&!field[i][j].isMine())||
        (field[i][j].isRevealed()&&field[i][j].isMine()
        )) {
          return false;
        }
      }
    }
    return true;
  }
}