package net.minecraft.src;

import java.util.Collection;

import net.PeytonPlayz585.opengl.GL11;

public class FilesystemUtils {
	
	public static void recursiveDeleteDirectory(String dir) {
		Collection<GL11.FileEntry> lst = GL11.listFiles(dir, true, true);
		for(GL11.FileEntry t : lst) {
			if(!t.isDirectory) {
				GL11.deleteFile(t.path);
			}
		}
		for(GL11.FileEntry t : lst) {
			if(t.isDirectory) {
				GL11.deleteFile(t.path);
			}
		}
		GL11.deleteFile(dir);
	}

}
