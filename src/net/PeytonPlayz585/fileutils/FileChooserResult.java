package net.PeytonPlayz585.fileutils;

public class FileChooserResult {

	public final String fileName;
	public final byte[] fileData;

	public FileChooserResult(String fileName, byte[] fileData) {
		this.fileName = fileName;
		this.fileData = fileData;
	}

}