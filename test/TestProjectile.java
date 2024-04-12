import ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx.Projectile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProjectile {

    @Test
    public void createProjectile() {
        // Create a new Projectile instance
        Projectile projectile = new Projectile(50, 50, Color.RED);

        // Test that the Projectile instance is created successfully
        Rectangle shape = projectile.getShape();
        assertEquals(50, shape.getX(), 0.001);
        assertEquals(50, shape.getY(), 0.001);
        assertEquals(Color.RED, shape.getFill());
        assertEquals(Projectile.WIDTH, shape.getWidth(), 0.001);
        assertEquals(Projectile.HEIGHT, shape.getHeight(), 0.001);
    }

    @Test
    public void createProjectile2() {
        // Create a new Projectile instance
        Projectile projectile = new Projectile(50, 50, Color.GREEN);

        // Test that the Projectile instance is created successfully
        Rectangle shape = projectile.getShape();
        assertEquals(50, shape.getX(), 0.001);
        assertEquals(50, shape.getY(), 0.001);
        assertEquals(Color.GREEN, shape.getFill());
        assertEquals(Projectile.WIDTH, shape.getWidth(), 0.001);
        assertEquals(Projectile.HEIGHT, shape.getHeight(), 0.001);
    }
}

