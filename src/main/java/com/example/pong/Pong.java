package com.example.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;

public class Pong extends Application {

    //variable
    private static final int width = 800;
    private static final int height = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 100;
    private static final int BALL_R = 15;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double playerOneYPos = height / 2;
    private double playerTwoYPos = height / 2;
    private double ballXPos = width / 2;
    private double ballYPos = width / 2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = width - PLAYER_WIDTH;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Pong.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("P O N G");
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse Control
        canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
        canvas.setOnMouseClicked((e -> gameStarted = true));
        stage.setScene(new Scene(new StackPane((canvas))));
        stage.show();
        tl.play();


    }
    private void run(GraphicsContext gc){
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //set text color
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(gameStarted){
            //set ball movement
          h

            //computer
            if (ballXPos <width -width/4){
                playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
            }
            else {
                playerTwoYPos = ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ?playerTwoYPos += 1: playerTwoYPos - 1;

            }

            //draw ball
            gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);
        }else {
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("on Click", width / 2, height / 2);
        }

    }


    public static void main(String[] args) {
        launch();
    }
}