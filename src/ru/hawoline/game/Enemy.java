package ru.hawoline.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Enemy extends Pane implements Movement{

    private ImageView imageView;
    private int count = 3;
    private int columns = 3;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 32;
    private int height = 32;
    Rectangle removeRect = null;
    SpriteAnimation animation;

    int health = 200;

    Enemy(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().addAll(imageView);

        this.health = 200;
    }

    @Override
    public void moveX(int x){
        boolean right = x > 0;
        for(int i = 0; i < Math.abs(x); i++) {
            if (right) this.setTranslateX(this.getTranslateX() + 1);
            else this.setTranslateX(this.getTranslateX() - 1);
        }
    }

    @Override
    public void moveY(int y) {
        boolean down = y > 0;
        for (int i = 0; i < Math.abs(y); i++) {
            if (down) this.setTranslateY(this.getTranslateY() + 1);
            else this.setTranslateY(this.getTranslateY() - 1);
        }
    }
}
