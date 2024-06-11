package net.PeytonPlayz585.socket;

import java.io.IOException;

import net.lax1dude.eaglercraft.adapter.EaglerAdapterImpl2;

public class Socket {
	
	public Socket(String hostName, int port) throws IOException {
		if(!EaglerAdapterImpl2.startConnection(hostName + ":" + port)) {
			IOException e = new IOException("Connection failed: " + hostName + ":" + port);
			e.printStackTrace();
			throw e;
		}
	}
	
	public Socket(String hostName) throws IOException {
		if(!EaglerAdapterImpl2.startConnection(hostName)) {
			IOException e = new IOException("Connection failed: " + hostName);
			e.printStackTrace();
			throw e;
		}
	}
	
	public void write(byte[] data) {
		if(socketOpen()) {
			EaglerAdapterImpl2.writePacket(data);
		}
	}
	
	public byte[] read() {
		if(socketOpen()) {
			return EaglerAdapterImpl2.readPacket();
		} else {
			return null;
		}
	}
	
	public void close() {
		if(socketOpen()) {
			EaglerAdapterImpl2.endConnection();
		}
	}
	
	public boolean socketOpen() {
		return EaglerAdapterImpl2.connectionOpen();
	}

}
