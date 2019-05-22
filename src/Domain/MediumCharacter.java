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
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("src/Assets/mega" + i + ".gif")));
        }
        super.setSprite(sprite);
    }

    @Override
    public void run() {
        ArrayList<Image> sprite = super.getSprite();
        int x = 0;

        while (true) {
            try {
                if (x < 800) {
                    for (int i = 0; i < 800; i = i + 15) {
                        for (int j = 0; j < 3; j++) {
                            super.setImage(sprite.get(j));
                            Thread.sleep(this.getSpeed());
                            super.setX(i);
                        }
                    }
                    break;
                }

                System.out.println(x);
            } catch (InterruptedException ex) {
            }
        }//while
    }//run
}//fin de clase
