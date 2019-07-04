package ru.hawoline.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

class Character extends Pane implements Movement{

    private ImageView imageView;
    private int count = 3;
    private int columns = 3;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 32;
    private int height = 32;
    int direction;

    private Rectangle removeRect = null;
    SpriteAnimation animation;

    int health;
    int intelligence;

    Character(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView,Duration.millis(200),count,columns,offsetX,offsetY,width,height);
        getChildren().add(imageView);

        this.intelligence = 0;
        this.health = 200;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public void moveX(int x){
        boolean right = x > 0;

        for(int i = 0; i < Math.abs(x); i++) {
            if (right) {
                this.setTranslateX(this.getTranslateX() + 1);
                direction = 0;
            }
            else {
                this.setTranslateX(this.getTranslateX() - 1);
                direction = 1;
            }
            isBonusEat();
        }
    }

    @Override
    public void moveY(int y) {
        boolean down = y > 0;

        for (int i = 0; i < Math.abs(y); i++) {
            if (down){
                this.setTranslateY(this.getTranslateY() + 1);
                direction = 2;
            }
            else {
                this.setTranslateY(this.getTranslateY() - 1);
                direction = 3;
            }
            isBonusEat();
        }
    }

    private void isBonusEat(){
        Main.bonuses.forEach((rect) -> {
            if (this.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeRect = rect;
                Random random = new Random();
                intelligence += random.nextInt(5);
                System.out.println("Intelligence: " + intelligence);
            }
        });
        Main.bonuses.remove(removeRect);
        Main.root.getChildren().remove(removeRect);
    }
}
