package net.PeytonPlayz585.fileutils;

import java.util.Collection;

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