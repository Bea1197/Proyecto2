package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class FirstLane extends Character {

    public FirstLane(String name, int x, int y, int imgNum, int speed) throws FileNotFoundException {
        super(name, x, y, imgNum, speed);
        setSprite();
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 6; i++) {
            if (super.getSpeed() == Utility.Variables.SlOW) {
                sprite.add(new Image(new FileInputStream("src/Assets/bill" + i + ".gif")));
            } else if (super.getSpeed() == Utility.Variables.QUICK) {
                sprite.add(new Image(new FileInputStream("src/Assets/Ryu" + i + ".gif")));
            } else if (super.getSpeed() == Utility.Variables.MEDIUM) {
                sprite.add(new Image(new FileInputStream("src/Assets/mega" + i + ".gif")));
            } else if (super.getSpeed() == Utility.Variables.RANDOM) {
                sprite.add(new Image(new FileInputStream("src/Assets/Running" + i + ".png")));
            }
        }
        super.setSprite(sprite);
    }

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        int x = 0;
        int bandera = 1;
        int tempX = 0;
        int tempY = 0;
        while (true) {
            try {
                switch (bandera) {
                    case 1:
                        if (!super.isReverse()) {
                            for (int i = super.getY(); i < 385; i = i + 15) {
                                for (int j = 0; j < 3; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(this.getSpeed());
                                }
                               
                                super.setY(i);
                                tempY = i;
                                bandera = 2;
                                if (super.isReverse()) {
                                    i = 385;
                                    bandera = 1;
                                }
                            }
                        } else {
                            for (int r = super.getY(); r > 200; r = r - 15) {
                                for (int t = 3; t < 6; t++) {
                                    super.setImage(sprite.get(t));
                                    Thread.sleep(this.getSpeed());
                                }
                                super.setY(r);
                                tempY = r;
                                bandera = -1;
                                if (!super.isReverse()) {
                                    r = 200;
                                    bandera = 1;
                                }
                            }
                        }

                        break;
                    case 2:
                        if (!super.isReverse()) {
                            for (int i = super.getX(); i > 140; i = i - 15) {
                                for (int j = 3; j < 6; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setX(i);
                                tempX = i;
                                bandera = 3;
                                if (super.isReverse()) {
                                    i = 140;
                                    bandera = 2;
                                }
                            }
                        } else {
                            for (int i = super.getX(); i < 750; i = i + 15) {
                                for (int j = 0; j < 3; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setX(i);
                                tempX = i;
                                bandera = 1;
                                if (!super.isReverse()) {
                                    i = 750;
                                    bandera = 2;
                                }
                            }
                        }

                        break;

                    case 3:
                        if (super.isWall()) {
                            suspend();
                        }
                        bandera = 4;

                        break;
                    case 4:
                        System.out.println("4");
                        if (!super.isReverse()) {
                            for (int i = super.getY(); i > 90; i = i - 15) {
                                for (int j = 0; j < 3; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setY(i);
                                bandera = 5;
                                tempY = i;
                                if (super.isReverse()) {
                                    i = 89;
                                    bandera = 4;
                                }
                            }
                        } else {
                            for (int i = super.getY(); i < 385; i = i + 15) {
                                for (int j = 0; j < 3; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setY(i);
                                bandera = 2;
                                if (!super.isReverse()) {
                                    i = 385;
                                    bandera = 4;
                                }
                            }
                        }
                        break;

                    case 5:
                        if (!super.isReverse()) {
                            for (int i = super.getX(); i < 750; i = i + 15) {
                                for (int j = 0; j < 3; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setX(i);
                                tempX = i;
                                bandera = 6;
                                if (super.isReverse()) {
                                    i = 750;
                                    bandera = 5;
                                }
                            }
                        } else {
                            for (int i = super.getX(); i > 140; i = i - 15) {
                                for (int j = 3; j < 6; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setX(i);
                                bandera = 4;
                                if (!super.isReverse()) {
                                    i = 140;
                                    bandera = 5;
                                }
                            }
                        }
                        break;
                    case 6:
                        if (!super.isReverse()) {
                            for (int i = super.getY(); i < 220; i = i + 15) {
                                for (int j = 3; j < 6; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setY(i);
                                bandera = -1;
                                tempY = i;
                                if (super.isReverse()) {
                                    i = 220;
                                    bandera = 6;
                                }
                            }
                        } else {
                            for (int i = super.getY(); i > 90; i = i - 15) {
                                for (int j = 3; j < 6; j++) {
                                    super.setImage(sprite.get(j));
                                    Thread.sleep(100);
                                }
                                super.setY(i);
                                bandera = 5;
                                if (!super.isReverse()) {
                                    i = 90;
                                    bandera = 6;
                                }
                            }
                        }
                        break;

                    default:
                        break;
                }
            } catch (InterruptedException ex) {
            }
        }//while
    }//run
}//fin de clase
