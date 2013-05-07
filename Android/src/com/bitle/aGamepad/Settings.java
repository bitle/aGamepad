package com.bitle.aGamepad;

public class Settings {
	public static boolean isGamepadOn () {
		return true;
	}
	public static void stopGamepad(){
		
	}
	public static String getReceiverIP() {
		return ip;
	}
	public static void setReceiverIP(String _ip) {
		ip = _ip;
	}
	public  static int getReceiverPort() {
		return 5000;
	}
	private static String ip; 
}
