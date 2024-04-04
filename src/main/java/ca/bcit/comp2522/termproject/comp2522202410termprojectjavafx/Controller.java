package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Controller {

    private JetFighterMain game;

    public Controller(JetFighterMain game, Pane root) {
        this.game = game;

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.setMovingLeft(true);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.setMovingRight(true);
            } else if (event.getCode() == KeyCode.SPACE) {
                game.shootProjectile();
            }
        });

        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.setMovingLeft(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.setMovingRight(false);
            }
        });
    }
}
