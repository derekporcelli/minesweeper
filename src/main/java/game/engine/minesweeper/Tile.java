package game.engine.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button{
  private boolean isMine;
  private boolean isRevealed;
  private boolean isFlagged;
  private int adjacentMines;
  private int row;
  private int col;
  private boolean wonFlag;
  private final ImageView flagged = new ImageView(new Image("Flagged.png"));
  private final ImageView triggeredBomb = new ImageView(new Image("TriggeredBomb.png"));
  private final ImageView bomb = new ImageView(new Image("Bomb.png"));
  private final ImageView zero = new ImageView(new Image("Empty.png"));
  private final ImageView one = new ImageView(new Image("1.png"));
  private final ImageView two = new ImageView(new Image("2.png"));
  private final ImageView three = new ImageView(new Image("3.png"));
  private final ImageView four = new ImageView(new Image("4.png"));
  private final ImageView five = new ImageView(new Image("5.png"));
  private final ImageView six = new ImageView(new Image("6.png"));
  private final ImageView seven = new ImageView(new Image("7.png"));
  private final ImageView eight = new ImageView(new Image("8.png"));
  private final ImageView unrevealed = new ImageView(new Image("unrevealed.png"));
  
  public Tile(double DIFFICULTY) {
    isMine = Math.random() < DIFFICULTY;
    isRevealed = false;
    isFlagged = false;
    adjacentMines = 0;
    wonFlag = false;
    this.setStyle("-fx-padding: 0;");
  }
  

  public boolean isMine() {
    return isMine;
  }
  
  public boolean isRevealed() {
    return isRevealed;
  }

  public boolean isFlagged() {
    return isFlagged;
  }

  public int getAdjacentMines() {
    return adjacentMines;
  }

  public void setAdjacentMines(int adjacentMines) {
    this.adjacentMines = adjacentMines;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }
  
  public void reveal() {
    isRevealed = true;
    render();
  }

  public void flag() {
    isFlagged = !isFlagged;
    render();
  }

  public void wonFlag(boolean wonFlag) {
    this.wonFlag = wonFlag;
  } 

  public void render() {
    ImageView image = unrevealed;
    if(!isRevealed&&isFlagged){
      image = flagged;
    }
    if(isMine&&isRevealed&&wonFlag){
      image = bomb;
    } else if(isMine&&isRevealed){
      image = triggeredBomb;
    }
    if(!isMine&&adjacentMines==0&&isRevealed){
      image = zero;
    }
    if(!isMine&&adjacentMines==1&&isRevealed){
      image = one;
    }
    if(!isMine&&adjacentMines==2&&isRevealed){
      image = two;
    }
    if(!isMine&&adjacentMines==3&&isRevealed){
      image = three;
    }
    if(!isMine&&adjacentMines==4&&isRevealed){
      image = four;
    }
    if(!isMine&&adjacentMines==5&&isRevealed){
      image = five;
    }
    if(!isMine&&adjacentMines==6&&isRevealed){
      image = six;
    }
    if(!isMine&&adjacentMines==7&&isRevealed){
      image = seven;
    }
    if(!isMine&&adjacentMines==8&&isRevealed){
      image = eight;
    }
    image.setFitWidth(this.getWidth());
    image.setFitHeight(this.getHeight());
    setGraphic(image);
  }
}