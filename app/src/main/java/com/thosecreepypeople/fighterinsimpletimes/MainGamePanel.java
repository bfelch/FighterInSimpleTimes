package com.thosecreepypeople.fighterinsimpletimes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by Brandon on 4/6/2015.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;

    // layout of stadium
    private char[][] floor;
    private int vOffset;
    private int hOffset;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), context, this);
        setFocusable(true);

        // initialize stadium
        floor = Stadium.getStadium();

        // get offsets
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        vOffset = (size.y - (Stadium.TILES_H * Stadium.TILE_SIZE)) / 2;
        hOffset = (size.x - (Stadium.TILES_W * Stadium.TILE_SIZE)) / 2;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // start thread
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // try until thread is joined
        boolean retry = true;

        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // if upper left corner, close
        // else if edge, move in that direction
        // else print touch location
        int edgeDist = 100;

        boolean debugging = true;

        if (event.getAction() == MotionEvent.ACTION_DOWN && debugging) {
            if (event.getY() < 50 && event.getX() < 50) {
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            } else if (event.getY() > getHeight() - edgeDist) {
                thread.getEnemy().setDirection(Gladiator.DIR.Down);
            } else if (event.getY() < edgeDist) {
                thread.getEnemy().setDirection(Gladiator.DIR.Up);
            } else if (event.getX() > getWidth() - edgeDist) {
                thread.getEnemy().setDirection(Gladiator.DIR.Right);
            } else if (event.getX() < edgeDist) {
                thread.getEnemy().setDirection(Gladiator.DIR.Left);
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            thread.getEnemy().setDirection(Gladiator.DIR.None);
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        drawStadium(canvas);

        if (thread.getPlayer().getPosY() < thread.getEnemy().getPosY()) {
            drawGladiator(canvas, thread.getPlayer().getSprite(), thread.getPlayer().getPosX(), thread.getPlayer().getPosY());
            drawGladiator(canvas, thread.getEnemy().getSprite(), thread.getEnemy().getPosX(), thread.getEnemy().getPosY());
        } else {
            drawGladiator(canvas, thread.getEnemy().getSprite(), thread.getEnemy().getPosX(), thread.getEnemy().getPosY());
            drawGladiator(canvas, thread.getPlayer().getSprite(), thread.getPlayer().getPosX(), thread.getPlayer().getPosY());
        }
    }

    private void drawStadium(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.stadium), hOffset, vOffset, null);
    }

    private void drawTile(Canvas canvas, int resID, int i, int j) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), resID), hOffset + Stadium.TILE_SIZE * i, vOffset + Stadium.TILE_SIZE * j, null);
    }

    private void drawGladiator(Canvas canvas, int resID, int posX, int posY) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), resID), hOffset + posX, vOffset + posY, null);
    }

    public MainThread getThread() {
        return thread;
    }
}
