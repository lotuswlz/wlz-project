package project_tool.codemonitor;

import java.io.File;

import java.io.FileFilter;

public class MyDirFileFilter implements FileFilter {
	
	public MyDirFileFilter() {
		
	}

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
