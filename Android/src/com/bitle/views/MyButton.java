package com.bitle.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MyButton extends Button {

	public MyButton(Context context) {
		super(context);
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			buttonListener.onButtonDown((View)this);
			break;
		case MotionEvent.ACTION_UP:
			buttonListener.onButtonUp((View)this);
			break;
		}
		return super.onTouchEvent(event);
	}
	
	public void setOnButtonActionListener(OnButtonActionListener listener) {
		buttonListener = listener;
	}
	
	protected OnButtonActionListener buttonListener;
}
