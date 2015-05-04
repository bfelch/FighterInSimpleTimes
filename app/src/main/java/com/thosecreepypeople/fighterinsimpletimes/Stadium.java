package com.thosecreepypeople.fighterinsimpletimes;

/**
 * Created by Brandon on 4/12/2015.
 */
public class Stadium {

    public static final int TILE_SIZE = 128;

    public static final int ASPECT_W = 4;
    public static final int ASPECT_H = 3;

    public static final int MODIFIER = 3;

    public static final int TILES_W = ASPECT_W * MODIFIER;
    public static final int TILES_H = ASPECT_H * MODIFIER;

    public static final char TILE_SAND = 's';
    public static final char TILE_WALL = 'w';

    public static final int BACKDROP_W = 2560;
    public static final int BACKDROP_H = 1600;

    public static char[][] getStadium() {
        char[][] floor = new char[TILES_W][TILES_H];

        for (int i = 0; i < TILES_W; i++) {
            for (int j = 0; j < TILES_H; j++) {
                // if top left corner, make sand tile
                // else if edge, make wall tile
                // else, make sand tile
                if (i == 0 || i == TILES_W - 1 ||
                        j == 0 || j == TILES_H - 1) {
                    if (i == 0 && j == 0) {
                        floor[i][j] = TILE_SAND;
                    } else {
                        floor[i][j] = TILE_WALL;
                    }
                } else {
                    floor[i][j] = TILE_SAND;
                }
            }
        }

        return floor;
    }
}
