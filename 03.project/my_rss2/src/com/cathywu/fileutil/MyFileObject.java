package com.cathywu.fileutil;

import java.io.File;
import java.text.SimpleDateFormat;


public class MyFileObject{

	private File baseFile;
	
	public MyFileObject() {
		
	}
	
	public MyFileObject(File baseFile) {
		this.baseFile = baseFile;
	}
	
	public boolean isDirectory() {
		return (this.baseFile != null && this.baseFile.isDirectory());
	}
	
	public File getBaseFile() {
		return baseFile;
	}

	public void setBaseFile(File baseFile) {
		this.baseFile = baseFile;
	}

	public String getPath() {
		if (this.baseFile == null) {
			return "";
		}
		if (this.baseFile.isDirectory()) {
			return this.baseFile.getPath();
		} else {
			return this.baseFile.getParent();
		}
	}
	
	public String getName() {
		if (this.baseFile == null) {
			return "";
		}
		return this.baseFile.getName();
	}
	
	public String getExtension() {
		if (this.baseFile == null || this.baseFile.isDirectory() || !this.baseFile.getName().contains(".")) {
			return "";
		}
		String filename = this.baseFile.getName();
		return filename.substring(filename.indexOf(".") + 1);
	}
	
	public String getFileModifidTimeStr() {
		if (this.baseFile == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.baseFile.lastModified());
	}
}
