package net.PeytonPlayz595.nbt;

import java.io.*;

public class NBTTagInt extends NBTBase {

	public NBTTagInt() {
	}

	public NBTTagInt(int i) {
		intValue = i;
	}

	void writeTagContents(DataOutput dataoutput) throws IOException {
		dataoutput.writeInt(intValue);
	}

	void readTagContents(DataInput datainput) throws IOException {
		intValue = datainput.readInt();
	}

	public byte getType() {
		return 3;
	}

	public String toString() {
		return (new StringBuilder()).append("").append(intValue).toString();
	}

	public int intValue;
}