package net.PeytonPlayz585;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import com.jcraft.jzlib.InflaterInputStream;

public class EPKDecompiler {
	
	public static class FileEntry {
		public final String name;
		public final byte[] data;
		protected FileEntry(String name, byte[] data) {
			this.name = name;
			this.data = data;
		}
	}
	
	private final ByteArrayInputStream in2;
	private DataInputStream in;
	private GL11.SHA1Digest dg = new GL11.SHA1Digest();
	private boolean isFinished = false;
	
	public EPKDecompiler(byte[] data) throws IOException {
		in2 = new ByteArrayInputStream(data);
		in = new DataInputStream(in2);
		byte[] header = new byte[8];
		in.read(header);
		if(!"EAGPKG!!".equals(new String(header, Charset.forName("UTF-8")))) throw new IOException("invalid epk file");
		in.readUTF();
		in = new DataInputStream(new InflaterInputStream(in2));
	}
	
	public FileEntry readFile() throws IOException {
		if(isFinished) {
			return null;
		}
		String s = in.readUTF();
		if(s.equals(" end")) {
			isFinished = true;
			return null;
		}else if(!s.equals("<file>")) {
			throw new IOException("invalid epk file");
		}
		String path = in.readUTF();
		byte[] digest = new byte[20];
		byte[] digest2 = new byte[20];
		in.read(digest);
		int len = in.readInt();
		byte[] file = new byte[len];
		in.read(file);
		dg.update(file, 0, len); dg.doFinal(digest2, 0);
		if(!Arrays.equals(digest, digest2)) throw new IOException("invalid file hash for "+path);
		if(!"</file>".equals(in.readUTF())) throw new IOException("invalid epk file");
		return new FileEntry(path, file);
	}
	
}