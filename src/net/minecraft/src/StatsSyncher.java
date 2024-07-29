package net.minecraft.src;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import net.PeytonPlayz585.fileutils.File;

public class StatsSyncher {
	private volatile boolean field_27438_a = false;
	private volatile Map field_27437_b = null;
	private volatile Map field_27436_c = null;
	private StatFileWriter field_27435_d;
	private String field_27434_e;
	private String field_27433_f;
	private String field_27432_g;
	private String field_27431_h;
	private String field_27430_i;
	private String field_27429_j;
	private Session field_27428_k;
	private int field_27427_l = 0;
	private int field_27426_m = 0;

	public StatsSyncher(Session var1, StatFileWriter var2, String var3) {
		this.field_27434_e = makeFilePath(var3, "stats_" + var1.username.toLowerCase() + "_unsent.dat");
		this.field_27433_f = makeFilePath(var3, "stats_" + var1.username.toLowerCase() + ".dat");
		this.field_27430_i = makeFilePath(var3, "stats_" + var1.username.toLowerCase() + "_unsent.old");
		this.field_27429_j = makeFilePath(var3, "stats_" + var1.username.toLowerCase() + ".old");
		this.field_27432_g = makeFilePath(var3, "stats_" + var1.username.toLowerCase() + "_unsent.tmp");
		this.field_27431_h = makeFilePath(var3, "stats_" + var1.username.toLowerCase() + ".tmp");
		if(!var1.username.toLowerCase().equals(var1.username)) {
			this.func_28214_a(var3, "stats_" + var1.username + "_unsent.dat", this.field_27434_e);
			this.func_28214_a(var3, "stats_" + var1.username + ".dat", this.field_27433_f);
			this.func_28214_a(var3, "stats_" + var1.username + "_unsent.old", this.field_27430_i);
			this.func_28214_a(var3, "stats_" + var1.username + ".old", this.field_27429_j);
			this.func_28214_a(var3, "stats_" + var1.username + "_unsent.tmp", this.field_27432_g);
			this.func_28214_a(var3, "stats_" + var1.username + ".tmp", this.field_27431_h);
		}

		this.field_27435_d = var2;
		this.field_27428_k = var1;
		if(File.exists(this.field_27434_e)) {
			var2.func_27179_a(this.func_27415_a(this.field_27434_e, this.field_27432_g, this.field_27430_i));
		}

		this.func_27418_a();
	}

	private void func_28214_a(String var1, String var2, String var3) {
		String var4 = makeFilePath(var1, var2);
		if(File.exists(var4) && !File.directoryExists(var4) && !File.exists(var3)) {
			File.renameFile(var4, var3);
		}

	}

	private Map func_27415_a(String var1, String var2, String var3) {
		return File.exists(var1) ? this.func_27408_a(var1) : (File.exists(var3) ? this.func_27408_a(var3) : (File.exists(var2) ? this.func_27408_a(var2) : null));
	}

	private Map func_27408_a(String var1) {
		byte[] bytes = File.readFile(var1);
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		return StatFileWriter.func_27177_a(new String(""));
	}

	private void func_27410_a(Map var1, String var2, String var3, String var4) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter var5 = new PrintWriter(baos);

		try {
			var5.print(StatFileWriter.func_27185_a(this.field_27428_k.username, "local", var1));
			File.writeFile(var3, baos.toByteArray());
		} finally {
			var5.close();
		}

		if(File.exists(var4)) {
			File.deleteFile(var4);
		}

		if(File.exists(var2)) {
			File.renameFile(var2, var4);
		}

		File.renameFile(var3, var2);
	}

	public void func_27418_a() {
		if(this.field_27438_a) {
			throw new IllegalStateException("Can\'t get stats from server while StatsSyncher is busy!");
		} else {
			this.field_27427_l = 100;
			this.field_27438_a = true;
			(new ThreadStatSyncherReceive(this)).start();
		}
	}

	public void func_27424_a(Map var1) {
		if(this.field_27438_a) {
			throw new IllegalStateException("Can\'t save stats while StatsSyncher is busy!");
		} else {
			this.field_27427_l = 100;
			this.field_27438_a = true;
			(new ThreadStatSyncherSend(this, var1)).start();
		}
	}

	public void syncStatsFileWithMap(Map var1) {
		int var2 = 30;

		while(this.field_27438_a) {
			--var2;
			if(var2 <= 0) {
				break;
			}

			try {
				Thread.sleep(100L);
			} catch (InterruptedException var10) {
				var10.printStackTrace();
			}
		}

		this.field_27438_a = true;

		try {
			this.func_27410_a(var1, this.field_27434_e, this.field_27432_g, this.field_27430_i);
		} catch (Exception var8) {
			var8.printStackTrace();
		} finally {
			this.field_27438_a = false;
		}

	}

	public boolean func_27420_b() {
		return this.field_27427_l <= 0 && !this.field_27438_a && this.field_27436_c == null;
	}

	public void func_27425_c() {
		if(this.field_27427_l > 0) {
			--this.field_27427_l;
		}

		if(this.field_27426_m > 0) {
			--this.field_27426_m;
		}

		if(this.field_27436_c != null) {
			this.field_27435_d.func_27187_c(this.field_27436_c);
			this.field_27436_c = null;
		}

		if(this.field_27437_b != null) {
			this.field_27435_d.func_27180_b(this.field_27437_b);
			this.field_27437_b = null;
		}

	}

	static Map func_27422_a(StatsSyncher var0) {
		return var0.field_27437_b;
	}

	static String func_27423_b(StatsSyncher var0) {
		return var0.field_27433_f;
	}

	static String func_27411_c(StatsSyncher var0) {
		return var0.field_27431_h;
	}

	static String func_27413_d(StatsSyncher var0) {
		return var0.field_27429_j;
	}

	static void func_27412_a(StatsSyncher var0, Map var1, String var2, String var3, String var4) throws IOException {
		var0.func_27410_a(var1, var2, var3, var4);
	}

	static Map func_27421_a(StatsSyncher var0, Map var1) {
		return var0.field_27437_b = var1;
	}

	static Map func_27409_a(StatsSyncher var0, String var1, String var2, String var3) {
		return var0.func_27415_a(var1, var2, var3);
	}

	static boolean func_27416_a(StatsSyncher var0, boolean var1) {
		return var0.field_27438_a = var1;
	}

	static String func_27414_e(StatsSyncher var0) {
		return var0.field_27434_e;
	}

	static String func_27417_f(StatsSyncher var0) {
		return var0.field_27432_g;
	}

	static String func_27419_g(StatsSyncher var0) {
		return var0.field_27430_i;
	}
	
	private static String makeFilePath(String s, String s1) {
		return s + "/" + s1;
	}
}
