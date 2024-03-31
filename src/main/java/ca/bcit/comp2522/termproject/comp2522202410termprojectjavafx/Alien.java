package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

class Alien extends Polygon {
    Alien(){
        super(0.0, 4.0, 0.0, 12.0, 4.0, 16.0,
                8.0, 16.0, 6.0, 24.0, 10.0, 24.0,
                12.0, 16.0, 16.0, 16.0, 18.0, 24.0,
                22.0, 24.0, 20.0, 16.0, 24.0, 16.0,
                28.0, 12.0, 28.0, 4.0, 24.0, 0.0,
                4.0, 0.0);

        setStrokeWidth(2);
        setStroke(Color.WHITE);
    }
}
