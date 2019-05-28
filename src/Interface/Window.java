package Interface;

import Data.XMLCharacterManager;
import Domain.FifthLane;
import Domain.FirstLane;
import Domain.FourthLane;
import Domain.SecondaLane;
import Domain.ThirdLane;
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

public class Window extends Application implements Runnable, EventHandler<ActionEvent> {
//RYU es el más veloz, luego bill, megaman es el más lento y kirby es random

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    private Image barrierImage;

    private ThirdLane third;
    private FirstLane first;
    private SecondaLane second;
    private FourthLane fourth;
    private FifthLane fifth;
    private XMLCharacterManager data;

    private Domain.Character r1;
    private Domain.Character r2;
    private Domain.Character r3;
    private Domain.Character r4;

    private Button buttonCreate;
    private Button buttonBarrier;
    private Button buttonRevert;
    private Button buttonSimulation;
    private Button buttonInterrupt;
    private Button buttonRestart;

    private TextField testValue;
    private TextField textCarries;

    private Label labelValue;
    private Label labelCarriles;

    private ComboBox SpeedComboBox;

    boolean stopThreads = false;
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
            this.barrierImage = new Image(new FileInputStream("src/Assets/valla2.png"));

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
            this.buttonRestart = new Button("Restart");

            this.SpeedComboBox = new ComboBox();
            this.SpeedComboBox.getItems().addAll("Medium ", "Quick", "Slow", "Random");
            this.SpeedComboBox.relocate(1040, 150);
            this.SpeedComboBox.setValue("Speed");

            this.buttonCreate.relocate(1040, 75);
            this.buttonBarrier.relocate(1040, 325);
            this.buttonRevert.relocate(1040, 365);
            this.buttonInterrupt.relocate(1040, 405);
            this.buttonRestart.relocate(1040, 445);
            this.buttonSimulation.relocate(1040, 495);

            this.testValue.relocate(1040, 40);
            this.textCarries.relocate(1040, 278);

            this.labelCarriles.relocate(1040, 258);
            this.labelValue.relocate(1040, 20);

            this.buttonCreate.setPrefSize(110, 30);
            this.buttonBarrier.setPrefSize(110, 30);
            this.buttonRevert.setPrefSize(110, 30);
            this.buttonInterrupt.setPrefSize(110, 30);
            this.buttonRestart.setPrefSize(110, 30);
            this.buttonSimulation.setPrefSize(115, 50);

            this.buttonCreate.setOnAction(this);
            this.buttonBarrier.setOnAction(this);
            this.buttonInterrupt.setOnAction(this);
            this.buttonRevert.setOnAction(this);
            this.buttonSimulation.setOnAction(this);
            this.buttonRestart.setOnAction(this);

            this.textCarries.setPrefSize(140, 30);
            this.testValue.setPrefSize(140, 30);

            this.pane.getChildren().add(buttonCreate);
            this.pane.getChildren().add(buttonBarrier);
            this.pane.getChildren().add(buttonRevert);
            this.pane.getChildren().add(buttonInterrupt);
            this.pane.getChildren().add(buttonSimulation);
            this.pane.getChildren().add(buttonRestart);

            this.pane.getChildren().add(testValue);
            this.pane.getChildren().add(textCarries);
            this.pane.getChildren().add(labelCarriles);
            this.pane.getChildren().add(labelValue);

            this.pane.getChildren().add(SpeedComboBox);

//            this.pane.getChildren().add(textCarries);
            primaryStage.setScene(this.scene);

            this.first = new FirstLane("thread 1", 750, 200, 0, Utility.Variables.SlOW);

//            this.slow.setWall(false);
            this.second = new SecondaLane("thread 2", 800, 200, 0, Utility.Variables.SlOW);
//            this.med.setWall(true);

            this.third = new ThirdLane("thread 3", 830, 200, 0, Utility.Variables.QUICK);

//            this.fast.setWall(true);
            this.fourth = new FourthLane("thread 4", 870, 200, 0, Utility.Variables.MEDIUM);
            this.fifth = new FifthLane("thread 5", 910, 200, 0, Utility.Variables.QUICK);
            this.thread = new Thread(this);
            this.thread.start();
        } catch (FileNotFoundException | BufferOverflowException ex) {
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, Variables.WIDTH, Variables.HEIGHT);
        gc.drawImage(this.image, 0, 0);
        gc.drawImage(this.first.getImage(), this.first.getX(), this.first.getY());
        gc.drawImage(this.second.getImage(), this.second.getX(), this.second.getY());
        gc.drawImage(this.third.getImage(), this.third.getX(), this.third.getY());
        gc.drawImage(this.fourth.getImage(), this.fourth.getX(), this.fourth.getY());
        gc.drawImage(this.fifth.getImage(), this.fifth.getX(), this.fifth.getY());

        if (second.isWall()) {
            gc.drawImage(barrierImage, 140, 405);
        }
        if (first.isWall()) {
            gc.drawImage(barrierImage, 180, 405);

        }
        if (third.isWall()) {
            gc.drawImage(barrierImage, 100, 405);

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
        if ((Button) e.getSource() == buttonCreate) {

        }
        if ((Button) e.getSource() == buttonRestart) {
            stopThreads = true;
            System.out.println(stopThreads);
            if (stopThreads == true) {
                this.first.resume();
                this.second.resume();
                this.third.resume();
                this.fourth.resume();
                this.fifth.resume();
            }

        }

        if ((Button) e.getSource() == buttonBarrier) {
            if(textCarries.getText().equals("1")){
               first.setWall(true);
            }
   if(textCarries.getText().equals("2")){
                second.setWall(true);
            }
      if(textCarries.getText().equals("3")){
                third.setWall(true);
            }
         if(textCarries.getText().equals("4")){
                fourth.setWall(true);
            }
            if(textCarries.getText().equals("5")){
                fifth.setWall(true);
            }
        }
        if ((Button) e.getSource() == buttonInterrupt) {
            stopThreads = false;
            if (stopThreads != true) {
                this.first.suspend();
                this.second.suspend();
                this.third.suspend();
                this.fourth.suspend();
                this.fifth.suspend();
            }

        }
        if ((Button) e.getSource() == buttonRevert) {

        }
        if ((Button) e.getSource() == buttonSimulation) {
            stopThreads = true;
            System.out.println(stopThreads);
            if (stopThreads == true) {
                this.first.start();
                this.second.start();
                this.third.start();
                this.fourth.start();
                this.fifth.start();
                AudioClip clip = new AudioClip(this.getClass().getResource("vuvuzela.mp3").toString());
                clip.play();
            }

        }

    }

}//End aclss
