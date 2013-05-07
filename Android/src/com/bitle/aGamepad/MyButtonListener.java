package com.bitle.aGamepad;

import android.view.View;

import com.bitle.views.OnButtonActionListener;


public class MyButtonListener implements OnButtonActionListener {

	public MyButtonListener(){
		gc = GamepadController.getInstance();
	}
//	@Override
//	public void onClick(View view) {
//		switch (view.getId()) {
//		case R.id.Button01:
//			gc.buttonDown(0);
	// break;
	// case R.id.Button02:
	// gc.buttonDown(1);
	// break;
	// }
	// }

	@Override
	public void onButtonDown(View view) {
		switch (view.getId()) {
		case R.id.btnUp:
			gc.buttonDown(0);
			break;
		case R.id.btnRight:
			gc.buttonDown(1);
			break;
		case R.id.btnDown:
			gc.buttonDown(2);
			break;
		case R.id.btnLeft:
			gc.buttonDown(3);
			break;
		}
	}

	@Override
	public void onButtonUp(View view) {
		switch (view.getId()) {
		case R.id.btnUp:
			gc.buttonUp(0);
			break;
		case R.id.btnRight:
			gc.buttonUp(1);
			break;
		case R.id.btnDown:
			gc.buttonUp(2);
			break;
		case R.id.btnLeft:
			gc.buttonUp(3);
			break;
		}
	}

	GamepadController gc;
}
