package Domain;

import Data.XMLCharacterManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import org.jdom.JDOMException;

public class MediumCharacter extends Character {

    public MediumCharacter(String identification, int x, int y, int imgNum, int speed) throws FileNotFoundException {
        super(identification, x, y, imgNum, speed);
        setSprite();
    }

    public void setSprite() throws FileNotFoundException {
        
      
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 6; i++) {
            sprite.add(new Image(new FileInputStream("src/Assets/mega" + i + ".gif")));
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
                        for (int i = 200; i < 420; i = i + 15) {
                            for (int j = 0; j < 3; j++) {

                                super.setImage(sprite.get(j));
                                Thread.sleep(this.getSpeed());
                                super.setY(i);
                                System.out.println(i);
                                System.out.println("XX" + super.getX());
                            }
                        }
                        bandera = 2;
                        break;
                    case 2:
                        for (int i = 750; i > 110; i = i - 15) {
                            for (int j = 3; j < 6; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setX(i);
                                bandera = 3;
                                System.out.println(i);

                            }
                        }
                        break;

                    case 3:
                        for (int i = 385; i >= 70; i = i - 15) {
                            for (int j = 0; j < 3; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setY(i);
                                bandera = 4;
                                System.out.println(i);

                            }
                        }
                        break;

                    case 4:
                        for (int i = 140; i < 790; i = i + 15) {
                            for (int j = 0; j < 3; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setX(i);
                                bandera = 5;
                                System.out.println(i);

                            }
                        }
                        break;
                    case 5:
                        for (int i = 90; i < 220; i = i + 15) {
                            for (int j = 3; j < 6; j++) {
                                super.setImage(sprite.get(j));
                                Thread.sleep(100);
                                super.setY(i);
                                bandera = -1;
                                System.out.println(i);

                            }
                        }
                        break;

                    default:
                        break;
                }

            } catch (InterruptedException ex) {
            }
             }
    }//run
}//fin de clase
