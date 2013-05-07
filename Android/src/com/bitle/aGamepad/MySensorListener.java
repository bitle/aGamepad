package com.bitle.aGamepad;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MySensorListener implements SensorEventListener {
	public MySensorListener() {
		gc = GamepadController.getInstance();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		int x = (int) (Math.abs(90 - event.values[1]) / 90 * 75);
		if (x < 0)
			x = 0;
		else if (x > 150)
			x = 150;
		int y = (int) ((event.values[2] / 90) * 150);
		if (y < 0)
			y = 0;
		else if (y > 150)
			y = 150;
		int z = (int) (event.values[0] / 360 * 150);
		gc.setPos(x, y, z);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Log.v("aGamepad", "MySensorListener.onSensorChanged x: " + x + " y: " + y);
	}

	private GamepadController gc;
}
