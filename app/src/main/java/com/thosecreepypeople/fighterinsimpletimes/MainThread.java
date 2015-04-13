package com.thosecreepypeople.fighterinsimpletimes;

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

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
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
                    this.gamePanel.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            this.gamePanel.getPlayer().update();
        }

        Log.d(TAG, "Game loop executed " + tickCount + " times");
    }
}
