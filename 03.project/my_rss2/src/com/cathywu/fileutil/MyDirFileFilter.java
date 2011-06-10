package com.cathywu.fileutil;

import java.io.File;

import java.io.FileFilter;

public class MyDirFileFilter implements FileFilter {
	
	public MyDirFileFilter() {
		
	}

	@Override
	public boolean accept(File f) {
		if (!f.isDirectory()) {
			return false;
		}
		if (f.getName().toLowerCase().contains(".svn")) {
			return false;
		}
		return f.isDirectory();
	}
}
