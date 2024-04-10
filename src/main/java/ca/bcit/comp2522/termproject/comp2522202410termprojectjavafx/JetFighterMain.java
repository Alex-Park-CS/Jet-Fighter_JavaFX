package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The main class of Jet Fighter game
 */
public class JetFighterMain {

    /** Width of the game scene. */
    public static final int WIDTH = 1100;

    /** Height of the game scene. */
    public static final int HEIGHT = 700;

    /** Width of the player sprite. */
    private static final int PLAYER_WIDTH = 100;

    /** Height of the player sprite. */
    private static final int PLAYER_HEIGHT = 40;

    /** Speed of the player sprite. */
    private static final int PLAYER_SPEED = 5;

    private final Pane root;
    private final Player player;
    private int level;
    private int lives;
    private int killCount;
    private final Text killCountText;
    private final Text levelText;
    private final Text livesText;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    private final ArrayList<Alien> aliens;

    private final ArrayList<Projectile> alienProjectiles;
    private final ArrayList<Projectile> playerProjectiles;
    private AnimationTimer gameLoop;
    private AnimationTimer shootingTimer;
    private final Random random;

    /**
     * Constructor of JetFighterMain.
     * Loads the main game screen and starts game loop.
     *
     * @param root Pane
     */
    public JetFighterMain(Pane root) {
        this.root = root;
        this.player = new Player((double) WIDTH / 2 - (double) PLAYER_WIDTH / 2,
                HEIGHT - PLAYER_HEIGHT - 30, 70, 70);
        this.aliens = new ArrayList<>();
        this.random = new Random();
        this.alienProjectiles = new ArrayList<>();
        this.playerProjectiles = new ArrayList<>();
        this.level = 1;
        this.killCount = 0;
        this.lives = 5;

        this.killCountText = new Text("Aliens Killed: " + killCount);
        killCountText.setFill(Color.YELLOW);
        killCountText.setTranslateX(915);
        killCountText.setTranslateY(20);
        killCountText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        this.levelText = new Text("Level: " + level);
        levelText.setFill(Color.YELLOW);
        levelText.setTranslateX(0);
        levelText.setTranslateY(20);
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        this.livesText = new Text("Lives: " + lives);
        livesText.setFill(Color.YELLOW);
        livesText.setTranslateX(0);
        livesText.setTranslateY(550);
        livesText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        root.getChildren().add(killCountText);
        root.getChildren().add(levelText);
        root.getChildren().add(livesText);
        root.getChildren().add(player.getShape());

        createAliens();

        startGameLoop();
        startShootingAlienProjectileTimer();
        new Controller(this, root);
    }

    /**
     * Starts the game loop, which handles the continuous updates and rendering of the game.
     * The game loop calls methods to move the player, move aliens, check collisions,
     * update kill count, check level, and check collisions with the player.
     */
    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
                moveAliens();
                checkCollisions();
                checkLevel();
                updateKillCount();
                checkCollisionsPlayer();
            }
        };
        gameLoop.start();
    }

    /**
     * Starts the timer responsible for shooting alien projectiles.
     * The timer periodically calls the shootAlienProjectile() method to create and shoot projectiles.
     */
    private void startShootingAlienProjectileTimer() {
        shootingTimer = new AnimationTimer() {
            private long lastShotTime = 0;

            @Override
            public void handle(long now) {
                if (now - lastShotTime >= 80_000_000_0L) {
                    shootAlienProjectile();
                    lastShotTime = now;
                }
            }
        };
        shootingTimer.start();
    }

    /**
     * Updates the displayed kill count text to reflect the current number of aliens killed.
     */
    private void updateKillCount() {
        this.killCountText.setText("Aliens Killed: " + this.killCount);
    }

    /**
     * Updates the displayed level text to reflect the current game level.
     */
    private void updateLevelCount() {
        this.levelText.setText("Level: " + this.level);
    }

    /**
     * Updates the displayed lives text to reflect the current number of player lives.
     */
    private void updateLives() {
        this.livesText.setText("Lives: " + this.lives);
    }

    /**
     * Creates alien objects and adds them to the game scene.
     * The number of aliens created depends on the current game level.
     */
    private void createAliens() {
        // update level every time create alien is called
        for (int i = 0; i < this.level * 2; i++) {
            Alien alien = new Alien(random.nextDouble() * (WIDTH - Alien.ALIEN_WIDTH),
                    random.nextDouble() * (HEIGHT - Alien.ALIEN_HEIGHT - 200));
            aliens.add(alien);
            root.getChildren().add(alien);
        }
    }

    /**
     * Moves all alien objects present in the game scene.
     */
    private void moveAliens() {
        for (Alien alien : aliens) {
            alien.move();
        }
    }


    /**
     * Shoots a projectile from a random alien towards the player.
     * Checks if there are aliens present before shooting.
     * The projectile is added to the game scene and moves downwards.
     */
    public void shootAlienProjectile() {
        if (!aliens.isEmpty()) {
            Alien randomAlien = aliens.get(random.nextInt(aliens.size()));
            Projectile projectile = new Projectile(randomAlien.getTranslateX()
                    + Alien.ALIEN_WIDTH / 2 - (double) Projectile.WIDTH / 2,
                    randomAlien.getTranslateY() + Alien.ALIEN_HEIGHT,
                    Color.YELLOWGREEN);
            alienProjectiles.add(projectile);
            root.getChildren().add(projectile.getShape());
            projectile.moveDown(root);
        }
    }

    /**
     * Checks the level of the game and creates new aliens if the current level is completed.
     * If there are no aliens remaining, the level is incremented, and new aliens are created.
     */
    private void checkLevel() {
        if(this.aliens.isEmpty()){
            this.level++;
            updateLevelCount();
            createAliens();
        }
    }

    /**
     * Checks collisions between player projectiles and aliens.
     * Removes collided projectiles and aliens from the game scene.
     * Updates the kill count.
     */
    private void checkCollisions() {
        List<Projectile> projectilesToRemove = new ArrayList<>();
        List<Alien> aliensToRemove = new ArrayList<>();

        for (Projectile projectile : this.playerProjectiles) {
            for (Alien alien : aliens) {
                if (projectile.getShape().getBoundsInParent().intersects(alien.getBoundsInParent())) {
                    this.killCount++;
                    root.getChildren().remove(alien);
                    aliensToRemove.add(alien);
                    root.getChildren().remove(projectile.getShape());
                    projectilesToRemove.add(projectile);
                    break; // Exit the inner loop after detecting a collision
                }
            }
        }
        this.aliens.removeAll(aliensToRemove);
        this.playerProjectiles.removeAll(projectilesToRemove);
        updateKillCount();
    }

    /**
     * Checks collisions between alien projectiles and the player.
     * Removes collided projectiles from the game scene.
     * Updates the player's remaining lives.
     * Stops the game and displays "GAME OVER" if the player runs out of lives.
     */
    private void checkCollisionsPlayer() {
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile projectile : this.alienProjectiles) {
            if (projectile.getShape().getBoundsInParent().
                    intersects(player.getBoundsInParent())){
                lives--;
                root.getChildren().remove(projectile.getShape());
                projectilesToRemove.add(projectile);
                if(lives == 0){
                    stopGame();
                    stopProjectile();
                    printGameOver();
                }
                break; // Exit the inner loop after detecting a collision
            }
        }
        this.alienProjectiles.removeAll(projectilesToRemove);
        updateLives();
    }

    /**
     * Stops the game loop.
     */
    private void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    /**
     * Stops the projectile animation timer.
     */
    private void stopProjectile() {
        if (gameLoop != null) {
            shootingTimer.stop();
        }
    }

    /**
     * Moves the player sprite based on the current movement state.
     * If movingLeft is true, the player moves left.
     * If movingRight is true, the player moves right.
     */
    private void movePlayer() {
        if (movingLeft && player.getX() > 0) {
            player.moveLeft(PLAYER_SPEED);
        }
        if (movingRight && player.getX() < WIDTH - player.getWidth()) {
            player.moveRight(PLAYER_SPEED);
        }
    }

    /**
     * Sets the movement state of the player to left or right.
     *
     * @param movingLeft true if the player is moving left, false otherwise
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * Sets the movement state of the player to left or right.
     *
     * @param movingRight true if the player is moving right, false otherwise
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * Shoots a projectile from the player towards the aliens.
     * The projectile is added to the game scene and moves upwards.
     */
    public void shootProjectile() {
        Projectile projectile = new Projectile(player.getX() + player.getWidth() / 2 - (double) Projectile.WIDTH / 2,
                player.getY() - Projectile.HEIGHT,
                Color.RED);
        this.playerProjectiles.add(projectile);
        root.getChildren().add(projectile.getShape());
        projectile.move(root);
    }

    /**
     * Prints "GAME OVER" message in red at the center of the game scene.
     */
    private void printGameOver() {
        Text gameOverText = new Text("GAME OVER!!");
        gameOverText.setFill(Color.RED);
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gameOverText.setX((double) WIDTH / 2 - 120);
        gameOverText.setY((double) HEIGHT / 2);
        root.getChildren().add(gameOverText);
    }


//    public Player getPlayer() {
//        return player;
//    }
//
//    public List<Alien> getAliens() {
//        return aliens;
//    }
}
