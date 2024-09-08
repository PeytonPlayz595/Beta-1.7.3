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
	
	// ======== Virtual Filesystem Functions =============
	
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
		(new java.io.File(filesystemBaseDirectory, stripPath(path))).mkdir();
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
