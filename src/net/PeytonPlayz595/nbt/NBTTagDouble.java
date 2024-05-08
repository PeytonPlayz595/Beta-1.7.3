package net.PeytonPlayz595.nbt;

import java.io.*;

public class NBTTagDouble extends NBTBase {

	public NBTTagDouble() {
	}

	public NBTTagDouble(double d) {
		doubleValue = d;
	}

	void writeTagContents(DataOutput dataoutput) throws IOException {
		dataoutput.writeDouble(doubleValue);
	}

	void readTagContents(DataInput datainput) throws IOException {
		doubleValue = datainput.readDouble();
	}

	public byte getType() {
		return 6;
	}

	public String toString() {
		return (new StringBuilder()).append("").append(doubleValue).toString();
	}

	public double doubleValue;
}