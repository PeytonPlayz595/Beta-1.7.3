package net.PeytonPlayz585;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.opengl.GL11;

import net.PeytonPlayz585.fileutils.File;
import net.PeytonPlayz585.fileutils.FileEntry;
import net.PeytonPlayz585.fileutils.FilesystemUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;

public class ImportExport {
	
	private static IProgressUpdate prog = null;
	private static String progressTitle = null;
	private static long lastProgressUpdate = 0l;
	
	public static String importWorld(IProgressUpdate loadingScreen, byte[] data, String fileName) {
		progressTitle = "Importing World";
		prog = loadingScreen;
		lastProgressUpdate = System.currentTimeMillis();
		loadingScreen.displayLoadingString("Importing World", "(please wait)");
		//GL11.EaglerAdapterImpl2.openFileChooser("epk", "application/epk");
		
//		byte[] loaded;
//		while((loaded = GL11.EaglerAdapterImpl2.getFileChooserResult()) == null) {
//			long t = System.currentTimeMillis();
//			if(t - lastProgressUpdate < 100l) {
//				continue;
//			}
//			lastProgressUpdate = t;
//			loadingScreen.displayLoadingString("Importing World", "(please wait)");
//		}
		
		if(data == null || data.length == 0) {
			return "$cancelled$";
		}
		
		String name = fileName;
		name = name.replaceAll("[^A-Za-z0-9\\-_]", "_").trim();
		
		while(File.exists(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name)) {
			name = "_" + name;
		}
		
		loadingScreen.displayLoadingString("Importing World", "Extracting EPK");
		
		try {
			EPKDecompiler loader = new EPKDecompiler(data);
			int counter = 0;
			EPKDecompiler.FileEntry f;
			while((f = loader.readFile()) != null) {
				File.writeFile(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name + "/" + f.name, f.data);
				counter += f.data.length;
				progress(counter);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			System.out.println();
			NBTBase b = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(File.readFile(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name + "/level.dat"))));
			if(!(b instanceof NBTTagCompound)) {
				throw new IOException("NBT in saves/" + name + "/level.dat is corrupt!");
			}
		}catch(IOException e) {
			e.printStackTrace();
			System.err.println("The folder 'saves/" + name + "/' will be deleted");
			FilesystemUtils.recursiveDeleteDirectory(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name);
		}
		
		return name;
	}
	
	public static void renameImportedWorld(String name, String displayName) {
		byte[] lvl = File.readFile(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name + "/level.dat");
		if(lvl != null) {
			try {
				NBTBase nbt = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(lvl)));
				if(nbt instanceof NBTTagCompound) {
					NBTTagCompound w = (NBTTagCompound)nbt;
					w.setString("LevelName", displayName);
					ByteArrayOutputStream out = new ByteArrayOutputStream(lvl.length + 16 + displayName.length() * 2); // should be large enough
					NBTBase.writeTag(w, new DataOutputStream(out));
					File.writeFile(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name + "/level.dat", out.toByteArray());
				}else {
					throw new IOException("file 'saves/" + name + "/level.dat' does not contain an NBTTagCompound");
				}
			}catch(IOException e) {
				System.err.println("Failed to modify world data for 'saves/" + name + "/level.dat'");
				System.err.println("It will be kept for future recovery");
				e.printStackTrace();
			}
		}
	}
	
	public static boolean exportWorld(IProgressUpdate loadingScreen, String name, String downloadName) {
		progressTitle = "Exporting World";
		prog = loadingScreen;
		loadingScreen.displayLoadingString("Exporting World", "(please wait)");
		
		if(name.contains("saves/")) {
			name = name.replace("saves/", "");
		}
		
		if(!File.exists(Minecraft.getMinecraft().getSaveDir() + "/saves/" + name + "/level.dat")) {
			System.err.println("world " + name + " does not exist!");
			return false;
		}
		
		int size = 0;
		String dir = Minecraft.getMinecraft().getSaveDir() + "/saves/" + name;
		
		try {
			EPKCompiler comp = new EPKCompiler(dir, 409600000);
			Collection<FileEntry> lst = File.listFilesRecursive(dir);
			Iterator<FileEntry> itr = lst.iterator();
			while(itr.hasNext()) {
				FileEntry t = itr.next();
				if(t.path.startsWith(dir + "/")) {
					byte[] dat = File.readFile(t.path);
					if(dat != null) {
						String fn = t.path.substring(dir.length() + 1);
						comp.append(fn, dat);
						size += dat.length;
						progress(size);
					}
				}
			}
			loadingScreen.displayLoadingString("Exporting World", "finishing...");
			GL11.EaglerAdapterImpl2.downloadFile(downloadName, comp.complete());
			return true;
		}catch(Throwable t) {
			System.err.println("Export of '" + name + "' failed!");
			t.printStackTrace();
			return false;
		}
		
//		try {
//			EPKCompiler comp = new EPKCompiler(dir, 409600000);
//			Collection<FileEntry> lst = File.listFilesAndDirectories(dir);
//			Iterator<FileEntry> itr = lst.iterator();
//			while(itr.hasNext()) {
//				FileEntry t = itr.next();
//				if(t.path.startsWith(dir + "/")) {
//					byte[] dat = File.readFile(t.path);
//					if(dat != null) {
//						String fn = t.path.substring(dir.length() + 1);
//						comp.append(fn, dat);
//						size += dat.length;
//						progress(size);
//						System.out.println(t.path);
//					}
//				}
//			}
//			loadingScreen.displayLoadingString("Exporting World", "finishing...");
//			GL11.EaglerAdapterImpl2.downloadFile(downloadName, comp.complete());
//			return true;
//		}catch(Throwable t) {
//			System.err.println("Export of '" + name + "' failed!");
//			t.printStackTrace();
//			return false;
//		}
		
	}
	
	private static void progress(int p) {
		long t = System.currentTimeMillis();
		if(t - lastProgressUpdate < 100l) {
			return;
		}
		lastProgressUpdate = t;
		String s;
		if(p < 1000) {
			s = "" + p + " B";
		}else if(p < 1000000) {
			s = "" + formatFloat(p / 1000f) + " kB";
		}else {
			s = "" + formatFloat(p / 1000000f) + " MB";
		}
		prog.displayLoadingString(progressTitle, s);
	}
	
	private static String formatFloat(float f) {
		String ret = Float.toString(f);
		int idx = ret.indexOf('.');
		if(ret.length() >= (idx + 3)) {
			ret = ret.substring(0, idx + 3);
		}
		return ret;
	}

}
