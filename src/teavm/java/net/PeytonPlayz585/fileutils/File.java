package net.PeytonPlayz585.fileutils;

import java.util.Collection;

import net.PeytonPlayz585.Client;
import net.PeytonPlayz585.fileutils.IndexedDBFilesystem.OpenState;

public class File {
	
	public static final boolean fileExists(String path) {
		return IndexedDBFilesystem.fileExists(path);
	}
	
	public static final boolean directoryExists(String path) {
		return IndexedDBFilesystem.directoryExists(path);
	}
	
	public static final boolean pathExists(String path) {
		return IndexedDBFilesystem.pathExists(path);
	}
	
	public static final boolean exists(String path) {
		return readFile(path) != null;
	}
	
	public static final void mkdir(String path) {
		String[] parts = path.split("/");
		String dir = parts[parts.length - 1];
		
		if(path.endsWith("/")) {
			String file = "placeholder.txt";
			path = path.replace(dir + "/", "");
			if(!path.endsWith("/")) {
				path = path + "/";
			}
			writeFile(path + dir + "/" + file, "UwU".getBytes());
		} else {
			String file = "placeholder.txt";
			path = path.replace(dir, "");
			if(!path.endsWith("/")) {
				path = path + "/";
			}
			writeFile(path + dir + "/" + file, "UwU".getBytes());
		}
	}
	
	public static final void writeFile(String path, byte[] data) {
		IndexedDBFilesystem.writeFile(path, data);
	}
	
	public static final byte[] readFile(String path) {
		return IndexedDBFilesystem.readFile(path);
	}
	
	public static final long getLastModified(String path) {
		return IndexedDBFilesystem.getLastModified(path);
	}
	
	public static final int getFileSize(String path) {
		return IndexedDBFilesystem.getFileSize(path);
	}
	
	public static final void renameFile(String oldPath, String newPath) {
		IndexedDBFilesystem.renameFile(oldPath, newPath);
	}
	
	public static final void copyFile(String oldPath, String newPath) {
		IndexedDBFilesystem.copyFile(oldPath, newPath);
	}
	
	public static final void deleteFile(String path) {
		IndexedDBFilesystem.deleteFile(path);
	}

	public static final Collection<FileEntry> listFiles(String path, boolean listDirs, boolean recursiveDirs) {
		return IndexedDBFilesystem.listFiles(path, listDirs, recursiveDirs);
	}
	
	public static final Collection<FileEntry> listFilesAndDirectories(String path) {
		return listFiles(path, true, false);
	}
	
	public static final Collection<FileEntry> listFilesRecursive(String path) {
		return listFiles(path, false, true);
	}
	
	public static final FileEntry[] listFiles(String path) {
		Collection<FileEntry> entries = listFilesAndDirectories(path);
		FileEntry[] entryArray = new FileEntry[entries.size()];
		
		int i = 0;
		for(FileEntry entry : entries) {
			entryArray[i] = entry;
			i = i + 1;
		}
		return entryArray;
	}
	
	public static boolean isCompressed(byte[] b) {
		if(b == null || b.length < 2) {
			return false;
		}
		return (b[0] == (byte) 0x1F) && (b[1] == (byte) 0x8B);
	}
	
	static {
		if(IndexedDBFilesystem.initialize() != OpenState.OPENED) {
			Client.showIncompatibleScreen("IndexedDB failed to initialize!");
		}
	}

}
