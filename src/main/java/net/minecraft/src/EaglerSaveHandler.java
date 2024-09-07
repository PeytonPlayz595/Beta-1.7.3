package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.PeytonPlayz585.fileutils.File;

public class EaglerSaveHandler implements ISaveHandler {
	
	private String saveDir;
	
	public EaglerSaveHandler(String s) {
		this.saveDir = s;
	}

	public WorldInfo loadWorldInfo() {
		byte[] file = File.readFile(saveDir + "/level.dat");
		if(file != null) {
			try {
				NBTBase nbt = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(file)));
				if(nbt instanceof NBTTagCompound) {
					return new WorldInfo((NBTTagCompound)nbt);
				}
			} catch (IOException e) {
				System.err.println("Failed to load world data for '" + saveDir + "/level.dat'");
				e.printStackTrace();
			}
		}
		return null;
	}

	public void func_22150_b() {
		//lol
	}

	public IChunkLoader getChunkLoader(WorldProvider var1) {
		if(var1 instanceof WorldProviderHell) {
			return new ChunkLoader(saveDir + "/c1");
		}else {
			return new ChunkLoader(saveDir + "/c0");
		}
	}

	public void saveWorldInfoAndPlayer(WorldInfo var1, List var2) {
		ByteArrayOutputStream out = new ByteArrayOutputStream(8192);
		DataOutputStream ds = new DataOutputStream(out);
		try {
			NBTBase.writeTag(var1.getNBTTagCompoundWithPlayer(var2), ds);
		} catch (IOException e) {
			System.err.println("Failed to serialize world data for '" + saveDir + "/level.dat'");
			e.printStackTrace();
			return;
		}
		File.writeFile(saveDir + "/level.dat", out.toByteArray());
	}

	@Override
	public void saveWorldInfo(WorldInfo var1) {
		ByteArrayOutputStream out = new ByteArrayOutputStream(8192);
		DataOutputStream ds = new DataOutputStream(out);
		try {
			NBTBase.writeTag(var1.getNBTTagCompound(), ds);
		} catch (IOException e) {
			System.err.println("Failed to serialize world data for '" + saveDir + "/level.dat'");
			e.printStackTrace();
			return;
		}
		File.writeFile(saveDir + "/level.dat", out.toByteArray());
	}

	public String func_28113_a(String var1) {
		return this.saveDir + "/" + var1 + ".dat";
	}
}
