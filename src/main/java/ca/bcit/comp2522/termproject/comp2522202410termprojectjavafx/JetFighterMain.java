package ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JetFighterMain {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 20;
    private static final int PLAYER_SPEED = 5;

    private final Pane root;
    private final Player player;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    private ArrayList<Alien> aliens;
    private Random random;



    public JetFighterMain(Pane root) {
        this.root = root;
        this.player = new Player(WIDTH / 2 - PLAYER_WIDTH / 2, HEIGHT - PLAYER_HEIGHT - 10, 30, 20);
        this.aliens = new ArrayList<>();
        this.random = new Random();

        root.getChildren().add(player.getShape());

        createAliens();

        startGameLoop();
        startShootingAlienProjectileTimer();
        new Controller(this, root);
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
                moveAliens();
            }
        }.start();
    }

    private void startShootingAlienProjectileTimer() {
        AnimationTimer shootingTimer = new AnimationTimer() {
            private long lastShotTime = 0;

            @Override
            public void handle(long now) {
                if (now - lastShotTime >= 25_000_000_0L) {
                    shootAlienProjectile();
                    lastShotTime = now;
                }
            }
        };
        shootingTimer.start();
    }

    private void createAliens() {
        for (int i = 0; i < 5; i++) {
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
        Alien randomAlien = aliens.get(random.nextInt(aliens.size()));
        Projectile projectile = new Projectile(randomAlien.getTranslateX()
                + Alien.ALIEN_WIDTH / 2 - (double) Projectile.WIDTH / 2,
                randomAlien.getTranslateY() + Alien.ALIEN_HEIGHT,
                Color.YELLOWGREEN);
        root.getChildren().add(projectile.getShape());
        projectile.moveDown();
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
        Projectile projectile = new Projectile(player.getX() + player.getWidth() / 2 - Projectile.WIDTH / 2,
                player.getY() - Projectile.HEIGHT,
                Color.RED);
        root.getChildren().add(projectile.getShape());
        projectile.move();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Alien> getAliens() {
        return aliens;
    }
}

