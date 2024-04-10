package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * The Controller class handles user input for the game.
 * It listens for keyboard events and updates the game accordingly.
 */
public class Controller {

    /**
     * Constructs a Controller object and registers event handlers for keyboard input.
     *
     * @param game the JetFighterMain object representing the game
     * @param root the root Pane where the game is displayed
     */
    public Controller(JetFighterMain game, Pane root) {

        // Event handler for key presses
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.setMovingLeft(true);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.setMovingRight(true);
            } else if (event.getCode() == KeyCode.SPACE) {
                game.shootProjectile();
            }
        });

        // Event handler for key releases
        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.setMovingLeft(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.setMovingRight(false);
            }
        });
    }
}
