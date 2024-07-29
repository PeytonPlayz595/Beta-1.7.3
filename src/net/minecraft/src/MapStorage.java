package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.PeytonPlayz585.fileutils.File;

public class MapStorage {
	private ISaveHandler field_28191_a;
	private Map loadedDataMap = new HashMap();
	private List loadedDataList = new ArrayList();
	private Map idCounts = new HashMap();

	public MapStorage(ISaveHandler var1) {
		this.field_28191_a = var1;
		this.loadIdCounts();
	}

	public MapDataBase loadData(Class var1, String var2) {
		MapDataBase var3 = (MapDataBase)this.loadedDataMap.get(var2);
		if(var3 != null) {
			return var3;
		} else {
			if(this.field_28191_a != null) {
				try {
					String var4 = this.field_28191_a.func_28113_a(var2);
					if(var4 != null && File.exists(var4)) {
						try {
							var3 = (MapDataBase)var1.getConstructor(new Class[]{String.class}).newInstance(new Object[]{var2});
						} catch (Exception var7) {
							throw new RuntimeException("Failed to instantiate " + var1.toString(), var7);
						}

						ByteArrayInputStream var5 = new ByteArrayInputStream(File.readFile(var4));
						NBTTagCompound var6 = CompressedStreamTools.func_1138_a(var5);
						var5.close();
						var3.readFromNBT(var6.getCompoundTag("data"));
					}
				} catch (Exception var8) {
					var8.printStackTrace();
				}
			}

			if(var3 != null) {
				this.loadedDataMap.put(var2, var3);
				this.loadedDataList.add(var3);
			}

			return var3;
		}
	}

	public void setData(String var1, MapDataBase var2) {
		if(var2 == null) {
			throw new RuntimeException("Can\'t set null data");
		} else {
			if(this.loadedDataMap.containsKey(var1)) {
				this.loadedDataList.remove(this.loadedDataMap.remove(var1));
			}

			this.loadedDataMap.put(var1, var2);
			this.loadedDataList.add(var2);
		}
	}

	public void saveAllData() {
		for(int var1 = 0; var1 < this.loadedDataList.size(); ++var1) {
			MapDataBase var2 = (MapDataBase)this.loadedDataList.get(var1);
			if(var2.isDirty()) {
				this.saveData(var2);
				var2.setDirty(false);
			}
		}

	}

	private void saveData(MapDataBase var1) {
		if(this.field_28191_a != null) {
			try {
				String var2 = this.field_28191_a.func_28113_a(var1.field_28168_a);
				if(var2 != null) {
					NBTTagCompound var3 = new NBTTagCompound();
					var1.writeToNBT(var3);
					NBTTagCompound var4 = new NBTTagCompound();
					var4.setCompoundTag("data", var3);
					ByteArrayOutputStream var5 = new ByteArrayOutputStream();
					CompressedStreamTools.writeGzippedCompoundToOutputStream(var4, var5);
					File.writeFile(var2, var5.toByteArray());
					var5.close();
				}
			} catch (Exception var6) {
				var6.printStackTrace();
			}

		}
	}

	private void loadIdCounts() {
		try {
			this.idCounts.clear();
			if(this.field_28191_a == null) {
				return;
			}

			String var1 = this.field_28191_a.func_28113_a("idcounts");
			if(var1 != null && File.exists(var1)) {
				DataInputStream var2 = new DataInputStream(new ByteArrayInputStream(File.readFile(var1)));
				NBTTagCompound var3 = CompressedStreamTools.func_1141_a(var2);
				var2.close();
				Iterator var4 = var3.func_28110_c().iterator();

				while(var4.hasNext()) {
					NBTBase var5 = (NBTBase)var4.next();
					if(var5 instanceof NBTTagShort) {
						NBTTagShort var6 = (NBTTagShort)var5;
						String var7 = var6.getKey();
						short var8 = var6.shortValue;
						this.idCounts.put(var7, Short.valueOf(var8));
					}
				}
			}
		} catch (Exception var9) {
			var9.printStackTrace();
		}

	}

	public int getUniqueDataId(String var1) {
		Short var2 = (Short)this.idCounts.get(var1);
		if(var2 == null) {
			var2 = Short.valueOf((short)0);
		} else {
			var2 = Short.valueOf((short)(var2.shortValue() + 1));
		}

		this.idCounts.put(var1, var2);
		if(this.field_28191_a == null) {
			return var2.shortValue();
		} else {
			try {
				String var3 = this.field_28191_a.func_28113_a("idcounts");
				if(var3 != null) {
					NBTTagCompound var4 = new NBTTagCompound();
					Iterator var5 = this.idCounts.keySet().iterator();

					while(var5.hasNext()) {
						String var6 = (String)var5.next();
						short var7 = ((Short)this.idCounts.get(var6)).shortValue();
						var4.setShort(var6, var7);
					}

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					DataOutputStream var9 = new DataOutputStream(baos);
					CompressedStreamTools.func_1139_a(var4, var9);
					File.writeFile(var3, baos.toByteArray());
					var9.close();
				}
			} catch (Exception var8) {
				var8.printStackTrace();
			}

			return var2.shortValue();
		}
	}
}
