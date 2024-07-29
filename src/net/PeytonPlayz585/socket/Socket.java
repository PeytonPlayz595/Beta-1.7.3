package net.PeytonPlayz585.socket;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

public class Socket {
	
	public Socket(String hostName, int port) throws IOException {
		if(!GL11.EaglerAdapterImpl2.startConnection(hostName + ":" + port)) {
			IOException e = new IOException("Connection failed: " + hostName + ":" + port);
			e.printStackTrace();
			throw e;
		}
	}
	
	public Socket(String hostName) throws IOException {
		if(!GL11.EaglerAdapterImpl2.startConnection(hostName)) {
			IOException e = new IOException("Connection failed: " + hostName);
			e.printStackTrace();
			throw e;
		}
	}
	
	public void write(byte[] data) {
		if(socketOpen()) {
			GL11.EaglerAdapterImpl2.writePacket(data);
		}
	}
	
	public byte[] read() {
		if(socketOpen()) {
			return GL11.EaglerAdapterImpl2.readPacket();
		} else {
			return null;
		}
	}
	
	public void close() {
		if(socketOpen()) {
			GL11.EaglerAdapterImpl2.endConnection();
		}
	}
	
	public boolean socketOpen() {
		return GL11.EaglerAdapterImpl2.connectionOpen();
	}

}