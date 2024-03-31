package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Driver extends Application {

    private Pane root = new Pane();
    private Parent createContent() {
        root.setPrefSize(600, 800);
        return root;
    }

    private Alien enemy = new Alien();
    @Override
    public void start(final Stage stage) throws Exception {
        root.getChildren().add(enemy);
        enemy.setTranslateX(100); // Move 100 pixels to the right
        enemy.setTranslateY(50);  // Move 50 pixels down

        Scene scene = new Scene(root, 1100, 700, Color.BLACK);
        stage.setTitle("Jet Fighter v.1.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
