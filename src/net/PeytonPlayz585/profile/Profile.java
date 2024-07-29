package net.PeytonPlayz585.profile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.NBTTagCompound;

public class Profile {
	
	private static String username = "";
	
	public static String getName() {
		return username;
	}
	
	public static void setName(String str) {
		username = str;
		Minecraft mc = Minecraft.getMinecraft();
		if(mc != null) {
			mc.session.username = str;
		}
	}
	
	public static void read() {
		read(GL11.EaglerAdapterImpl2.loadLocalStorage("P"));
	}
	
	public static void read(byte[] profileStorage) {
		if (profileStorage == null) {
			return;
		}
		
		NBTTagCompound profile;
		try {
			profile = CompressedStreamTools.func_1138_a(new ByteArrayInputStream(profileStorage));
		}catch(IOException ex) {
			return;
		}
		
		if (profile == null || profile.hasNoTags()) {
			return;
		}
		
		String loadUsername = profile.getString("username").trim();

		if(!loadUsername.isEmpty()) {
			username = loadUsername.replaceAll("[^A-Za-z0-9]", "_");
		}
	}
	
	private static byte[] write() {
		NBTTagCompound profile = new NBTTagCompound();
		profile.setString("username", username);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			CompressedStreamTools.writeGzippedCompoundToOutputStream(profile, baos);
		} catch (IOException e) {
			return null;
		}
		return baos.toByteArray();
	}
	
	public static void save() {
		byte[] b = write();
		if(b != null) {
			GL11.EaglerAdapterImpl2.saveLocalStorage("P", b);
		}
	}
	
	static {
		read();
	}

}