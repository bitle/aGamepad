package com.bitle.aGamepad;


public class GamepadController {
	public static GamepadController getInstance() {
		if (instance == null) {
			instance = new GamepadController(); 
			return instance;
		}
		else return instance;
	}
	public synchronized GamepadState getGamepadState(){
		//Log.d("aGamepad", "GamepadController.getGamepadState enter");
		GamepadState t = gamepadState.Clone();
		//notifyAll();
		//Log.d("aGamepad", "GamepadController.getGamepadState exit");
		return t;
	}
	
	public synchronized void buttonUp(int n){
		//Log.d("aGamepad", "GamepadController.buttonUp enter");
		if (n < gamepadState.buttons.length) {
			gamepadState.buttons[n] = false;
		}
		//Log.d("aGamepad", "GamepadController.buttonUp exit");
	}
	
	public synchronized void buttonDown(int n){
		//Log.d("aGamepad", "GamepadController.buttonDown enter");
		if (n < gamepadState.buttons.length) {
			gamepadState.buttons[n] = true;
		}
		//Log.d("aGamepad", "GamepadController.buttonDown exit");
	}
	
	public synchronized void setPos(int x, int y, int z){
		gamepadState.xPos = x;
		gamepadState.yPos = y;
		gamepadState.zPos = z;
	}
	
	private GamepadController() {
		gamepadState = new GamepadState();
	}
	
	private GamepadState gamepadState;
	static private GamepadController instance;
}
