package com.thosecreepypeople.fighterinsimpletimes;

/**
 * Created by Brandon on 4/12/2015.
 */
public abstract class Gladiator {
    enum DIR {
        Up, Down, Left, Right, None
    };

    protected DIR movingDir = DIR.None;

    private int speed = 15;

    protected int currSprite = -1;

    protected int animDelay = 10;
    protected long updateFrame = 0;
    protected long animFrame = 0;
    protected int maxAnimFrameIdle = 2;
    protected int maxAnimFrameUp = 4;
    protected int maxAnimFrameDown = 6;
    protected int maxAnimFrameLeft = 7;
    protected int maxAnimFrameRight = 7;

    private int posX = 0;
    private int posY = 0;

    private void move() {
        if (movingDir != DIR.None) {
            switch (movingDir) {
                case Up:
                    posY = clamp(posY - speed, Stadium.TILE_SIZE, (Stadium.TILES_H - 2) * Stadium.TILE_SIZE);
                    break;
                case Down:
                    posY = clamp(posY + speed, Stadium.TILE_SIZE, (Stadium.TILES_H - 2) * Stadium.TILE_SIZE);
                    break;
                case Left:
                    posX = clamp(posX - speed, Stadium.TILE_SIZE, (Stadium.TILES_W - 2) * Stadium.TILE_SIZE);
                    break;
                case Right:
                    posX = clamp(posX + speed, Stadium.TILE_SIZE, (Stadium.TILES_W - 2) * Stadium.TILE_SIZE);
                    break;
            }
        }
    }

    protected abstract void updateAnimation();

    protected void setDirection(DIR dir) {
        updateFrame = movingDir != dir ? 0 : updateFrame; // reset frame if new direction
        movingDir = dir;
    }

    public void update() {
        updateAnimation();
        move();
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(val, max));
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPos(int x, int y) {
        posX = clamp(x, Stadium.TILE_SIZE, (Stadium.TILES_W - 2) * Stadium.TILE_SIZE);
        posY = clamp(y, Stadium.TILE_SIZE, (Stadium.TILES_H - 2) * Stadium.TILE_SIZE);
    }

    public int getSprite() {
        return currSprite;
    }
}
