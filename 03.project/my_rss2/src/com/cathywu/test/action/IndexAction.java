/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.cathywu.fileutil.FilesUtils;
import com.cathywu.fileutil.MyFileFilter;
import com.cathywu.fileutil.MyFileObject;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

    private String path;
    
    private String basePath;
    
    private String timeStr;
    
    private String ext;
    
    private List<MyFileObject> list;

	public String getPath() {
		return path;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public String getExt() {
		return ext;
	}

	public List<MyFileObject> getList() {
		return list;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
    public String execute() throws Exception {
        try {
            if (path == null || "".equals(this.path.trim())) {
            	return SUCCESS;
            }
            
            if (this.basePath == null || "".equals(this.basePath)) {
            	Date date = null;
            	MyFileFilter filter = null;
            	if (this.timeStr != null && !"".equals(this.timeStr.trim())) {
                	date = parse(this.timeStr, "yyyy-MM-dd HH:mm:ss");
                	filter = new MyFileFilter(this.ext, date);
                } else {
                	filter = new MyFileFilter(this.ext);
                }
            	this.list = FilesUtils.getFilesByFilter(this.path, filter);
            } else {
            	this.list = getNoUseFiles();
            }
            return SUCCESS;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return ERROR;
    }
	
	public List<MyFileObject> getNoUseFiles() {
		Date date = null;
        if (this.timeStr != null && !"".equals(this.timeStr.trim())) {
        	date = parse(this.timeStr, "yyyy-MM-dd HH:mm:ss");
        }
        MyFileFilter filter = new MyFileFilter(this.ext, date);
        List<MyFileObject> lst = FilesUtils.getFilesByFilter(path, filter);
        
        List<MyFileObject> lst3 = new ArrayList<MyFileObject>();
        lst3.addAll(lst);
        
        List<MyFileObject> lst2 = null;
        
        MyFileObject m = null;
        for (int i = 0; i < lst.size(); i++) {
        	m = lst.get(i);
        	lst2 = FilesUtils.findFilesFromFile(this.basePath, "xml", m.getName());
        	if (lst2.size() > 0) {
        		lst.remove(i);
        		i--;
        	}
        }
        
        for (int i = 0; i < lst.size(); i++) {
        	m = lst.get(i);
        	lst2 = FilesUtils.findFilesFromFile("D:/projects/offerme/30_Coding/branch/om_web/src/au/com/iwanttobuy", "java", m.getName());
        	if (lst2.size() > 0) {
        		lst.remove(i);
        		i--;
        	}
        }
        
        lst2.clear();
        
        List<MyFileObject> ll = new ArrayList<MyFileObject>();
        ll.addAll(lst);
        for (MyFileObject mx : lst) {
        	for (MyFileObject m2 : lst3) {
        		if (mx.getBaseFile().getPath().equals(m2.getBaseFile().getPath())) {
        			continue;
        		}
        		if(FilesUtils.containStr(m2.getBaseFile(), mx.getName())) {
        			ll.remove(mx);
        		}
        	}
        }
        return ll;
	}
	
	public static void main(String[] args) {
		IndexAction a = new IndexAction();
		a.setPath("D:/projects/offerme/30_Coding/branch/om_web/web/");
		a.setBasePath("D:/projects/offerme/30_Coding/branch/om_web/web/WEB-INF");
		a.setExt("jsp");
		try {
			a.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Date parse(String source, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
