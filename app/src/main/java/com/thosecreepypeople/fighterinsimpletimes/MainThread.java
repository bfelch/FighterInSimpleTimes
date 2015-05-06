package com.thosecreepypeople.fighterinsimpletimes;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Brandon on 4/6/2015.
 */
public class MainThread extends Thread {
    private static final String TAG = MainThread.class.getSimpleName();

    private boolean running;

    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;

    // player reference
    private static PlayerGladiator player;
    private EnemyGladiator enemy;

    public MainThread(SurfaceHolder surfaceHolder, Context context, MainGamePanel gamePanel) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

        // create player
        player = new PlayerGladiator(context);
        enemy = new EnemyGladiator(1);

        player.setCanMove(true);
        enemy.setCanMove(true);

        Gladiator.stopMoving = false;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long tickCount = 0L;

        Log.d(TAG, "Starting game loop");

        Canvas canvas;

        while (running) {
            tickCount++;

            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // draw canvas
                    this.gamePanel.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            player.update();
            enemy.update();

            Gladiator.DIR collDir = detectCollision();

            if (collDir != Gladiator.DIR.None) {
                resolveCollision(collDir);
            }
        }

        Log.d(TAG, "Game loop executed " + tickCount + " times");
    }

    // direction from player to enemy
    private Gladiator.DIR detectCollision() {
        float distX = player.getPosX() - enemy.getPosX();
        float distY = player.getPosY() - enemy.getPosY();

        float absDistX = Math.abs(distX);
        float absDistY = Math.abs(distY);

        if (absDistX < Stadium.TILE_SIZE) {
            if (absDistY < Stadium.TILE_SIZE) {
                if (absDistX >= absDistY) {
                    if (distX >= 0) {
                        return Gladiator.DIR.Left;
                    } else {
                        return Gladiator.DIR.Right;
                    }
                } else {
                    if (distY >= 0) {
                        return Gladiator.DIR.Up;
                    } else {
                        return Gladiator.DIR.Down;
                    }
                }
            }
        }

        return Gladiator.DIR.None;
    }

    private void resolveCollision(Gladiator.DIR collDir) {
        int takeHitSpeed = 8;
        int giveHitSpeed = 1;

        if (player.getDirection() == collDir) {
            if (enemy.getDirection() != Gladiator.getOpposite(collDir)) {
                enemy.setKnockout(player);
                enemy.knockBack(collDir, takeHitSpeed);
                player.knockBack(Gladiator.getOpposite(collDir), giveHitSpeed);
            }
        } else if (enemy.getDirection() == Gladiator.getOpposite(collDir)) {
            if (player.getDirection() != collDir) {
                player.setKnockout(enemy);
                player.knockBack(Gladiator.getOpposite(collDir), takeHitSpeed);
                enemy.knockBack(collDir, giveHitSpeed);
            }
        } else {
            player.knockBack(collDir, takeHitSpeed * 2);
            enemy.knockBack(Gladiator.getOpposite(collDir), takeHitSpeed * 2);
        }
    }

    public static PlayerGladiator getPlayer() {
        return player;
    }
    public EnemyGladiator getEnemy() { return enemy; }
}
