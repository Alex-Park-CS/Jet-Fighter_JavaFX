package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the player in the game.
 * The player is controlled by the user and can move left or right.
 * It also holds information about the player's position, size, and image.
 */
public class Player {

    private final ImageView shape;

    /**
     * Constructs a Player object with the specified coordinates, width, and height.
     *
     * @param x      the x-coordinate of the player's initial position
     * @param y      the y-coordinate of the player's initial position
     * @param width  the width of the player's sprite
     * @param height the height of the player's sprite
     */
    public Player(double x, double y, double width, double height) {
        Image playerImage = new Image("jetFighter.png"); // Replace "path_to_your_image_file.jpg" with the actual path to your image file
        shape = new ImageView(playerImage);
        shape.setX(x);
        shape.setY(y);
        shape.setFitWidth(width);
        shape.setFitHeight(height);
    }

    /**
     * Retrieves the bounding box of the player's sprite in the parent coordinate space.
     *
     * @return the bounding box of the player's sprite
     */
    public Bounds getBoundsInParent() {
        return shape.getBoundsInParent();
    }

    /**
     * Retrieves the ImageView representing the player's sprite.
     *
     * @return the ImageView representing the player's sprite
     */
    public ImageView getShape() {
        return shape;
    }

    /**
     * Retrieves the x-coordinate of the player's current position.
     *
     * @return the x-coordinate of the player's position
     */
    public double getX() {
        return shape.getX();
    }

    /**
     * Retrieves the y-coordinate of the player's current position.
     *
     * @return the y-coordinate of the player's position
     */
    public double getY() {
        return shape.getY();
    }

    /**
     * Retrieves the width of the player's sprite.
     *
     * @return the width of the player's sprite
     */
    public double getWidth() {
        return shape.getFitWidth();
    }

    /**
     * Moves the player to the left by the specified speed.
     *
     * @param speed the speed at which the player moves left
     */
    public void moveLeft(double speed) {
        shape.setX(shape.getX() - speed);
    }

    /**
     * Moves the player to the right by the specified speed.
     *
     * @param speed the speed at which the player moves right
     */
    public void moveRight(double speed) {
        shape.setX(shape.getX() + speed);
    }
}
