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

public class JetFighterMain {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    private static final int PLAYER_WIDTH = 100;
    private static final int PLAYER_HEIGHT = 40;
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
    private void updateKillCount() {
        this.killCountText.setText("Aliens Killed: " + this.killCount);
    }

    private void updateLevelCount() {
        this.levelText.setText("Level: " + this.level);
    }

    private void updateLives() {
        this.livesText.setText("Lives: " + this.lives);
    }

    private void createAliens() {
        // update level every time create alien is called
        for (int i = 0; i < this.level * 2; i++) {
            Alien alien = new Alien(random.nextDouble() * (WIDTH - Alien.ALIEN_WIDTH),
                    random.nextDouble() * (HEIGHT - Alien.ALIEN_HEIGHT - 200));
            aliens.add(alien);
            root.getChildren().add(alien);
        }
    }

    private void moveAliens() {
        for (Alien alien : aliens) {
            alien.move();
        }
    }

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

    private void checkLevel() {
        if(this.aliens.isEmpty()){
            this.level++;
            updateLevelCount();
            createAliens();
        }
    }

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

    private void stopGame() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }

    private void stopProjectile() {
        if (gameLoop != null) {
            shootingTimer.stop();
        }
    }

    private void movePlayer() {
        if (movingLeft && player.getX() > 0) {
            player.moveLeft(PLAYER_SPEED);
        }
        if (movingRight && player.getX() < WIDTH - player.getWidth()) {
            player.moveRight(PLAYER_SPEED);
        }
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void shootProjectile() {
        Projectile projectile = new Projectile(player.getX() + player.getWidth() / 2 - (double) Projectile.WIDTH / 2,
                player.getY() - Projectile.HEIGHT,
                Color.RED);
        this.playerProjectiles.add(projectile);
        root.getChildren().add(projectile.getShape());
        projectile.move(root);
    }

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
