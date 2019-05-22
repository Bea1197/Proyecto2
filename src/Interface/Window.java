package Interface;

import Data.XMLCharacterManager;
import Domain.SlowCharacter;
import Domain.MediumCharacter;
import Domain.FastCharacter;
import Utility.Variables;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jdom.JDOMException;

public class Window extends Application implements Runnable {
//RYU es el más veloz, luego bill, megaman es el más lento y kirby es random

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;

    private FastCharacter rr;
    private SlowCharacter rb;
    private MediumCharacter rm;
    private XMLCharacterManager data;

    private Domain.Character r1;
    private Domain.Character r2;
    private Domain.Character r3;
    private Domain.Character r4;

    private ArrayList<Image> imagesSprite;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Graphics and Threads");
        initComponents(primaryStage);
        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
        ArrayList<Image> imagesSprite = new ArrayList<>();
    }

    @Override
    public void run() {

        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;

        while (true) {
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000;
                Thread.sleep(wait);
                GraphicsContext gc = this.canvas.getGraphicsContext2D();
                draw(gc);
            } catch (InterruptedException ex) {
            }
        }
    }

    private void initComponents(Stage primaryStage) throws IOException, JDOMException {
        this.data = new XMLCharacterManager(Utility.Variables.CHARACTERSPATH);
        try {
            this.pane = new Pane();
            this.scene = new Scene(this.pane, Variables.WIDTH, Variables.HEIGHT);
            this.canvas = new Canvas(Variables.WIDTH, Variables.HEIGHT);
            this.image = new Image(new FileInputStream("src/Assets/pistaa.jpg"));
            this.pane.getChildren().add(this.canvas);

            primaryStage.setScene(this.scene);

            r1 = new Domain.Character("thread 1", -50, 500, 0, Utility.Variables.SlOW);
            r2 = new Domain.Character("thread 2", -50, 100, 0, Utility.Variables.QUICK);
            r3 = new Domain.Character("thread 3", -50, 200, 0, Utility.Variables.MEDIUM);
            r4 = new Domain.Character("thread 4", -50, 300, 0, Utility.Variables.RANDOM);

            
            this.rb = new SlowCharacter("thread 3", -50, 165, 500, Utility.Variables.MEDIUM);
            this.rb.start();

            this.thread = new Thread(this);
            this.thread.start();
        } catch (FileNotFoundException | BufferOverflowException ex) {
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
        gc.drawImage(this.image, 0, 0);
//        gc.drawImage(this.rk.getImage(), this.rk.getX(), this.rk.getY());
//        gc.drawImage(this.rr.getImage(), this.rr.getX(), this.rr.getY());
        gc.drawImage(this.rb.getImage(), this.rb.getX(), this.rb.getY());
//        gc.drawImage(this.rm.getImage(), this.rm.getX(), this.rm.getY());
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}
