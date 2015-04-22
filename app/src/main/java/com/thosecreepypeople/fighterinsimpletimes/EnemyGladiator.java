package com.thosecreepypeople.fighterinsimpletimes;

import java.util.Random;

/**
 * Created by Heather on 4/22/2015.
 * This class is to design the AI for the gladiator
 */
public class EnemyGladiator extends Gladiator{

    public EnemyGladiator() {
        Random r = new Random();
        int newX = r.nextInt((Stadium.TILES_W - 2) * Stadium.TILE_SIZE) + Stadium.TILE_SIZE;
        int newY = r.nextInt((Stadium.TILES_H - 2) * Stadium.TILE_SIZE) + Stadium.TILE_SIZE;
        setPos(newX, newY);

        currSprite = R.mipmap.fg_idle1;
    }

    public void chooseMovement(int level){
        int levelNum=level;
        switch(level){
            case 1:
               // random();random movement
                break;
            case 2:
                //follow();Call the method that John made=follow sprite
                break;
        }
    }

    @Override
    protected void updateAnimation() {
        updateFrame++;

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
}
