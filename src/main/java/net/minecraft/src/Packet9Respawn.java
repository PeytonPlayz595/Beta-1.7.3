package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet9Respawn extends Packet {
	public byte field_28048_a;

	public Packet9Respawn() {
	}

	public Packet9Respawn(byte var1) {
		this.field_28048_a = var1;
	}

	public void processPacket(NetHandler var1) {
		var1.func_9448_a(this);
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.field_28048_a = var1.readByte();
	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeByte(this.field_28048_a);
	}

	public int getPacketSize() {
		return 1;
	}
}
