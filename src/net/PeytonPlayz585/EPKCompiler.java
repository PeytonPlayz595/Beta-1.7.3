package net.PeytonPlayz585;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;

import org.lwjgl.opengl.GL11;

import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.DeflaterOutputStream;

public class EPKCompiler {
	
	private final ByteArrayOutputStream osb;
	private DataOutputStream os;
	private Deflater d;
	private final GL11.SHA1Digest dig = new GL11.SHA1Digest();
	
	public EPKCompiler(String name, int initialSize) {
		try {
			osb = new ByteArrayOutputStream(initialSize);
			d = new Deflater(9);
			os = new DataOutputStream(osb);
			os.write("EAGPKG!!".getBytes(Charset.forName("UTF-8")));
			os.writeUTF("\n\n #  eaglercraft package file - " + name + "\n #  eagler eagler eagler eagler eagler eagler eagler\n\n");
			d = new Deflater(9);
			os = new DataOutputStream(new DeflaterOutputStream(osb, d));
		}catch(Throwable t) {
			throw new RuntimeException("this happened somehow", t);
		}
	}
	
	public void append(String name, byte[] dat) {
		try {
			os.writeUTF("<file>");
			os.writeUTF(name);
			byte[] v = dat;
			dig.update(v, 0, v.length);
			byte[] final_ = new byte[20];
			dig.doFinal(final_, 0);
			os.write(final_);
			os.writeInt(v.length);
			os.write(v);
			os.writeUTF("</file>");
		}catch(Throwable t) {
			throw new RuntimeException("this happened somehow", t);
		}
	}
	
	public byte[] complete() {
		try {
			os.writeUTF(" end");
			os.flush();
			os.close();
			return osb.toByteArray();
		}catch(Throwable t) {
			throw new RuntimeException("this happened somehow", t);
		}
	}
	
}