package com.bitle.aGamepad;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bitle.views.MyButton;

public class aGamepad extends Activity implements OnClickListener  {
	private PowerManager powerManager;
	private WakeLock wakeLock;
	private SensorManager sensManager;
	private MySensorListener sensListener;
	private NetworkThread thread;
	private DataSender sender;
	private GamepadController gamepadController;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Log.d("aGamepad", "onCreate");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		try {
			wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "aGamepad");
		} catch (Exception e) {
			Log.e("aGamepad", "Can't get wakelog", e);
		}

		sensManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensListener = new MySensorListener();
		
		((MyButton)findViewById(R.id.btnUp)).setOnButtonActionListener(new MyButtonListener());
		((MyButton)findViewById(R.id.btnRight)).setOnButtonActionListener(new MyButtonListener());
		((MyButton)findViewById(R.id.btnDown)).setOnButtonActionListener(new MyButtonListener());
		((MyButton)findViewById(R.id.btnLeft)).setOnButtonActionListener(new MyButtonListener());
		Button btnIp = (Button) findViewById(R.id.btnIp);
		btnIp.setOnClickListener(this);
		
		gamepadController = GamepadController.getInstance();
		//Log.d("aGamepad", "onCreate:Starting thread");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "Exit");
		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case 1:
            TerminateApp();
            return true;
        }
       
        return super.onOptionsItemSelected(item);
	}

	private void TerminateApp() {
		if (thread != null)
			thread.interrupt();
		sensManager.unregisterListener(sensListener);
		this.finish();
	}

	@Override
	protected void onPause() {
		//Log.d("aGamepad", "onPause start");
		if (wakeLock != null) {
			wakeLock.release();
		}
		if (thread != null)
			thread.Pause();
		sensManager.unregisterListener(sensListener);
		super.onPause();
		//Log.d("aGamepad", "onPause exit");
	}

	@Override
	protected void onResume() {
		//Log.d("aGamepad", "onResume start");
		if (wakeLock != null) {
			wakeLock.acquire();
		}
		if (thread != null)
			thread.WakeUp();
		sensManager.registerListener(sensListener, sensManager
				.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
		//Log.d("aGamepad", "onResume exit");
	}
	
	
	@Override
	public void onClick(View v) {
		Button btnIp = (Button) findViewById(R.id.btnIp);
		btnIp.setEnabled(false);
		EditText et = (EditText)findViewById(R.id.editIp);
		et.setFocusable(false);
		Settings.setReceiverIP(et.getText().toString());
		
		sender = new DataSender(Settings.getReceiverIP(), Settings.getReceiverPort());
		try {
			sender.Start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		thread = new NetworkThread(sender);
		thread.setName("NetThread");
		thread.start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			gamepadController.buttonDown(5);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			gamepadController.buttonDown(6);
			return true;
		} else
			return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			gamepadController.buttonUp(5);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			gamepadController.buttonUp(6);
			return true;
		} else
			return super.onKeyUp(keyCode, event);
	}
}