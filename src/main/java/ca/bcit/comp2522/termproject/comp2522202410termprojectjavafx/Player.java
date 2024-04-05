package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

    private final ImageView shape;

    public Player(double x, double y, double width, double height) {
        Image playerImage = new Image("jetFighter.png"); // Replace "path_to_your_image_file.jpg" with the actual path to your image file
        shape = new ImageView(playerImage);
        shape.setX(x);
        shape.setY(y);
        shape.setFitWidth(width);
        shape.setFitHeight(height);
    }

    public ImageView getShape() {
        return shape;
    }

    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
    }

    public double getWidth() {
        return shape.getFitWidth();
    }

    public void moveLeft(double speed) {
        shape.setX(shape.getX() - speed);
    }

    public void moveRight(double speed) {
        shape.setX(shape.getX() + speed);
    }
}
