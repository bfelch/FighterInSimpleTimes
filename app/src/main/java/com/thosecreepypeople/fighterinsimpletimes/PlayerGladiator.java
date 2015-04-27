package com.thosecreepypeople.fighterinsimpletimes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Random;

/**
 * Created by Brandon on 4/13/2015.
 */
public class PlayerGladiator extends Gladiator implements SensorEventListener {

    private static final String TAG = PlayerGladiator.class.getSimpleName();

    private SensorManager sensorManager;
    private Sensor sensor;

    private long lastUpdate;
    private float tolerance = 1.0f;

    public PlayerGladiator(Context context) {
        super();

        Random r = new Random();
        int newX = r.nextInt((Stadium.TILES_W - 2) * Stadium.TILE_SIZE) + Stadium.TILE_SIZE;
        int newY = r.nextInt((Stadium.TILES_H - 2) * Stadium.TILE_SIZE) + Stadium.TILE_SIZE;
        setPos(newX, newY);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        currSprite = R.mipmap.bf_idle1;

        lastUpdate = System.currentTimeMillis();
    }

    @Override
    protected void updateAnimation() {
        updateFrame++;

        switch (movingDir) {
            case Up:
                animFrame = (updateFrame / animDelay) % maxAnimFrameUp;

                if (animFrame == 0) {
                    currSprite = R.mipmap.bf_back1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.bf_back2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.bf_back3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.bf_back4;
                }
                break;
            case Down:
                animFrame = (updateFrame / animDelay) % maxAnimFrameDown;

                if (animFrame == 0) {
                    currSprite = R.mipmap.bf_forward1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.bf_forward2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.bf_forward3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.bf_forward4;
                } else if (animFrame == 4) {
                    currSprite = R.mipmap.bf_forward5;
                } else if (animFrame == 5) {
                    currSprite = R.mipmap.bf_forward6;
                }
                break;
            case Left:
                animFrame = (updateFrame / animDelay) % maxAnimFrameLeft;

                if (animFrame == 0) {
                    currSprite = R.mipmap.bf_left1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.bf_left2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.bf_left3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.bf_left4;
                } else if (animFrame == 4) {
                    currSprite = R.mipmap.bf_left5;
                } else if (animFrame == 5) {
                    currSprite = R.mipmap.bf_left6;
                } else if (animFrame == 6) {
                    currSprite = R.mipmap.bf_left7;
                }
                break;
            case Right:
                animFrame = (updateFrame / animDelay) % maxAnimFrameRight;

                if (animFrame == 0) {
                    currSprite = R.mipmap.bf_right1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.bf_right2;
                } else if (animFrame == 2) {
                    currSprite = R.mipmap.bf_right3;
                } else if (animFrame == 3) {
                    currSprite = R.mipmap.bf_right4;
                } else if (animFrame == 4) {
                    currSprite = R.mipmap.bf_right5;
                } else if (animFrame == 5) {
                    currSprite = R.mipmap.bf_right6;
                } else if (animFrame == 6) {
                    currSprite = R.mipmap.bf_right7;
                }
                break;
            case None:
                animFrame = (updateFrame / animDelay) % maxAnimFrameIdle;

                if (animFrame == 0) {
                    currSprite = R.mipmap.bf_idle1;
                } else if (animFrame == 1) {
                    currSprite = R.mipmap.bf_idle2;
                }
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getOrientation(sensorEvent);
        }
    }

    public void getOrientation(SensorEvent event) {
        float[] values = event.values;

        long currTime = System.currentTimeMillis();

        if (currTime - lastUpdate > 20) {
            lastUpdate = currTime;

            if (Math.abs(values[0]) > Math.abs(values[1])) {
                if (values[0] > tolerance) {
                    setDirection(DIR.Down);
                } else if (values[0] < -tolerance) {
                    setDirection(DIR.Up);
                } else {
                    setDirection(DIR.None);
                }
            } else {
                if (values[1] > tolerance) {
                    setDirection(DIR.Right);
                } else if (values[1] < -tolerance) {
                    setDirection(DIR.Left);
                } else {
                    setDirection(DIR.None);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
