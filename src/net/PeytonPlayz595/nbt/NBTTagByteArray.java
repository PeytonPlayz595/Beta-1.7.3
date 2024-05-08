package net.PeytonPlayz595.nbt;

import java.io.*;

public class NBTTagByteArray extends NBTBase {

	public NBTTagByteArray() {
	}

	public NBTTagByteArray(byte abyte0[]) {
		byteArray = abyte0;
	}

	void writeTagContents(DataOutput dataoutput) throws IOException {
		dataoutput.writeInt(byteArray.length);
		dataoutput.write(byteArray);
	}

	void readTagContents(DataInput datainput) throws IOException {
		int i = datainput.readInt();
		byteArray = new byte[i];
		datainput.readFully(byteArray);
	}

	public byte getType() {
		return 7;
	}

	public String toString() {
		return (new StringBuilder()).append("[").append(byteArray.length).append(" bytes]").toString();
	}

	public byte byteArray[];
}