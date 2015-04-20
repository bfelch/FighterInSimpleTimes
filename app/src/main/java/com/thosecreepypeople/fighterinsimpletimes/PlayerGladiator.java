package com.thosecreepypeople.fighterinsimpletimes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Brandon on 4/13/2015.
 */
public class PlayerGladiator extends Gladiator implements SensorEventListener {

    private static final String TAG = PlayerGladiator.class.getSimpleName();

    private SensorManager sensorManager;
    private Sensor sensor;

    private long lastUpdate;
    private float tolerance = 2.0f;

    public PlayerGladiator(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        lastUpdate = System.currentTimeMillis();
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
                }
            } else {
                if (values[1] > tolerance) {
                    setDirection(DIR.Right);
                } else if (values[1] < -tolerance) {
                    setDirection(DIR.Left);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
