package com.thosecreepypeople.fighterinsimpletimes;

/**
 * Created by Brandon on 4/12/2015.
 */
public class Gladiator {
    enum DIR {
        Up, Down, Left, Right
    };

    private int posX = 0;
    private int posY = 0;

    public void move(DIR dir) {
        switch (dir) {
            case Up:
                posY = clamp(posY - Stadium.TILE_SIZE, 0, Stadium.TILES_H * Stadium.TILE_SIZE);
                break;
            case Down:
                posY = clamp(posY + Stadium.TILE_SIZE, 0, Stadium.TILES_H * Stadium.TILE_SIZE);
                break;
            case Left:
                posX = clamp(posX - Stadium.TILE_SIZE, 0, Stadium.TILES_W * Stadium.TILE_SIZE);
                break;
            case Right:
                posX = clamp(posX + Stadium.TILE_SIZE, 0, Stadium.TILES_W * Stadium.TILE_SIZE);
                break;
        }
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
}
