package com.bitle.aGamepad;


public final class GamepadState {

	public GamepadState() {
		buttons = new boolean[8];
	}

	public GamepadState Clone() {
		//Log.d("aGamepad", "GamepadState.Clone from " + xPos + " " + yPos);
		GamepadState t = new GamepadState();
		t.xPos = xPos;
		t.yPos = yPos;
		t.zPos = zPos;
		t.buttons = buttons.clone();

		return t;
	}

	public int xPos;
	public int yPos;
	public int zPos;
	public boolean[] buttons;
}
