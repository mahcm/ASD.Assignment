/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customizeflower;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class CustomizeFlower extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        StackPane root = new StackPane();
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
