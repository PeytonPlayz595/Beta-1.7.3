package net.minecraft.src;

public abstract class MapDataBase {
	public final String field_28168_a;
	private boolean dirty;

	public MapDataBase(String var1) {
		this.field_28168_a = var1;
	}

	public abstract void readFromNBT(NBTTagCompound var1);

	public abstract void writeToNBT(NBTTagCompound var1);

	public void markDirty() {
		this.setDirty(true);
	}

	public void setDirty(boolean var1) {
		this.dirty = var1;
	}

	public boolean isDirty() {
		return this.dirty;
	}
}
