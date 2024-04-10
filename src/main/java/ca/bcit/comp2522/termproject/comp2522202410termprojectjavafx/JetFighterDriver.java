package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JetFighterDriver extends Application {
    // drives the program
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();


        Image backgroundImage = new Image("background.jpg");
        root.getChildren().add(new ImageView(backgroundImage));

        Scene scene = new Scene(root, 1100, 700, Color.BLACK);

        root.setFocusTraversable(true);
        root.requestFocus();

        JetFighterMain game = new JetFighterMain(root);
        new Controller(game, root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JetFighter v.1.0");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

