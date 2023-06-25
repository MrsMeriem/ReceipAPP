package foodapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;


public class FoodApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        root.getStylesheets().add("file:src/main/resources/app/foodapp/view/style.css");
        primaryStage.setTitle("FoodApp");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("file:src/main/resources/app/foodapp/view/img/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, Color.BLACK));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
