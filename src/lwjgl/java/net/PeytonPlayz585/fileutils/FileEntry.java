package net.PeytonPlayz585.fileutils;

public class FileEntry {
	
	public final String path;
	public final boolean isDirectory;
	public final long lastModified;
	
	public FileEntry(String path, boolean isDirectory, long lastModified) {
		this.path = path;
		this.isDirectory = isDirectory;
		this.lastModified = lastModified;
	}
	
	public String getName() {
		int i = path.indexOf('/');
		if(i >= 0) {
			return path.substring(i + 1);
		}else {
			return path;
		}
	}

	public boolean isFile() {
		return !isDirectory;
	}

	public long length() {
		return File.getFileSize(path);
	}

	public long lastModified() {
		return File.getLastModified(path);
	}
	
}