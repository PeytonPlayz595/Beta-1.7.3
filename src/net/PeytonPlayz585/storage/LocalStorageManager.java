package net.PeytonPlayz585.storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz595.nbt.NBTBase;
import net.PeytonPlayz595.nbt.NBTTagCompound;
import net.lax1dude.eaglercraft.Base64;

public class LocalStorageManager {

	public static NBTTagCompound gameSettingsStorage = null;
	public static NBTTagCompound levelSettingsStorage = null;
	
	public static void loadStorage() {
		byte[] g = GL11.loadLocalStorage("g");
		byte[] p = GL11.loadLocalStorage("p");
		
		if(g != null) {
			try {
				NBTBase t = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(g)));
				if(t != null && t instanceof NBTTagCompound) {
					gameSettingsStorage = (NBTTagCompound)t;
				}
			}catch(IOException e) {
				;
			}
		}
		
		if(p != null) {
			try {
				NBTBase t = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(p)));
				if(t != null && t instanceof NBTTagCompound) {
					levelSettingsStorage = (NBTTagCompound)t;
				}
			}catch(IOException e) {
				;
			}
		}

		if(gameSettingsStorage == null) gameSettingsStorage = new NBTTagCompound();
		if(levelSettingsStorage == null) levelSettingsStorage = new NBTTagCompound();
		
	}
	
	public static void saveStorageG() {
		try {
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			NBTBase.writeTag(gameSettingsStorage, new DataOutputStream(s));
			GL11.saveLocalStorage("g", s.toByteArray());
		} catch (IOException e) {
			;
		}
	}
	
	public static void saveStorageP() {
		try {
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			NBTBase.writeTag(levelSettingsStorage, new DataOutputStream(s));
			GL11.saveLocalStorage("p", s.toByteArray());
		} catch (IOException e) {
			;
		}
	}
	
	public static String dumpConfiguration() {
		try {
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			NBTBase.writeTag(gameSettingsStorage, new DataOutputStream(s));
			return Base64.encodeBase64String(s.toByteArray());
		} catch(Throwable e) {
			return "<error>";
		}
	}

}