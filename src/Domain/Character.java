package Domain;

import Utility.Variables;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

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
//         this.slow = new SlowCharacter("thread 1", 750, 200, 0, Utility.Variables.SlOW);
//            this.slow.start();
//            this.slow.setWall(false);
//
//            this.med = new MediumCharacter("thread 2", 800, 200, 0, Utility.Variables.MEDIUM);
//            this.med.setWall(true);
//            this.med.start();
//
//            this.fast = new FastCharacter("thread 3", 850, 200, 0, Utility.Variables.QUICK);
//            this.fast.start();
//            this.fast.setWall(false);
//
//            this.thread = new Thread(this);
//            this.thread.start();
//        } catch (FileNotFoundException | BufferOverflowException ex) {
//        }
//    }
//
//    private void draw(GraphicsContext gc) {
//        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
//        gc.drawImage(this.image, 0, 0);
//        gc.drawImage(this.slow.getImage(), this.slow.getX(), this.slow.getY());
//        gc.drawImage(this.med.getImage(), this.med.getX(), this.med.getY());
//        gc.drawImage(this.fast.getImage(), this.fast.getX(), this.fast.getY());
//        if (med.isWall()) {
//            gc.fillRect(140, 405, 35, 35);
//        }
//        if (slow.isWall()) {
//            gc.fillRect(180, 405, 35, 35);
//        }
//        if (fast.isWall()) {
////            
//            gc.fillRect(100, 405, 35, 35);
//        }
//    public void Music() {
//        AudioClip clip = new AudioClip(this.getClass().getResource("Bohemian.mp3").toString());
//        clip.play();
//    }
//    }