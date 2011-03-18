package project_tool.codemonitor;

import java.io.File;
import java.util.Date;

import java.io.FileFilter;

public class MyFileFilter implements FileFilter {
	
	private String extension;
	
	private Date lastUpdateTime;
	
	private boolean isHidden;
	
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public MyFileFilter(String extension) {
		this.extension = extension;
	}
	
	public MyFileFilter(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public MyFileFilter(String extension, Date lastUpdateTime) {
		this.extension = extension;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public MyFileFilter() {
		
	}

	public boolean accept(File f) {
		if (f.getName().toLowerCase().contains(".svn")) {
			return false;
		}
		
		if (this.isHidden && f.isHidden()) {
			return false;
		}
		
		boolean needTime = false;
		boolean timeFlag = false;
		boolean needExt = false;
		boolean extFlag = false;
		if (lastUpdateTime != null && !lastUpdateTime.after(new Date())) {
			needTime = true;
			long m = f.lastModified();
			if (m >= lastUpdateTime.getTime()) {
				timeFlag = true;
			}
		}
		
		if (this.extension != null && !this.extension.trim().equals("")) {
			needExt = true;
			String ext = f.getName().substring(f.getName().indexOf(".") + 1);
			if (ext.trim().toLowerCase().equals(this.extension.trim().toLowerCase())) {
				extFlag = true;
			}
		}
		return (needTime && timeFlag && needExt && extFlag)
				|| (needTime && timeFlag && (!needExt))
				|| ((!needTime) && needExt && extFlag)
				|| (!needTime && !timeFlag && !needExt && !extFlag);
	}
}
