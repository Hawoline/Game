package ru.hawoline.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    static ArrayList<Rectangle> bonuses = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    Image image = new Image(getClass().getResourceAsStream("res/img/Personage.png"));
    Image bulletImage = new Image(getClass().getResourceAsStream("res/img/fireball.png"));
    ImageView imageView = new ImageView(image);
    ImageView imageView1 = new ImageView(image);
    Character player = new Character(imageView);
    Character secondPlayer = new Character(imageView1);
    static Pane root = new Pane();

    private KeyCode[] firstPlayerKeyCodes = {KeyCode.UP, KeyCode.DOWN, KeyCode.RIGHT, KeyCode.LEFT};
    private KeyCode[] secondPlayerKeyCodes = {KeyCode.W, KeyCode.S, KeyCode.D, KeyCode.A};

    static int rootWidth = 600;
    static int rootHeight = 600;

    @Override
    public void start(Stage primaryStage) {
        root.setPrefSize(rootWidth,rootHeight);
        root.getChildren().addAll(player, secondPlayer);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
        scene.setOnKeyReleased(event-> {
            keys.put(event.getCode(), false);
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void bonus(){
        int random = (int)Math.floor(Math.random()*100);
        int x = (int)Math.floor(Math.random()*rootWidth);
        int y = (int)Math.floor(Math.random()*rootHeight);
        if(random == 5){
            Rectangle rect = new Rectangle(20,20,Color.RED);
            rect.setX(x);
            rect.setY(y);
            bonuses.add(rect);
            root.getChildren().addAll(rect);
        }
    }
    private void update() {
        int counter = 0;

        for (Bullet bullet: bullets){
            bullet.move();
        }

        for (KeyCode keyCode: firstPlayerKeyCodes){
            if (!isPressed(keyCode))
                counter++;
        }
        if (counter == firstPlayerKeyCodes.length){
            player.animation.stop();
            counter = 0;
        }

        for (KeyCode keyCode: secondPlayerKeyCodes){
            if (!isPressed(keyCode))
                counter++;
        }

        if (counter == secondPlayerKeyCodes.length){
            secondPlayer.animation.stop();
        }

        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(96);
            player.moveY(-2);
        }
        if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(2);
        }
        if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(64);
            player.moveX(2);
        }
        if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(32);
            player.moveX(-2);
        }
        if (isPressed(KeyCode.W)) {
            secondPlayer.animation.play();
            secondPlayer.animation.setOffsetY(96);
            secondPlayer.moveY(-2);
        }
        if (isPressed(KeyCode.S)) {
            secondPlayer.animation.play();
            secondPlayer.animation.setOffsetY(0);
            secondPlayer.moveY(2);
        }
        if (isPressed(KeyCode.D)) {
            secondPlayer.animation.play();
            secondPlayer.animation.setOffsetY(64);
            secondPlayer.moveX(2);
        }
        if (isPressed(KeyCode.A)) {
            secondPlayer.animation.play();
            secondPlayer.animation.setOffsetY(32);
            secondPlayer.moveX(-2);
        }

        if (isPressed(KeyCode.SPACE)){
            shoot(player);
        }
        if (isPressed(KeyCode.BACK_SPACE)){
            shoot(secondPlayer);
        }
    }

    private void shoot(Character character){
        ImageView bulletIV = new ImageView(bulletImage);
        Bullet bullet = new Bullet(bulletIV, character.direction, character);
        bullets.add(bullet);
        root.getChildren().add(bullet);
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}