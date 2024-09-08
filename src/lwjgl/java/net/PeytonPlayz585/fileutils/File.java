package net.PeytonPlayz585.fileutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class File {
	
private static final java.io.File filesystemBaseDirectory = new java.io.File("filesystem");
	
	static {
		filesystemBaseDirectory.mkdirs();
	}
	
	// ======== Virtual Filesystem Functions =============
	
	public static final boolean exists(String path) {
		return (new java.io.File(filesystemBaseDirectory, stripPath(path))).exists();
	}
	
	public static final boolean fileExists(String path) {
		return (new java.io.File(filesystemBaseDirectory, stripPath(path))).isFile();
	}
	
	public static final boolean directoryExists(String path) {
		return (new java.io.File(filesystemBaseDirectory, stripPath(path))).isDirectory();
	}
	
	public static final boolean pathExists(String path) {
		return (new java.io.File(filesystemBaseDirectory, stripPath(path))).exists();
	}
	
	public static final void writeFile(String path, byte[] data) {
		try {
			java.io.File f = new java.io.File(filesystemBaseDirectory, stripPath(path));
			java.io.File p = f.getParentFile();
			if(p != null) {
				p.mkdirs();
			}
			FileOutputStream os = new FileOutputStream(f);
			os.write(data);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final byte[] readFile(String path) {
		java.io.File f = new java.io.File(filesystemBaseDirectory, stripPath(path));
		if(!f.isFile()) {
			return null;
		}
		try {
			byte[] ret = new byte[(int)f.length()];
			FileInputStream in = new FileInputStream(f);
			in.read(ret);
			in.close();
			return ret;
		}catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static final long getLastModified(String path) {
		return (new java.io.File(filesystemBaseDirectory, stripPath(path))).lastModified();
	}
	
	public static final int getFileSize(String path) {
		return (int)(new java.io.File(filesystemBaseDirectory, stripPath(path))).length();
	}
	
	public static final void renameFile(String oldPath, String newPath) {
		java.io.File f1 = new java.io.File(filesystemBaseDirectory, stripPath(oldPath));
		java.io.File f2 = new java.io.File(filesystemBaseDirectory, stripPath(newPath));
		if(f1.exists()) {
			if(f2.exists()) {
				try {
					FileInputStream fs1 = new FileInputStream(f1);
					FileOutputStream fs2 = new FileOutputStream(f2);
					byte[] buffer = new byte[1024 * 64];
					int a;
					while((a = fs1.read(buffer)) > 0) {
						fs2.write(buffer, 0, a);
					}
					fs1.close();
					fs2.close();
					f1.delete();
				} catch (IOException e) {
					System.err.println("Copy from '" + oldPath + "' to '" + newPath + "' failed");
					e.printStackTrace();
				}
			}else {
				java.io.File p = f2.getParentFile();
				if(p != null) {
					p.mkdirs();
				}
				f1.renameTo(f2);
			}
		}
	}
	
	public static final void copyFile(String oldPath, String newPath) {
		try {
			java.io.File ff2 = new java.io.File(filesystemBaseDirectory, stripPath(newPath));
			java.io.File p = ff2.getParentFile();
			if(p != null) {
				p.mkdirs();
			}
			FileInputStream f1 = new FileInputStream(new java.io.File(filesystemBaseDirectory, stripPath(oldPath)));
			FileOutputStream f2 = new FileOutputStream(ff2);
			byte[] buffer = new byte[1024 * 64];
			int a;
			while((a = f1.read(buffer)) > 0) {
				f2.write(buffer, 0, a);
			}
			f1.close();
			f2.close();
		} catch (IOException e) {
			System.err.println("Copy from '" + oldPath + "' to '" + newPath + "' failed");
			e.printStackTrace();
		}
	}
	
	public static final void deleteFile(String path) {
		(new java.io.File(filesystemBaseDirectory, stripPath(path))).delete();
	}
	
	public static final Collection<FileEntry> listFiles(String path, boolean listDirs, boolean recursiveDirs) {
		path = stripPath(path);
		ArrayList<FileEntry> ret = new ArrayList<>();
		java.io.File f = new java.io.File(filesystemBaseDirectory, path);
		if(f.isFile()) {
			ret.add(new FileEntry(path, false, f.lastModified()));
		}else if(f.isDirectory()) {
			for(java.io.File ff : f.listFiles()) {
				if(ff.isDirectory()) {
					if(listDirs && !recursiveDirs) {
						ret.add(new FileEntry(path + "/" + ff.getName(), true, -1l));
					}
					if(recursiveDirs) {
						recursiveListing(path + "/" + ff.getName(), ff, ret, listDirs, recursiveDirs);
					}
				}else {
					ret.add(new FileEntry(path + "/" + ff.getName(), false, ff.lastModified()));
				}
			}
		}
		return ret;
	}
	
	private static void recursiveListing(String path, java.io.File f, Collection<FileEntry> lst, boolean listDirs, boolean recursiveDirs) {
		if(f.isFile()) {
			lst.add(new FileEntry(path, false, f.lastModified()));
		}else if(f.isDirectory()) {
			if(listDirs) {
				lst.add(new FileEntry(path, true, -1l));
			}
			if(recursiveDirs) {
				for(java.io.File ff : f.listFiles()) {
					recursiveListing(path + "/" + ff.getName(), ff, lst, listDirs, recursiveDirs);
				}
			}
		}
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
	
	private static String stripPath(String str) {
		if(str.startsWith("/")) {
			str = str.substring(1);
		}
		if(str.endsWith("/")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

}
