package com.bitle.aGamepad;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class DataSender {
	public DataSender(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void Start() throws Exception {
		Log.d("aGamepad", "DataSender.Start enter");
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			throw new Exception ("Can't create socket");
		}
		Log.d("aGamepad", "DataSender.Start exit");
	}
	public void Stop() {
		Log.d("aGamepad", "DataSender.Stop enter");
		socket.close();
		Log.d("aGamepad", "DataSender.Stop exit");
	}
	
	public void Send(GamepadState gs) {
		//Log.d("aGamepad", "DataSender.Send enter");
		byte[] data = new byte[16];
		try {
			intToByteArray(gs.zPos, data, 0);
			intToByteArray(gs.xPos, data, 4);
			intToByteArray(gs.yPos, data, 8);
			int buttons = 0;
			for (int i = 0; i < gs.buttons.length; i++) {
				if (gs.buttons[i]) {
					buttons |= (int)Math.pow(2, i);
				}
			}
			intToByteArray(buttons, data, 12);

			socket.send(new DatagramPacket(data, data.length, InetAddress.getByName(host), port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Log.d("aGamepad", "DataSender.Send exit");
	}
	
	private void intToByteArray(int value, byte[] out, int offset) throws Exception {
        byte[] t = new byte[] {
                (byte)(value),
                (byte)(value >>> 8),
                (byte)(value >>> 16),
                (byte)(value >>> 24)};
        if (out.length < offset + 4) {
        	throw new Exception("Buffer is too small");
        }
        for (int i = 0; i < t.length ; i++ ){
        	out[offset + i] = t[i]; 
        }
	}
	private DatagramSocket socket;
	private String host;
	private int port;
}
