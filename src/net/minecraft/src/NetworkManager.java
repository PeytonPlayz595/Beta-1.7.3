package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.PeytonPlayz585.socket.ByteBufferDirectInputStream;
import net.PeytonPlayz585.socket.Socket;
import net.PeytonPlayz585.socket.SocketException;

public class NetworkManager {
	public static final Object threadSyncObject = new Object();
	public static int numReadThreads;
	public static int numWriteThreads;
	private Object sendQueueLock = new Object();
	private Socket networkSocket;
	private boolean isRunning = true;
	private List readPackets = Collections.synchronizedList(new ArrayList());
	private List dataPackets = Collections.synchronizedList(new ArrayList());
	private List chunkDataPackets = Collections.synchronizedList(new ArrayList());
	private NetHandler netHandler;
	private boolean isServerTerminating = false;
	private boolean isTerminating = false;
	private String terminationReason = "";
	private Object[] field_20101_t;
	private int timeSinceLastRead = 0;
	private int sendQueueByteLength = 0;
	public static int[] field_28145_d = new int[256];
	public static int[] field_28144_e = new int[256];
	public int chunkDataSendCounter = 0;
	private int field_20100_w = 50;

	public NetworkManager(Socket var1, String var2, NetHandler var3) throws IOException {
		this.networkSocket = var1;
		this.netHandler = var3;
	}

	public void addToSendQueue(Packet var1) {
		if(!this.isServerTerminating) {
			Object var2 = this.sendQueueLock;
			synchronized(var2) {
				this.sendQueueByteLength += var1.getPacketSize() + 1;
				if(var1.isChunkDataPacket) {
					this.chunkDataPackets.add(var1);
				} else {
					this.dataPackets.add(var1);
				}
				sendPacket();
				
			}
		}
	}

	private boolean sendPacket() {
		boolean var1 = false;

		try {
			int[] var10000;
			int var10001;
			Packet var2;
			Object var3;
			if(!this.dataPackets.isEmpty() && (this.chunkDataSendCounter == 0 || System.currentTimeMillis() - ((Packet)this.dataPackets.get(0)).creationTimeMillis >= (long)this.chunkDataSendCounter)) {
				var3 = this.sendQueueLock;
				synchronized(var3) {
					var2 = (Packet)this.dataPackets.remove(0);
					this.sendQueueByteLength -= var2.getPacketSize() + 1;
				}

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream socketOutputStream = new DataOutputStream(baos);
				Packet.writePacket(var2, socketOutputStream);
				baos.flush();
				socketOutputStream.flush();
				this.networkSocket.write(baos.toByteArray());
				baos.flush();
				socketOutputStream.flush();
				var10000 = field_28144_e;
				var10001 = var2.getPacketId();
				var10000[var10001] += var2.getPacketSize() + 1;
				var1 = true;
			}

			if(this.field_20100_w-- <= 0 && !this.chunkDataPackets.isEmpty() && (this.chunkDataSendCounter == 0 || System.currentTimeMillis() - ((Packet)this.chunkDataPackets.get(0)).creationTimeMillis >= (long)this.chunkDataSendCounter)) {
				var3 = this.sendQueueLock;
				synchronized(var3) {
					var2 = (Packet)this.chunkDataPackets.remove(0);
					this.sendQueueByteLength -= var2.getPacketSize() + 1;
				}

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream socketOutputStream = new DataOutputStream(baos);
				Packet.writePacket(var2, socketOutputStream);
				baos.flush();
				socketOutputStream.flush();
				this.networkSocket.write(baos.toByteArray());
				baos.flush();
				socketOutputStream.flush();
				var10000 = field_28144_e;
				var10001 = var2.getPacketId();
				var10000[var10001] += var2.getPacketSize() + 1;
				this.field_20100_w = 0;
				var1 = true;
			}
			
			return var1;
		} catch (Exception var8) {
			if(!this.isTerminating) {
				this.onNetworkError(var8);
			}

			return false;
		}
	}

	public void wakeThreads() {
		// Do nothing
	}

	private ByteBuffer oldChunkBuffer = null;
	private LinkedList<ByteBuffer> readChunks = new LinkedList();
	
	public boolean readPacket() {
		boolean var1 = false;
		readChunks.clear();
		
		if(oldChunkBuffer != null) {
			readChunks.add(oldChunkBuffer);
		}
		
		byte[] packet;
		while((packet = this.networkSocket.read()) != null) {
			readChunks.add(ByteBuffer.wrap(packet));
		}
		
		if(!readChunks.isEmpty()) {
			int cap = 0;
			for(ByteBuffer b : readChunks) {
				cap += b.limit();
			}
			
			ByteBuffer stream = ByteBuffer.allocate(cap);
			for(ByteBuffer b : readChunks) {
				stream.put(b);
			}
			stream.flip();
			
			DataInputStream packetStream = new DataInputStream(new ByteBufferDirectInputStream(stream));
			while(stream.hasRemaining()) {
				stream.mark();
				try {
					Packet var2 = Packet.readPacket(packetStream, this.netHandler.isServerHandler());
					if(var2 != null) {
						int[] var10000 = field_28145_d;
						int var10001 = var2.getPacketId();
						var10000[var10001] += var2.getPacketSize() + 1;
						this.readPackets.add(var2);
						var1 = true;
					} else {
						this.networkShutdown("disconnect.endOfStream", new Object[0]);
					}
				} catch(EOFException e) {
					stream.reset();
					break;
				} catch(IOException e) {
					continue;
				} catch(Throwable t) {
					t.printStackTrace();
				}
			}
			
			if(stream.hasRemaining()) {
				oldChunkBuffer = stream.slice();
			}else {
				oldChunkBuffer = null;
			}
		}
		
		return var1;
	}

	private void onNetworkError(Exception var1) {
		var1.printStackTrace();
		this.networkShutdown("disconnect.genericReason", new Object[]{"Internal exception: " + var1.toString()});
	}

	public void networkShutdown(String var1, Object... var2) {
		if(this.isRunning) {
			this.isTerminating = true;
			this.terminationReason = var1;
			this.field_20101_t = var2;
			this.isRunning = false;

			try {
				this.networkSocket.close();
				this.networkSocket = null;
			} catch (Throwable var4) {
			}

		}
	}
	
	public void processReadPackets() {
		if(this.sendQueueByteLength > 1048576) {
			this.networkShutdown("disconnect.overflow", new Object[0]);
		}

		if(this.readPackets.isEmpty()) {
			if(this.timeSinceLastRead++ == 1200) {
				this.networkShutdown("disconnect.timeout", new Object[0]);
			}
		} else {
			this.timeSinceLastRead = 0;
		}

		int var1 = 100;

		while(!this.readPackets.isEmpty() && var1-- >= 0) {
			Packet var2 = (Packet)this.readPackets.remove(0);
			var2.processPacket(this.netHandler);
		}

		this.wakeThreads();
		if(this.isTerminating && this.readPackets.isEmpty()) {
			this.netHandler.handleErrorMessage(this.terminationReason, this.field_20101_t);
		}
		
		if(!this.networkSocket.socketOpen()) {
			onNetworkError(new SocketException(new String("Connection reset")));
		}

	}

	public void func_28142_c() {
		this.wakeThreads();
		this.isServerTerminating = true;
		(new ThreadCloseConnection(this)).start();
	}

	static boolean isRunning(NetworkManager var0) {
		return var0.isRunning;
	}

	static boolean readNetworkPacket(NetworkManager var0) {
		return var0.readPacket();
	}

	static boolean sendNetworkPacket(NetworkManager var0) {
		return var0.sendPacket();
	}

	static void func_30005_a(NetworkManager var0, Exception var1) {
		var0.onNetworkError(var1);
	}
}
