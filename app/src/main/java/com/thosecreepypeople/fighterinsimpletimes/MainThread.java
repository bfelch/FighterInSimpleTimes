package com.thosecreepypeople.fighterinsimpletimes;

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

        while (running) {
            tickCount++;
        }

        Log.d(TAG, "Game loop executed " + tickCount + " times");
    }
}
