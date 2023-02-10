package com.example.pong;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloController{

    
    public AnchorPane anchor;
    public Rectangle rectanguloIzq;
    public Rectangle rectangleDrch;
    public Circle bola;
    public Label puntuacionIzq;
    public Label puntuacionDrch;

    private boolean w = false;
    private boolean s = false;
    private boolean SeJugadorArriba = false;
    private boolean SeJugadorAbajo = false;
    private int movimiento = 10;

    public void initialize(){
        
        anchor.sceneProperty().addListener((observableValue, oldscene, escena) -> {

            if(escena != null){
                escena.setOnKeyPressed(keyEvent -> {
                    switch (keyEvent.getCode()){
                        case W ->
                            w = true;
                        case S ->
                            s = true;
                        case UP ->
                            SeJugadorArriba = true;
                        case DOWN ->
                            SeJugadorAbajo = true;
                    }
                });
                escena.setOnKeyReleased(keyEvent -> {
                    switch (keyEvent.getCode()){
                        case W->
                            w = false;
                        case S->
                            s = false;
                        case UP->
                            SeJugadorArriba = false;
                        case DOWN->
                            SeJugadorAbajo = false;
                    }
                });
                Timeline tl = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {

                    if (w){
                        rectanguloIzq.setY(rectanguloIzq.getY() - movimiento);
                    }
                    if (s){
                        rectanguloIzq.setLayoutY(rectanguloIzq.getLayoutY() + movimiento);
                    }
                    if (SeJugadorArriba){
                         rectangleDrch.setLayoutY(rectangleDrch.getLayoutY() - movimiento);
                    }
                    if (SeJugadorAbajo){
                        rectangleDrch.setLayoutY(rectangleDrch.getLayoutY() + movimiento);
                    }

                }));
                tl.setCycleCount(Animation.INDEFINITE);
                tl.play();
            }
        });

    }

}