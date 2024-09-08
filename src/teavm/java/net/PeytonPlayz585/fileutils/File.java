package net.PeytonPlayz585.fileutils;

import java.util.ArrayList;
import java.util.Collection;

import net.PeytonPlayz585.Client;
import net.PeytonPlayz585.fileutils.IndexedDBFilesystem.OpenState;

public class File {
	
	private String path;
	private transient int prefixLength;
	private final char slash = '/';
	private static final String pathSeperator = "/";
	private static final String[] altPathSeperator = new String[] { "\\" };
	
	public File(String pathName) {
		 if (pathName == null) {
			 throw new NullPointerException();
		 }
		 this.path = this.normalize(pathName);
		 this.prefixLength = this.prefixLength(this.path);
	}
	
	public File(String parent, String child) {
		if (child == null) {
            throw new NullPointerException();
        }
		
		if(parent != null) {
			this.path = this.createPath(parent, child);
		} else {
			this.path = this.normalize(child);
		}
	}
	
	public File(File parent, String child) {
		if (child == null) {
            throw new NullPointerException();
        }
		if(parent != null) {
			this.path = this.createPath(parent.path, child);
		} else {
			this.path = this.normalize(child);
		}
	}
	
	private int prefixLength(String path) {
        char slash = this.slash;
        int n = path.length();
        if (n == 0) return 0;
        char c0 = path.charAt(0);
        char c1 = (n > 1) ? path.charAt(1) : 0;
        if (c0 == slash) {
            if (c1 == slash) return 2;
            return 1;
        }
        if (isLetter(c0) && (c1 == ':')) {
            if ((n > 2) && (path.charAt(2) == slash))
                return 3;
            return 2;
        }
        return 0;
    }
	
	private boolean isLetter(char c) {
        return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
    }
	
	private String normalize(String p) {
		for(int i = 0; i < altPathSeperator.length; ++i) {
			p = p.replace(altPathSeperator[i], pathSeperator);
		}
		if(p.startsWith(pathSeperator)) {
			p = p.substring(1);
		}
		if(p.endsWith(pathSeperator)) {
			p = p.substring(0, p.length() - pathSeperator.length());
		}
		return p;
	}
	
	private String[] splitPath(String p) {
		String[] pth = normalize(p).split(pathSeperator);
		for(int i = 0; i < pth.length; ++i) {
			pth[i] = pth[i].trim();
		}
		return pth;
	}
	
	private String createPath(Object... p) {
		ArrayList<String> r = new ArrayList();
		for(int i = 0; i < p.length; ++i) {
			if(p[i] == null) {
				continue;
			}
			String gg = p[i].toString();
			if(gg == null) {
				continue;
			}
			String[] parts = splitPath(gg);
			for(int j = 0; j < parts.length; ++j) {
				if(parts[j] == null || parts[j].equals(".")) {
					continue;
				}else if(parts[j].equals("..") && r.size() > 0) {
					int k = r.size() - 1;
					if(!r.get(k).equals("..")) {
						r.remove(k);
					}else {
						r.add("..");
					}
				}else {
					r.add(parts[j]);
				}
			}
		}
		if(r.size() > 0) {
			StringBuilder s = new StringBuilder();
			for(int i = 0; i < r.size(); ++i) {
				if(i > 0) {
					s.append(pathSeperator);
				}
				s.append(r.get(i));
			}
			return s.toString();
		}else {
			return null;
		}
	}
	
	public String getName() {
        int index = path.lastIndexOf(slash);
        if (index < prefixLength) return path.substring(prefixLength);
        return path.substring(index + 1);
    }
	
	public String getPath() {
        return path;
    }
	
	public boolean exists() {
		return exists(path);
	}
	
	public boolean isDirectory() {
		return exists(path) && directoryExists(path);
	}
	
	public void renameTo(File newPath) {
		renameFile(path, newPath.path);
	}
	
	public byte[] read() {
		return readFile(path);
	}
	
	public void write(byte[] data) {
		writeFile(path, data);
	}
	
	public void delete() {
		deleteFile(path);
	}
	
	public void mkdir() {
		IndexedDBFilesystem.mkdir(path);
	}
	
	public File[] listFiles() {
		Collection<FileEntry> collection = listFiles(path, false, false);
		int size = collection.size();
		FileEntry[] entries = collection.toArray(new FileEntry[size]);
		File[] files = new File[size];
		
		for(int i = 0; i < size; i++) {
			files[i] = new File(entries[i].path);
		}
		return files;
	}
	
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
		return IndexedDBFilesystem.exists(path);
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
