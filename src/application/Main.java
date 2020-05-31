package application;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Splash loading image
        Parent splash = FXMLLoader.load(getClass().getResource("/fxml/Splash.fxml"));
        Scene scene = new Scene(splash, 400, 300, Color.TRANSPARENT);
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.setScene(scene);
        splashStage.show();
        // On lance la scène principale après 1.5 secondes
        PauseTransition pause = new PauseTransition(Duration.millis(1500));
        pause.setOnFinished(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/LoginMain.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setTitle("League Companion");
            primaryStage.setScene(new Scene(root, 660, 412));
            primaryStage.getIcons().add(new Image("resources/leaguelogoblack.png"));
            primaryStage.show();
            primaryStage.setResizable(false);
            splashStage.close();
        });
        pause.play();
    }

}
