package com.bitle.aGamepad;


public class NetworkThread extends Thread {
	public NetworkThread(DataSender ds) {
		sender = ds;
		controller = GamepadController.getInstance();
		suspend = false;
	}
	public void Pause(){
		suspend = true;
	}
	public void WakeUp(){
		suspend = false;
	}
	@Override
	public void run() {
		//Log.d("aGamepad", "NetworkThread.run enter");
		while (!interrupted()) {
			try {
				if (suspend)
					sleep((long)5000);
				//controller.wait();
				sender.Send(controller.getGamepadState());
				//controller.notifyAll();
				//sleep(1000);
			} catch (InterruptedException ex){
				sender.Stop();
			}
		}
		//Log.d("aGamepad", "NetworkThread.run exit");
	}
	boolean suspend;
	DataSender sender;
	GamepadController controller;
}
