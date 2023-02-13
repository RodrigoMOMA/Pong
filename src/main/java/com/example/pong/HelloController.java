package com.example.pong;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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

    private static final int VelocidadCirculo = 5;
    private int x = VelocidadCirculo;
    private int y = VelocidadCirculo;

    private int puntosIzq;
    private int puntosDrch;

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
                    bola.setLayoutX(bola.getLayoutX() + x);
                    bola.setLayoutY(bola.getLayoutY() + y);
                    Shape choqueRectangulo1 =Shape.intersect(rectanguloIzq,bola);
                    Shape choqueRectangulo2=Shape.intersect(rectangleDrch,bola);
                    //if (bola.getLayoutX() <= bola.getRadius() || bola.getLayoutX() >= escena.getWidth() - bola.getRadius()){
                    //    x = -x;
                    //}
                    if (bola.getLayoutY() <= bola.getRadius() || bola.getLayoutY() >= escena.getHeight() - bola.getRadius()) {
                        y = -y;
                    }

                    if (!choqueRectangulo1.getBoundsInLocal().isEmpty()||!choqueRectangulo2.getBoundsInLocal().isEmpty()){
                        x*=-1;
                    }
                    if (bola.getLayoutX() > escena.getWidth()){
                        puntosDrch ++;
                        puntuacionDrch.setText(String.valueOf(puntosDrch));
                        bola.setLayoutX(300);
                        bola.setLayoutY(200);
                    }
                    if (bola.getLayoutX() < 0){
                        puntosIzq ++;
                        puntuacionIzq.setText(String.valueOf(puntosIzq));
                        bola.setLayoutX(300);
                        bola.setLayoutY(200);
                    }

                }));
                tl.setCycleCount(Animation.INDEFINITE);
                tl.play();
            }
        });

    }

}