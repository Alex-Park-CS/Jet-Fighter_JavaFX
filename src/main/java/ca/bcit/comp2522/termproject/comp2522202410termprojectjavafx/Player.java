package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {

    private final Rectangle shape;

    public Player(double x, double y, double width, double height) {
        shape = new Rectangle(x, y, width, height);
        shape.setFill(Color.BLUE);
    }

    public Rectangle getShape() {
        return shape;
    }

    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
    }

    public double getWidth() {
        return shape.getWidth();
    }

    public void moveLeft(double speed) {
        shape.setX(shape.getX() - speed);
    }

    public void moveRight(double speed) {
        shape.setX(shape.getX() + speed);
    }
}
