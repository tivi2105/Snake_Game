package com.game.snake_game;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Random;

public class GameLoop {

    private final Random rand = new Random();

    int speed;
    int windowHeight;
    int windowWidth;
    KeyCode dir;

    SnakeGame sg;

    GameLoop(int speed, int windowHeight, int windowWidth, KeyCode dir, SnakeGame sg) {
        this.speed = speed;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.dir = dir;
        this.sg = sg;
    }

    public AnimationTimer startGame(List<Rectangle> snake, Rectangle food, Point startPoint) {
        if(null == snake || snake.size() == 0 || null == food || null == startPoint) {
            return null;
        }
        return new AnimationTimer() {
            public void handle(long currentNanoTime) {
                updateSnake(snake, startPoint);
                if(ateFood(snake.get(0), food)) {
                    updateFoodLocation(food, snake);
                }
            }
        };
    }

    private void updateSnake(List<Rectangle> snake, Point p) {
        updateHead(snake.get(0), p);
        for(int i = 1;i < snake.size()-1; i++) {
            snake.get(i).setTranslateX(snake.get(i+1).getTranslateX());
            snake.get(i).setTranslateY(snake.get(i+1).getTranslateY());
        }
        if(snake.size() > 0) {
            snake.get(snake.size() - 1).setTranslateX(p.getX());
            snake.get(snake.size() - 1).setTranslateY(p.getY());
        }
    }

    private void updateHead(Rectangle head, Point p) {

        //Changing direction of the head
        if (dir == KeyCode.RIGHT) {
            p.setX(p.getX() + this.speed);
        } else if (dir == KeyCode.LEFT) {
            p.setX(p.getX() - this.speed);
        } else if (dir == KeyCode.DOWN) {
            p.setY(p.getY() + this.speed);
        } else if (dir == KeyCode.UP) {
            p.setY(p.getY() - this.speed);
        }

        //Checking if head is out of our window
        if(p.getX() > 799) {
            p.setX(0);
        }
        if(p.getX() < 0) {
            p.setX(790);
        }
        if(p.getY() > 599) {
            p.setY(0);
        }
        if(p.getY() < 0) {
            p.setY(590);
        }
        head.setTranslateX(p.getX());
        head.setTranslateY(p.getY());
    }

    public void updateDirection(KeyCode keyPressed, AnimationTimer game) {
        if (keyPressed == KeyCode.RIGHT && this.dir != KeyCode.LEFT) {
            this.dir = keyPressed;
        } else if (keyPressed == KeyCode.LEFT && this.dir != KeyCode.RIGHT) {
            this.dir = keyPressed;
        } else if (keyPressed == KeyCode.DOWN && this.dir != KeyCode.UP) {
            this.dir = keyPressed;
        } else if (keyPressed == KeyCode.UP && this.dir != KeyCode.DOWN) {
            this.dir = keyPressed;
        } else if(keyPressed == KeyCode.S && null != game) {
            this.dir = KeyCode.RIGHT;
            game.start();
        } else if((keyPressed == KeyCode.P || keyPressed == KeyCode.E) && null != game) {
            game.stop();
        }
    }

    public Point getRandomPointForFood() {

        Point randPoint = new Point();
        randPoint.setX(rand.nextInt(80)*10);
        randPoint.setY(rand.nextInt(60)*10);

        return randPoint;
    }

    private boolean ateFood(Rectangle head, Rectangle food) {
        double headX = head.getTranslateX();
        double headY = head.getTranslateY();
        double foodX = food.getTranslateX();
        double foodY = food.getTranslateY();
        if(headX == foodX) {
            System.out.println(headX + "--" + headY + "--" + foodX + "--" + foodY + "--" + (headX == foodX && headY == foodY));
        }
        if(headX == foodX && headY == foodY) {
            return true;
        }

        return false;
    }

    private void updateFoodLocation(Rectangle food, List<Rectangle> snake) {
        Point newPoint = getRandomPointForFood();
        food.setTranslateX(newPoint.getX());
        food.setTranslateY(newPoint.getY());
        Rectangle newBlock = new Rectangle(10, 10, Color.RED);
        newBlock.setTranslateX(newPoint.getX());
        newBlock.setTranslateY(newPoint.getY());
        snake.add(newBlock);
        sg.addFood(newBlock);
    }
}
