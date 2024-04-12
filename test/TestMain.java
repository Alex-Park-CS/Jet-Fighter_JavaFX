import ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx.JetFighterMain;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    private final Pane root = new Pane();
    private final JetFighterMain jetFighter = new JetFighterMain(root);

    @BeforeAll
    public static void setUpClass() {
        // Initialize JavaFX
        new JFXPanel();
    }

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @Test
    public void testCheckLevel_WhenAliensIsEmpty_ThenLevelIsIncrementedAndNewAliensAreCreated() {

        jetFighter.getAlienProjectiles().clear();
        jetFighter.checkLevel();

        // Assert
        assertEquals(1, jetFighter.getLevel());
        assertEquals(2, jetFighter.getAliens().size());
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
