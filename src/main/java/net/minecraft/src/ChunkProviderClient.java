package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunkProviderClient implements IChunkProvider {
	private Chunk blankChunk;
	private Map chunkMapping = new HashMap();
	private List field_889_c = new ArrayList();
	private World worldObj;

	public ChunkProviderClient(World var1) {
		this.blankChunk = new EmptyChunk(var1, new byte[-Short.MIN_VALUE], 0, 0);
		this.worldObj = var1;
	}

	public boolean chunkExists(int var1, int var2) {
		if(this != null) {
			return true;
		} else {
			ChunkCoordIntPair var3 = new ChunkCoordIntPair(var1, var2);
			return this.chunkMapping.containsKey(var3);
		}
	}

	public void func_539_c(int var1, int var2) {
		Chunk var3 = this.provideChunk(var1, var2);
		if(!var3.func_21167_h()) {
			var3.onChunkUnload();
		}

		this.chunkMapping.remove(new ChunkCoordIntPair(var1, var2));
		this.field_889_c.remove(var3);
	}

	public Chunk prepareChunk(int var1, int var2) {
		ChunkCoordIntPair var3 = new ChunkCoordIntPair(var1, var2);
		byte[] var4 = new byte[-Short.MIN_VALUE];
		Chunk var5 = new Chunk(this.worldObj, var4, var1, var2);
		Arrays.fill(var5.skylightMap.data, (byte)-1);
		this.chunkMapping.put(var3, var5);
		var5.isChunkLoaded = true;
		return var5;
	}

	public Chunk provideChunk(int var1, int var2) {
		ChunkCoordIntPair var3 = new ChunkCoordIntPair(var1, var2);
		Chunk var4 = (Chunk)this.chunkMapping.get(var3);
		return var4 == null ? this.blankChunk : var4;
	}

	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return false;
	}

	public void populate(IChunkProvider var1, int var2, int var3) {
	}

	public String makeString() {
		return "MultiplayerChunkCache: " + this.chunkMapping.size();
	}
}
