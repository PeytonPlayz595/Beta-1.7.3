package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import net.PeytonPlayz585.fileutils.File;

public class ChunkLoader implements IChunkLoader {
	private String saveDir;

	public ChunkLoader(String var1) {
		this.saveDir = var1;
	}
	
	public static final String CHUNK_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String chunkFileForXZ(int var1, int var2) {
		int unsignedX = var1 + 30233088;
		int unsignedZ = var2 + 30233088;
		int radix = CHUNK_CHARS.length();
		char[] path = new char[10];
		for(int i = 0; i < 5; ++i) {
			path[i * 2] = CHUNK_CHARS.charAt(unsignedX % radix);
			unsignedX /= radix;
			path[i * 2 + 1] = CHUNK_CHARS.charAt(unsignedZ % radix);
			unsignedZ /= radix;
		}
		String s = new String(path);
		String s1 = saveDir + "/" + s;
		return s1;
	}

	public Chunk loadChunk(World var1, int var2, int var3) throws IOException {
		String var4 = this.chunkFileForXZ(var2, var3);
		byte[] chunkData = File.readFile(var4);
		
		if(chunkData != null) {
			try {
				ByteArrayInputStream var5 = new ByteArrayInputStream(chunkData);
				NBTTagCompound var6 = CompressedStreamTools.func_1138_a(var5).getCompoundTag("Level");
				
				int x = var6.getInteger("xPos");
				int z = var6.getInteger("zPos");
				if(var2 != x || var3 != z) {
					String var7 = this.chunkFileForXZ(x, z);
					File.renameFile(var4, var7);
					return null;
				}
				
				Chunk var7 = loadChunkIntoWorldFromCompound(var1, var6);
				var7.func_25124_i();
				return var7;
			} catch(IOException e) {
				File.deleteFile(var4);
				return null;
			}
		} else {
			return null;
		}
	}

	public void saveChunk(World var1, Chunk var2) throws IOException {
		var1.checkSessionLock();
		String var3 = this.chunkFileForXZ(var2.xPosition, var2.zPosition);
		ByteArrayOutputStream var5 = new ByteArrayOutputStream();
		
		if(File.exists(var3)) {
			WorldInfo var4 = var1.getWorldInfo();
			var4.setSizeOnDisk(var4.getSizeOnDisk() - File.getFileSize(var3));
		}
		
		NBTTagCompound var6 = new NBTTagCompound();
		NBTTagCompound var7 = new NBTTagCompound();
		var6.setTag("Level", var7);
		storeChunkInCompound(var2, var1, var7);
		
		try {
			CompressedStreamTools.writeGzippedCompoundToOutputStream(var6, var5);
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		var5.flush();
		File.writeFile(var3, var5.toByteArray());
		WorldInfo var8 = var1.getWorldInfo();
		var8.setSizeOnDisk(var8.getSizeOnDisk() + File.getFileSize(var3));
	}

	public static void storeChunkInCompound(Chunk var0, World var1, NBTTagCompound var2) {
		var1.checkSessionLock();
		var2.setInteger("xPos", var0.xPosition);
		var2.setInteger("zPos", var0.zPosition);
		var2.setLong("LastUpdate", var1.getWorldTime());
		var2.setByteArray("Blocks", var0.blocks);
		var2.setByteArray("Data", var0.data.data);
		var2.setByteArray("SkyLight", var0.skylightMap.data);
		var2.setByteArray("BlockLight", var0.blocklightMap.data);
		var2.setByteArray("HeightMap", var0.heightMap);
		var2.setBoolean("TerrainPopulated", var0.isTerrainPopulated);
		var0.hasEntities = false;
		NBTTagList var3 = new NBTTagList();

		Iterator var5;
		NBTTagCompound var7;
		for(int var4 = 0; var4 < var0.entities.length; ++var4) {
			var5 = var0.entities[var4].iterator();

			while(var5.hasNext()) {
				Entity var6 = (Entity)var5.next();
				var0.hasEntities = true;
				var7 = new NBTTagCompound();
				if(var6.addEntityID(var7)) {
					var3.setTag(var7);
				}
			}
		}

		var2.setTag("Entities", var3);
		NBTTagList var8 = new NBTTagList();
		var5 = var0.chunkTileEntityMap.values().iterator();

		while(var5.hasNext()) {
			TileEntity var9 = (TileEntity)var5.next();
			var7 = new NBTTagCompound();
			var9.writeToNBT(var7);
			var8.setTag(var7);
		}

		var2.setTag("TileEntities", var8);
	}

	public static Chunk loadChunkIntoWorldFromCompound(World var0, NBTTagCompound var1) {
		int var2 = var1.getInteger("xPos");
		int var3 = var1.getInteger("zPos");
		Chunk var4 = new Chunk(var0, var2, var3);
		var4.blocks = var1.getByteArray("Blocks");
		var4.data = new NibbleArray(var1.getByteArray("Data"));
		var4.skylightMap = new NibbleArray(var1.getByteArray("SkyLight"));
		var4.blocklightMap = new NibbleArray(var1.getByteArray("BlockLight"));
		var4.heightMap = var1.getByteArray("HeightMap");
		var4.isTerrainPopulated = var1.getBoolean("TerrainPopulated");
		if(!var4.data.isValid()) {
			var4.data = new NibbleArray(var4.blocks.length);
		}

		if(var4.heightMap == null || !var4.skylightMap.isValid()) {
			var4.heightMap = new byte[256];
			var4.skylightMap = new NibbleArray(var4.blocks.length);
			var4.func_1024_c();
		}

		if(!var4.blocklightMap.isValid()) {
			var4.blocklightMap = new NibbleArray(var4.blocks.length);
			var4.func_1014_a();
		}

		NBTTagList var5 = var1.getTagList("Entities");
		if(var5 != null) {
			for(int var6 = 0; var6 < var5.tagCount(); ++var6) {
				NBTTagCompound var7 = (NBTTagCompound)var5.tagAt(var6);
				Entity var8 = EntityList.createEntityFromNBT(var7, var0);
				var4.hasEntities = true;
				if(var8 != null) {
					var4.addEntity(var8);
				}
			}
		}

		NBTTagList var10 = var1.getTagList("TileEntities");
		if(var10 != null) {
			for(int var11 = 0; var11 < var10.tagCount(); ++var11) {
				NBTTagCompound var12 = (NBTTagCompound)var10.tagAt(var11);
				TileEntity var9 = TileEntity.createAndLoadEntity(var12);
				if(var9 != null) {
					var4.addTileEntity(var9);
				}
			}
		}

		return var4;
	}

	public void func_814_a() {
	}

	public void saveExtraData() {
	}

	public void saveExtraChunkData(World var1, Chunk var2) throws IOException {
	}
}
