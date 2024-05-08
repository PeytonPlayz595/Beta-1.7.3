package net.PeytonPlayz595.nbt;

import java.io.*;

public class NBTTagShort extends NBTBase {

	public NBTTagShort() {
	}

	public NBTTagShort(short word0) {
		shortValue = word0;
	}

	void writeTagContents(DataOutput dataoutput) throws IOException {
		dataoutput.writeShort(shortValue);
	}

	void readTagContents(DataInput datainput) throws IOException {
		shortValue = datainput.readShort();
	}

	public byte getType() {
		return 2;
	}

	public String toString() {
		return (new StringBuilder()).append("").append(shortValue).toString();
	}

	public short shortValue;
}