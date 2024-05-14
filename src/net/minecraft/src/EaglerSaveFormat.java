package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import net.PeytonPlayz585.opengl.GL11;

import static net.PeytonPlayz585.opengl.GL11.FileEntry;

public class EaglerSaveFormat implements ISaveFormat {
	
	private String saveDir;
	
	public EaglerSaveFormat(String s) {
		this.saveDir = s;
	}

	public String func_22178_a() {
		return "Modified SaveFormat";
	}

	public ISaveHandler getSaveLoader(String var1, boolean var2) {
		if(var1.contains("saves")) {
			var1 = var1.replace("saves/", "");
		}
		return new EaglerSaveHandler(saveDir + "/" + var1);
	}

	//Returns a list a worlds
	public List func_22176_b() {
		ArrayList<SaveFormatComparator> lst = new ArrayList<>();
		GL11.listFilesAndDirectories(saveDir).forEach(new Consumer<FileEntry>() {
			@Override
			public void accept(FileEntry t) {
				if(!t.isDirectory) {
					return;
				}
				String folderName = t.getName();
				String dir = t.path;
				byte[] lvl = GL11.readFile(dir + "/level.dat");
				if(lvl != null) {
					try {
						NBTBase nbt = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(lvl)));
						if(nbt instanceof NBTTagCompound) {
							WorldInfo w =  new WorldInfo((NBTTagCompound)nbt);
							String s1 = w.getWorldName();
							if (s1 == null || MathHelper.stringNullOrLengthZero(s1)) {
								s1 = folderName;
							}
							lst.add(new SaveFormatComparator(folderName, s1, w.getLastTimePlayed(), w.getSizeOnDisk(), false));
						}else {
							throw new IOException("file '" + dir + "/level.dat' does not contain an NBTTagCompound");
						}
					}catch(IOException e) {
						System.err.println("Failed to load world data for '" + saveDir + "/level.dat'");
						System.err.println("It will be kept for future recovery");
						e.printStackTrace();
					}
				}
			}
		});
		return lst;
	}

	public void flushCache() {
		//no...
	}

	//Returns world info for the world
	public WorldInfo func_22173_b(String var1) {
		byte[] lvl = GL11.readFile(saveDir + "/" + var1 + "/level.dat");
		if(lvl != null) {
			try {
				NBTBase nbt = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(lvl)));
				if(nbt instanceof NBTTagCompound) {
					return new WorldInfo((NBTTagCompound)nbt);
				}else {
					throw new IOException("file '" + saveDir + "/" + var1 + "/level.dat' does not contain an NBTTagCompound");
				}
			}catch(IOException e) {
				System.err.println("Failed to load world data for '" + saveDir + "/level.dat'");
				System.err.println("It will be kept for future recovery");
				e.printStackTrace();
			}
		}
		return null;
	}

	//Deletes world by name
	public void func_22172_c(String var1) {
		String path = saveDir + "/" + var1;
		if(path.contains("/saves/saves/")) {
			path = path.replace("/saves/saves/", "/saves/");
		}
		FilesystemUtils.recursiveDeleteDirectory(path);
	}

	//Renames world
	public void func_22170_a(String var1, String var2) {
		if(var1.contains("/saves/saves/")) {
			var1 = var1.replace("/saves/saves/", "/saves/");
		}
		if(var2.contains("/saves/saves/")) {
			var2 = var2.replace("/saves/saves/", "/saves/");
		}
		byte[] lvl = GL11.readFile(saveDir + "/" + var1 + "/level.dat");
		if(lvl != null) {
			try {
				NBTBase nbt = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(lvl)));
				if(nbt instanceof NBTTagCompound) {
					NBTTagCompound w = (NBTTagCompound)nbt;
					w.setString("LevelName", var2);
					ByteArrayOutputStream out = new ByteArrayOutputStream(lvl.length + 16 + var2.length() * 2);
					NBTBase.writeTag(w, new DataOutputStream(out));
					GL11.writeFile(saveDir + "/" + var1 + "/level.dat", out.toByteArray());
				}else {
					throw new IOException("file '" + saveDir + "/" + var1 + "/level.dat' does not contain an NBTTagCompound");
				}
			}catch(IOException e) {
				System.err.println("Failed to load world data for '" + saveDir + "/level.dat'");
				System.err.println("It will be kept for future recovery");
				e.printStackTrace();
			}
		}
	}

	//no
	public boolean isOldMapFormat(String var1) {
		return false;
	}

	//Also no
	public boolean convertMapFormat(String var1, IProgressUpdate var2) {
		return false;
	}

}
