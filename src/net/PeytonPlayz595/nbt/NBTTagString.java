package net.PeytonPlayz595.nbt;

import java.io.*;

public class NBTTagString extends NBTBase {

	public NBTTagString() {
	}

	public NBTTagString(String s) {
		stringValue = s;
		if (s == null) {
			throw new IllegalArgumentException("Empty string not allowed");
		} else {
			return;
		}
	}

	void writeTagContents(DataOutput dataoutput) throws IOException {
		dataoutput.writeUTF(stringValue);
	}

	void readTagContents(DataInput datainput) throws IOException {
		stringValue = datainput.readUTF();
	}

	public byte getType() {
		return 8;
	}

	public String toString() {
		return (new StringBuilder()).append("").append(stringValue).toString();
	}

	public String stringValue;
}