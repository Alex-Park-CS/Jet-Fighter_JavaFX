package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Random;

public class Alien extends Polygon {

    private static final double[] ALIEN_SHAPE = {
            0.0, 4.0, 0.0, 12.0, 4.0, 16.0,
            8.0, 16.0, 6.0, 24.0, 10.0, 24.0,
            12.0, 16.0, 16.0, 16.0, 18.0, 24.0,
            22.0, 24.0, 20.0, 16.0, 24.0, 16.0,
            28.0, 12.0, 28.0, 4.0, 24.0, 0.0,
            4.0, 0.0
    };

    public static final double ALIEN_WIDTH = 28.0;
    public static final double ALIEN_HEIGHT = 24.0;

    private static final double MAX_SPEED = 3.0;

    private double speedX;
    private double speedY;

    public Alien(double x, double y) {
        super(ALIEN_SHAPE);
        setTranslateX(x);
        setTranslateY(y);
        setStrokeWidth(2);
        setStroke(Color.WHITE);

        Random random = new Random();
        speedX = (random.nextDouble() - 0.5) * MAX_SPEED * 2; // Random speed between -MAX_SPEED and MAX_SPEED
        speedY = (random.nextDouble() - 0.5) * MAX_SPEED * 2; // Random speed between -MAX_SPEED and MAX_SPEED
    }

    public void move() {
        setTranslateX(getTranslateX() + speedX);
        setTranslateY(getTranslateY() + speedY);

        // Reverse direction if hitting the edges
        if (getTranslateX() < 0 || getTranslateX() + ALIEN_WIDTH > JetFighterMain.WIDTH) {
            speedX = -speedX;
        }
        if (getTranslateY() < 0 || getTranslateY() + ALIEN_HEIGHT > JetFighterMain.HEIGHT) {
            speedY = -speedY;
        }
    }
}
