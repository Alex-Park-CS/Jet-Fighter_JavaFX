import ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx.JetFighterMain;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    @BeforeAll
    public static void setUpClass() {
        // Initialize JavaFX
        new JFXPanel();
    }


    @Test
    public void testShootAlienProjectile() {
        // Test shootAlienProjectile method
        Pane root = new Pane();
        JetFighterMain jetFighterMain = new JetFighterMain(root);
        jetFighterMain.shootAlienProjectile();
        assertFalse(jetFighterMain.getAlienProjectiles().isEmpty());
    }
}
