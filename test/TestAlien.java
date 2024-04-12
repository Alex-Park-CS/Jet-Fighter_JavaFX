import ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx.Alien;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestAlien {
    @Test
    public void createAlien() {
        Alien alien = new Alien(50, 50);

        assertEquals(50, alien.getTranslateX(), 0.001);
        assertEquals(50, alien.getTranslateY(), 0.001);
    }

    @Test
    public void testMove() {
        Alien alien = new Alien(50, 50);

        alien.move(null); // Passing null as the Pane parameter, as it's not used in the move method

        assertNotEquals(50, alien.getTranslateX(), 0.001);
        assertNotEquals(50, alien.getTranslateY(), 0.001);
    }
}
