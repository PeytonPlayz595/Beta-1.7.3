package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet17Sleep extends Packet {
	public int field_22045_a;
	public int field_22044_b;
	public int field_22048_c;
	public int field_22047_d;
	public int field_22046_e;

	public void readPacketData(DataInputStream var1) throws IOException {
		this.field_22045_a = var1.readInt();
		this.field_22046_e = var1.readByte();
		this.field_22044_b = var1.readInt();
		this.field_22048_c = var1.readByte();
		this.field_22047_d = var1.readInt();
	}

	public void writePacketData(DataOutputStream var1) throws IOException {
		var1.writeInt(this.field_22045_a);
		var1.writeByte(this.field_22046_e);
		var1.writeInt(this.field_22044_b);
		var1.writeByte(this.field_22048_c);
		var1.writeInt(this.field_22047_d);
	}

	public void processPacket(NetHandler var1) {
		var1.func_22186_a(this);
	}

	public int getPacketSize() {
		return 14;
	}
}
