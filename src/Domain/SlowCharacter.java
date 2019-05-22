package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class SlowCharacter extends Character {

    public SlowCharacter(String name, int x, int y, int imgNum, int speed) throws FileNotFoundException {
        super(name, x, y, imgNum, speed);
        setSprite();
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 6; i++) {
            sprite.add(new Image(new FileInputStream("src/Assets/bill" + i + ".gif")));
        }
        super.setSprite(sprite);
    }

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        int x = 0;
        int bandera = 1;
        while (true) {
            try {
                switch (bandera) {
                    case 1:
                        for (int i = 165; i < 620; i = i + 15) {
                            for (int j = 0; j < 3; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(this.getSpeed());
                                super.setX(i);
                                System.out.println(i);
                            }
                        }
                        bandera = 2;
                        break;
                    case 2:
                        for (int i = 180; i < 380; i = i + 15) {
                            for (int j = 3; j < 6; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setY(i);
                                bandera = 3;
                                System.out.println(i);

                            }
                        }
                        break;
                    case 3:
                        for (int i = 620; i > 130; i = i - 15) {
                            for (int j = 3; j < 6; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setX(i);
                                bandera = 4;
                                System.out.println(i);

                            }
                        }
                        break;
                    case 4:
                        for (int i = 380; i >= 165; i = i - 15) {
                            for (int j = 0; j < 3; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setY(i);
                         bandera =1;
                                System.out.println(i);

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
