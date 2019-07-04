package ru.hawoline.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Pane implements Movement{

    private ImageView imageView;
    private int width = 32;
    private int height = 32;
    private Character character;

    /*
    * 0 - right
    * 1 - left
    * 2 - down
    * 3 - up
    */
    int direction = 0;

    Bullet(ImageView imageView, int direction, Character character){
        this.imageView = imageView;
        this.direction = direction;
        getChildren().add(imageView);

        this.character = character;
        this.setTranslateX(this.character.getTranslateX());
        this.setTranslateY(this.character.getTranslateY());
    }

    @Override
    public void moveX(int x) {
        this.setTranslateX(this.getTranslateX() + x);
    }

    @Override
    public void moveY(int y) {
        this.setTranslateY(this.getTranslateY() + y);
    }

    void move(){
        switch (direction){
            case 0:
                moveX(1);
                break;
            case 1:
                moveX(-1);
                break;
            case 2:
                moveY(1);
                break;
            case 3:
                moveY(-1);
        }
    }

    public Character getCharacter() {
        return character;
    }

    boolean checkCollision(Character character){
        return getBoundsInParent().intersects(character.getBoundsInParent());
    }
}
