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
    private PlayerGladiator player;
    private EnemyGladiator enemy;

    public MainThread(SurfaceHolder surfaceHolder, Context context, MainGamePanel gamePanel) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

        // create player
        player = new PlayerGladiator(context);
        enemy = new EnemyGladiator();
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
        }

        Log.d(TAG, "Game loop executed " + tickCount + " times");
    }

    public PlayerGladiator getPlayer() {
        return player;
    }
    public EnemyGladiator getEnemy() { return enemy; }
}
