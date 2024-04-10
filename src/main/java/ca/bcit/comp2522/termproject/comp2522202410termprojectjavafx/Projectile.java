package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Projectile class represents a projectile fired within the game.
 * It defines the properties and behavior of a projectile object, including its movement and appearance.
 */
public class Projectile {

    public static final int WIDTH = 5;
    public static final int HEIGHT = 10;
    public static final int SPEED = 5;

    private final Rectangle shape;

    /**
     * Constructs a Projectile object with the specified position and color.
     *
     * @param x         the x-coordinate of the projectile's initial position
     * @param y         the y-coordinate of the projectile's initial position
     * @param projColor the color of the projectile
     */
    public Projectile(double x, double y, Color projColor) {
        shape = new Rectangle(x, y, WIDTH, HEIGHT);
        shape.setFill(projColor);
    }

    /**
     * Retrieves the Rectangle representing the shape of the projectile.
     *
     * @return the Rectangle representing the shape of the projectile
     */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * Moves the projectile upward on the screen at a constant speed.
     * If the projectile moves beyond the top boundary of the root pane, it is removed from the pane.
     *
     * @param root the root Pane where the projectile is displayed
     */
    public void move(Pane root) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                shape.setY(shape.getY() - SPEED);
                if (shape.getY() < 0) {
                    root.getChildren().remove(shape);
                    this.stop();
                }
            }
        }.start();
    }

    /**
     * Moves the projectile downward on the screen at a constant speed.
     * If the projectile moves beyond the bottom boundary of the root pane, it is removed from the pane.
     *
     * @param root the root Pane where the projectile is displayed
     */
    public void moveDown(Pane root) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                shape.setY(shape.getY() + SPEED - 1);
                if (shape.getY() > root.getHeight()) {
                    root.getChildren().remove(shape);
                    this.stop();
                }
            }
        }.start();
    }
}
