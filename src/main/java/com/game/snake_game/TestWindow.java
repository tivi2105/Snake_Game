package com.game.snake_game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TestWindow extends Application {

    private int speed;
    private double startX, startY;
    private double rectHeight, rectWidth;
    private Rectangle head;
    private Rectangle food;
    private KeyCode dir;
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage stage) throws IOException {
        startX = 100;
        startY = 100;
        rectHeight = 10;
        rectWidth = 10;
        dir = KeyCode.A;
        speed = 10;

        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(e -> {
            updateDirection(head, e.getCode());
        });
        stage.setScene(scene);

        final long startNanoTime = System.nanoTime();

        gameLoop = startGame();
        stage.show();
    }

    private Parent createContent() {

        head = new Rectangle(rectHeight, rectWidth, Color.RED);
        head.setTranslateX(startX);
        head.setTranslateY(startY);

        food = new Rectangle(rectHeight, rectWidth, Color.GREEN);
        food.setTranslateX(startX);
        food.setTranslateY(startY);

        var root = new Pane(head);
        root.setPrefSize(800, 600);

        return root;
    }

    private void updateDirection(Node node, KeyCode keyPressed) {
        if (keyPressed == KeyCode.RIGHT && dir != KeyCode.LEFT) {
            dir = keyPressed;
        } else if (keyPressed == KeyCode.LEFT && dir != KeyCode.RIGHT) {
            dir = keyPressed;
        } else if (keyPressed == KeyCode.DOWN && dir != KeyCode.UP) {
            dir = keyPressed;
        } else if (keyPressed == KeyCode.UP && dir != KeyCode.DOWN) {
            dir = keyPressed;
        } else if(keyPressed == KeyCode.E && null != gameLoop) {
            gameLoop.stop();
        } else if(keyPressed == KeyCode.S && null != gameLoop) {
            dir = KeyCode.RIGHT;
            gameLoop.start();
        }
    }

    public void updateHead() {

        //Changing direction of the head
        if (dir == KeyCode.RIGHT) {
            startX += speed;
        } else if (dir == KeyCode.LEFT) {
            startX -= speed;
        } else if (dir == KeyCode.DOWN) {
            startY += speed;
        } else if (dir == KeyCode.UP) {
            startY -= speed;
        }

        //Checking if head is out of our window
        if(startX > 799) {
            startX = 0;
        }
        if(startX < 0) {
            startX = 799;
        }
        if(startY > 599) {
            startY = 0;
        }
        if(startY < 0) {
            startY = 599;
        }
    }



    private AnimationTimer startGame() {
        return new AnimationTimer() {
            public void handle(long currentNanoTime) {

                updateHead();
                head.setTranslateX(startX);
                head.setTranslateY(startY);
            }
        };
    }

    public static void main(String[] args) {
        launch();
    }
}
/*@Override
    public void start(Stage primaryStage) throws IOException {
       Group root = new Group();
       Scene scene = new Scene(root, 800, 600, Color.SKYBLUE);
       Stage stage = new Stage();

       root.getChildren().add(getText());
       root.getChildren().add(drawLine());

       stage.setScene(scene);
       stage.show();
    }*/

/*public class TestWindow extends Application {

    @Override
    public void start(Stage theStage) throws IOException {

        theStage.setTitle( "Timeline Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Rectangle earth = new Rectangle(10, 10, Color.LIGHTSKYBLUE);
        Rectangle sun   = new Rectangle(50, 50, Color.RED);
        Rectangle space = new Rectangle(50, 50, Color.BLACK);

        root.getChildren().add(earth);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            double x = 10;
            double y = 10;
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                //gc.setFill(Color.RED);
                //gc.fillRect(x, y, 10, 10);

                x += 5;
                if(x > 500) {
                    x = 10;
                    y += 10;
                }
                earth.setTranslateX(x);
                earth.setTranslateY(y);
            }
        }.start();

        theStage.show();
    }
}*/

/*public Text getText() {
        Text text = new Text();
        text.setText("Testing");
        text.setX(100);
        text.setY(100);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.GREEN);

        return text;
    }

    public void drawLine(Pane root) {
        for(int i = 10;i <= 800; i += 10) {
            Line line = new Line();
            line.setStartX(i);
            line.setStartY(0);
            line.setEndX(i);
            line.setEndY(600);
            line.setStrokeWidth(0.1);
            line.setOpacity(0.5);
            root.getChildren().add(line);
        }
        for(int i = 0;i <= 600; i += 10) {
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(i);
            line.setEndX(800);
            line.setEndY(i);
            //line.setStrokeWidth(0.1);
            line.setOpacity(0.5);
            root.getChildren().add(line);
        }
       line.setStrokeWidth(5);
        line.setStroke(Color.RED);

    }*/
