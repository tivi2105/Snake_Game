module com.game.snake_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.snake_game to javafx.fxml;
    exports com.game.snake_game;
}