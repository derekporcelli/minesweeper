module game.engine.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.engine.minesweeper to javafx.fxml;
    exports game.engine.minesweeper;
}