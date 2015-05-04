package com.thosecreepypeople.fighterinsimpletimes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.Random;
import com.thosecreepypeople.fighterinsimpletimes.MainThread;

/**
 * Created by Heather on 4/22/2015.
 * This class is to design the AI for the gladiator
 */
public class EnemyGladiator extends Gladiator{

    PlayerGladiator playerGladiator;
    int randTimerLimit = 0;
    int timer = 0;

    public EnemyGladiator() {
        super();

        Random r = new Random();
        int newX = r.nextInt((Stadium.TILES_W - 2) * Stadium.TILE_SIZE) + Stadium.TILE_SIZE;
        int newY = r.nextInt((Stadium.TILES_H - 2) * Stadium.TILE_SIZE) + Stadium.TILE_SIZE;
        setPos(newX, newY);
        playerGladiator = MainThread.getPlayer();

        currSprite = R.mipmap.fg_idle1;
    }

    public void random(){
        Random r = new Random();
        int rand = r.nextInt(5);

        if(timer == randTimerLimit) {
            switch (rand) {
                case 0:
                    setDirection(DIR.Up);
                    break;
                case 1:
                    setDirection(DIR.Down);
                    break;
                case 2:
                    setDirection(DIR.Right);
                    break;
                case 3:
                    setDirection(DIR.Left);
                    break;
                case 4:
                    setDirection(DIR.None);
                    break;
            }
            timer = 0;
            randTimerLimit = r.nextInt(20 - 5 + 1) + 5;
        }

        timer++;
    }

    public void mirror(){
        if(playerGladiator.getDirection() == DIR.Down){
            setDirection(DIR.Down);
        }
        else if (playerGladiator.getDirection() == DIR.Up){
            setDirection(DIR.Up);
        }
        else if(playerGladiator.getDirection() == DIR.Left){
            setDirection(DIR.Left);
        }
        else if(playerGladiator.getDirection() == DIR.Right){
            setDirection(DIR.Right);
        }
        else{
            setDirection(DIR.None);
        }
    }

    public void follow(){
        int xDist = playerGladiator.getPosX() - getPosX();
        int yDist = playerGladiator.getPosY() - getPosY();

        if(xDist == 0){
            if(yDist > 0){
                setDirection(DIR.Down);
            }
            else {
                setDirection(DIR.Up);
            }
        }
        else if(yDist == 0){
            if(xDist > 0){
                setDirection(DIR.Right);
            }
            else {
                setDirection(DIR.Left);
            }
        }
    }

    public void chooseMovement(int level){
        switch(level){
            case 1:
                random();
                break;
            case 2:
                mirror();
                break;
            case 3:
                follow();
                break;
        }
    }

    @Override
    protected void updateAnimation() {
        updateFrame++;
        chooseMovement(1);

        int delay = 200;
        if (knockout % delay >= delay / 2) {
            currSprite = R.mipmap.none;
            return;
        }

        switch (movingDir) {
            case Up:
                animFrame = (updateFrame / animDelay) % maxAnimFrameUp;

                if (animFrame == 0) {
                    currSprite = R.mipmap.fg_back1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.fg_back2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.fg_back3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.fg_back4;
                }
                break;
            case Down:
                animFrame = (updateFrame / animDelay) % maxAnimFrameDown;

                if (animFrame == 0) {
                    currSprite = R.mipmap.fg_forward1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.fg_forward2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.fg_forward3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.fg_forward4;
                } else if (animFrame == 4) {
                    currSprite = R.mipmap.fg_forward5;
                } else if (animFrame == 5) {
                    currSprite = R.mipmap.fg_forward6;
                }
                break;
            case Left:
                animFrame = (updateFrame / animDelay) % maxAnimFrameLeft;

                if (animFrame == 0) {
                    currSprite = R.mipmap.fg_left1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.fg_left2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.fg_left3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.fg_left4;
                } else if (animFrame == 4) {
                    currSprite = R.mipmap.fg_left5;
                } else if (animFrame == 5) {
                    currSprite = R.mipmap.fg_left6;
                } else if (animFrame == 6) {
                    currSprite = R.mipmap.fg_left7;
                }
                break;
            case Right:
                animFrame = (updateFrame / animDelay) % maxAnimFrameRight;

                if (animFrame == 0) {
                    currSprite = R.mipmap.fg_right1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.fg_right2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.fg_right3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.fg_right4;
                } else if (animFrame == 4) {
                    currSprite = R.mipmap.fg_right5;
                } else if (animFrame == 5) {
                    currSprite = R.mipmap.fg_right6;
                } else if (animFrame == 6) {
                    currSprite = R.mipmap.fg_right7;
                }
                break;
            case None:
                animFrame = (updateFrame / animDelay) % maxAnimFrameIdle;

                if (animFrame == 0) {
                    currSprite = R.mipmap.fg_idle1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.fg_idle2;
                }
                break;
        }
    }

    @Override
    public void drawHealth(Canvas canvas, Bitmap heart) {
        Point size = MainGamePanel.size;

        for (int i = 0; i < health; i++) {
            canvas.drawBitmap(heart, size.x - Stadium.TILE_SIZE * (i + 1), 0, null);
        }
    }
}
