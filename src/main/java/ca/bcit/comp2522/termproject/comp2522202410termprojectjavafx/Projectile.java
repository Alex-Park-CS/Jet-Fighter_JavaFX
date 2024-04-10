package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Projectile {

    public static final int WIDTH = 5;
    public static final int HEIGHT = 10;
    public static final int SPEED = 5;

    private final Rectangle shape;

    public Projectile(double x, double y, Color projColor) {
        shape = new Rectangle(x, y, WIDTH, HEIGHT);
        shape.setFill(projColor);
    }

    public Rectangle getShape() {
        return shape;
    }

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
