package com.game.snake_game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends Application {

    private int speed;
    private double startX, startY;
    private double rectHeight, rectWidth;
    private List<Rectangle> snake;
    private Rectangle food;
    private KeyCode dir;
    private Point startPoint;
    private GameLoop gameLoop;
    private Pane root;

    @Override
    public void start(Stage stage) throws IOException {
        rectHeight = 10;
        rectWidth = 10;
        dir = KeyCode.A;
        speed = 10;
        startPoint = new Point(100, 100);

        snake = new ArrayList<>();

        this.gameLoop = new GameLoop(speed, 800, 600, dir, this);

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake Game");
        stage.setScene(scene);

        AnimationTimer game = gameLoop.startGame(snake, food, startPoint);
        scene.setOnKeyPressed(e -> {
            gameLoop.updateDirection(e.getCode(), game);
        });


        stage.show();
    }

    private Parent createContent() {

        Rectangle head = new Rectangle(rectHeight, rectWidth, Color.BLACK);
        head.setTranslateX(startPoint.getX());
        head.setTranslateY(startPoint.getY());

        snake.add(head);

        Point randFoodPoint = gameLoop.getRandomPointForFood();

        food = new Rectangle(rectHeight, rectWidth, Color.GREEN);
        food.setTranslateX(randFoodPoint.getX());
        food.setTranslateY(randFoodPoint.getY());

        root = new Pane(head, food);
        root.setPrefSize(800, 600);

        return root;
    }

    public void addFood(Rectangle newFood) {
        root.getChildren().add(newFood);
    }

    public static void main(String[] args) {
        launch();
    }
}

