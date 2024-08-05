package net.PeytonPlayz585.util.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.PeytonPlayz585.fileutils.File;
import net.PeytonPlayz585.fileutils.FileEntry;

public class ZipFile {
	
	private List<ZipEntry> entries = new ArrayList<ZipEntry>();
	private HashMap<String, byte[]> filePool = new HashMap<String, byte[]>();
	
	public ZipFile(ZipInputStream zis) throws IOException {
		ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            entries.add(entry);
            
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            	byte[] buffer = new byte[(int)entry.getSize()];
				int len;
				while ((len = zis.read(buffer)) > 0) {
					baos.write(buffer, 0, len);
				}

				byte[] fileData = baos.toByteArray();
				filePool.put(entry.getName(), fileData);
            }
            
            zis.closeEntry();
        }
        
        zis.close();
	}
	
	public ZipFile(FileEntry file) throws IOException {
		this(new ZipInputStream(new ByteArrayInputStream(File.readFile(file.path))));
	}

	public ZipEntry getEntry(String name) {
		for(int i = 0; i < entries.size(); i++) {
			ZipEntry entry = entries.get(i);
			if(entry != null) {
				if(entry.getName().equals(name)) {
					return entry;
				}
			}
		}
		
		return null;
	}

	public InputStream getInputStream(ZipEntry entry) {
		String name = entry.getName();
		return new ByteArrayInputStream(filePool.get(name));
	}

	public void close() {
		entries.clear();
		filePool.clear();
		entries = new ArrayList<ZipEntry>();
		filePool = new HashMap<String, byte[]>();
	}
}
