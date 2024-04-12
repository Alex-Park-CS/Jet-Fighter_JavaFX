import ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx.JetFighterMain;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    private final Pane root = new Pane();
    private final JetFighterMain jetFighter = new JetFighterMain(root);

    @BeforeAll
    public static void setUpClass() {
        Platform.startup(() -> {});
    }

    @Test
    public void testShootAlienProjectile() {
        jetFighter.shootAlienProjectile();
        assertFalse(jetFighter.getAlienProjectiles().isEmpty());
    }

}
