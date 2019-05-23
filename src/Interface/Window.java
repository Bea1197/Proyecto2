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

    private FastCharacter fast;
    private SlowCharacter slow;
    private MediumCharacter med;
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

           
            
            this.slow = new SlowCharacter("thread 1", 750, 200, 0, Utility.Variables.SlOW);
            this.slow.start();
            
            this.med= new MediumCharacter("thread 2", 800, 200, 0, Utility.Variables.MEDIUM);
            this.med.start();
            
            this.fast= new FastCharacter("thread 3", 850, 200, 0, Utility.Variables.QUICK);
            this.fast.start();
            this.thread = new Thread(this);
            this.thread.start();
        } catch (FileNotFoundException | BufferOverflowException ex) {
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
        gc.drawImage(this.image, 0, 0);
        gc.drawImage(this.slow.getImage(), this.slow.getX(), this.slow.getY());
        gc.drawImage(this.med.getImage(), this.med.getX(), this.med.getY());
        gc.drawImage(this.fast.getImage(), this.fast.getX(), this.fast.getY());
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}
