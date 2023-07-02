package progettoingegneria.pazientiipertesi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Group root= new Group();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("progetto");
        //Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}