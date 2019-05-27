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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import org.jdom.JDOMException;

public class Window extends Application implements Runnable ,EventHandler<ActionEvent> {
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

    private Button buttonCreate ;
    private Button buttonBarrier ;
    private Button buttonRevert;
    private Button buttonSimulation; 
    private Button buttonInterrupt;
    
    private TextField testValue;
    private TextField textCarries;
    
    private Label labelValue;
    private Label labelCarriles;
     
     private ComboBox SpeedComboBox ;

   
    boolean stopThreads;
    private ArrayList<Image> imagesSprite;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Graphics and Threads");
        initComponents(primaryStage);
        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
        ArrayList<Image> imagesSprite = new ArrayList<>();
        Music();
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

            this.testValue = new TextField();
            this.textCarries = new TextField();

            this.labelValue = new Label("Value");
            this.labelCarriles = new Label("Lanes");

            this.buttonCreate = new Button("Create");
            this.buttonBarrier = new Button("Barrier");
            this.buttonRevert = new Button("Revert");
            this.buttonSimulation = new Button("Simulation");
            this.buttonInterrupt = new Button("Interrupt");

            this.SpeedComboBox = new ComboBox();
            this.SpeedComboBox.getItems().addAll("Medium ", "Quick", "Slow", "Random");
            this.SpeedComboBox.relocate(1040, 150);
            this.SpeedComboBox.setValue("Speed");

            this.buttonCreate.relocate(1040, 75);
            this.buttonBarrier.relocate(1040, 325);
            this.buttonRevert.relocate(1040, 365);
            this.buttonInterrupt.relocate(1040, 410);
            this.buttonSimulation.relocate(1040, 490);

            this.testValue.relocate(1040, 40);
            this.textCarries.relocate(1040, 278);

            this.labelCarriles.relocate(1040, 258);
            this.labelValue.relocate(1040, 20);

            this.buttonCreate.setPrefSize(110, 30);
            this. buttonBarrier.setPrefSize(110, 30);
            this.buttonRevert.setPrefSize(110, 30);
            this.buttonInterrupt.setPrefSize(110, 30);
            this.buttonSimulation.setPrefSize(115, 50);
            
            this.buttonCreate.setOnAction(this);
            this.buttonBarrier.setOnAction(this);
            this.buttonInterrupt.setOnAction(this);
            this.buttonRevert.setOnAction(this);
            this.buttonSimulation.setOnAction(this);

            this.textCarries.setPrefSize(140, 30);
            this.testValue.setPrefSize(140, 30);

            this.pane.getChildren().add(buttonCreate);
            this.pane.getChildren().add(buttonBarrier);
            this.pane.getChildren().add(buttonRevert);
            this.pane.getChildren().add(buttonInterrupt);
            this.pane.getChildren().add(buttonSimulation);

            this.pane.getChildren().add(testValue);
            this.pane.getChildren().add(textCarries);
            this.pane.getChildren().add(labelCarriles);
            this.pane.getChildren().add(labelValue);

            this.pane.getChildren().add(SpeedComboBox);

//            this.pane.getChildren().add(textCarries);
            primaryStage.setScene(this.scene);

            this.slow = new SlowCharacter("thread 1", 750, 200, 0, Utility.Variables.SlOW);

            this.slow.setWall(true);

            this.med = new MediumCharacter("thread 2", 800, 200, 0, Utility.Variables.MEDIUM);
            this.med.setWall(true);

            this.fast = new FastCharacter("thread 3", 850, 200, 0, Utility.Variables.QUICK);

            this.fast.setWall(true);

            if (stopThreads != true) {
                this.slow.start();
                this.med.start();
                this.fast.start();
                AudioClip clip = new AudioClip(this.getClass().getResource("vuvuzela.mp3").toString());
                clip.play();
            } else {
                this.slow.suspend();
                this.med.suspend();
                this.fast.suspend();
            }

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
        if (med.isWall()) {
            gc.fillRect(140, 405, 35, 35);
        }
        if (slow.isWall()) {
            gc.fillRect(100, 405, 35, 35);
        }
        if (fast.isWall()) {
            gc.fillRect(180, 405, 35, 35);
        }

    }

    public void Music() {
        AudioClip clip = new AudioClip(this.getClass().getResource("batman.mp3").toString());
        clip.play();
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };

    @Override
    public void handle(ActionEvent e) {
        if ((Button)e.getSource()==buttonCreate) {
            System.out.println("Boton");
        }
     
        if ((Button)e.getSource()==buttonBarrier) {
            
        }
        if ((Button)e.getSource()==buttonInterrupt) {
            
        }
        if ((Button)e.getSource()==buttonRevert) {
            
        }
         if ((Button)e.getSource()==buttonSimulation) {
            
        }
    }
    
}//End aclss
