package Domain;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Character extends Thread {

    private String identification;
    private int x;
    private int y;
    int imgNum;
    private Image image;
    private ArrayList<Image> sprite;
   private  boolean wall;
    private int speed;

    public Character() {
    }

    public Character(String identification, int x, int y, int imgNum, int speed) {
        this.x = x;
        this.y = y;
        this.identification = identification;
        this.imgNum = imgNum;
        this.sprite = new ArrayList<Image>();
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImgNum() {
        return imgNum;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

}
