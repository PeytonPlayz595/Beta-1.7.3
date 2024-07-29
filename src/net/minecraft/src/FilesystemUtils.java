package net.minecraft.src;

import java.util.Collection;

import net.PeytonPlayz585.fileutils.File;
import net.PeytonPlayz585.fileutils.FileEntry;

public class FilesystemUtils {
	
	public static void recursiveDeleteDirectory(String dir) {
		Collection<FileEntry> lst = File.listFiles(dir, true, true);
		for(FileEntry t : lst) {
			if(!t.isDirectory) {
				File.deleteFile(t.path);
			}
		}
		for(FileEntry t : lst) {
			if(t.isDirectory) {
				File.deleteFile(t.path);
			}
		}
		File.deleteFile(dir);
	}

}
