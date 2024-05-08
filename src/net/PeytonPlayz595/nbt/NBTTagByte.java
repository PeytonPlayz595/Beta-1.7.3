package net.PeytonPlayz595.nbt;

import java.io.*;

public class NBTTagByte extends NBTBase {

	public NBTTagByte() {
	}

	public NBTTagByte(byte byte0) {
		byteValue = byte0;
	}

	void writeTagContents(DataOutput dataoutput) throws IOException {
		dataoutput.writeByte(byteValue);
	}

	void readTagContents(DataInput datainput) throws IOException {
		byteValue = datainput.readByte();
	}

	public byte getType() {
		return 1;
	}

	public String toString() {
		return (new StringBuilder()).append("").append(byteValue).toString();
	}

	public byte byteValue;
}