package Interface;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import threadwork.ThreadWork;

/**
 *
 * @author Diego
 */
public class NewFXMain extends Application implements EventHandler<ActionEvent>{
    
    Button btn_Start;
    Pane root;
  Stage second;
    ThreadWork window;
    public void init(){
        root = new Pane();
        this.btn_Start = new Button("Start!");
        this.btn_Start.setFont(Font.font("MOON", 25));
        this.btn_Start.setOnAction(this);
//        this.second= new Stage();
//        this.window= new ThreadWork();
    }
    
    public void background(){
        String image = "background.jpg";
        root.setStyle("-fx-background-image: url('" + image + "'); "
           +"-fx-background-position: left top, center;"
           +"-fx-background-repeat: no-repeat;"
           +"-fx-background-size: cover, auto;");  
    }
    
    @Override
    public void start(Stage primaryStage) {
        init();
        background();
        this.btn_Start.setLayoutX(245);
        this.btn_Start.setLayoutY(245);
        root.getChildren().add(this.btn_Start);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
                if ((Button) event.getSource() == btn_Start) {
                    try {
                        Application.launch(Window.class);
                      
                    } catch (Exception ex) {
                        Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
                
    }
}
