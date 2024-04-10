package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Random;

/**
 * The Alien class represents an alien entity within the game.
 * It defines the properties and behavior of an alien object, including its shape, movement, and appearance.
 */
public class Alien extends Polygon implements Movement {

    // The shape of the alien
    private static final double[] ALIEN_SHAPE = {
            0.0, 4.0, 0.0, 12.0, 4.0, 16.0,
            8.0, 16.0, 6.0, 24.0, 10.0, 24.0,
            12.0, 16.0, 16.0, 16.0, 18.0, 24.0,
            22.0, 24.0, 20.0, 16.0, 24.0, 16.0,
            28.0, 12.0, 28.0, 4.0, 24.0, 0.0,
            4.0, 0.0
    };

    // The width and height of the alien
    public static final double ALIEN_WIDTH = 28.0;
    public static final double ALIEN_HEIGHT = 24.0;

    // The maximum speed of the alien
    private static final double MAX_SPEED = 7.0;

    private double speedX; // The horizontal speed of the alien
    private double speedY; // The vertical speed of the alien

    /**
     * Constructs an Alien object with the specified position.
     *
     * @param x the x-coordinate of the alien's initial position
     * @param y the y-coordinate of the alien's initial position
     */
    public Alien(double x, double y) {
        super(ALIEN_SHAPE);
        setTranslateX(x);
        setTranslateY(y);
        setStrokeWidth(2);
        setStroke(Color.WHITE);

        Random random = new Random();
        // Randomize initial speeds within the maximum speed range
        speedX = (random.nextDouble() - 0.5) * MAX_SPEED * 2; // Random speed between -MAX_SPEED and MAX_SPEED
        speedY = (random.nextDouble() - 0.5) * MAX_SPEED * 2; // Random speed between -MAX_SPEED and MAX_SPEED
    }

    /**
     * Moves the alien based on its current speed.
     * If the alien reaches the edges of the screen, it reverses its direction.
     */
    public void move(Pane root) {
        // Update position based on current speed
        setTranslateX(getTranslateX() + speedX);
        setTranslateY(getTranslateY() + speedY);

        // Reverse direction if hitting the edges
        if (this.getTranslateX() < 0 || this.getTranslateX() + ALIEN_WIDTH > JetFighterMain.WIDTH) {
            speedX = -speedX;
        }
        if (this.getTranslateY() < 10 || this.getTranslateY() + ALIEN_HEIGHT + 200 > JetFighterMain.HEIGHT) {
            speedY = -speedY;
        }
    }
}
