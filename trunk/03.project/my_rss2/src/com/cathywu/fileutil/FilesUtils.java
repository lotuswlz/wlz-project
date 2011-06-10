package com.cathywu.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilesUtils {

	public static List<MyFileObject> getFilesByFilter(String dir, MyFileFilter mf) {
		File files = new File(dir);
		if (files.isFile() || files.listFiles() == null || files.listFiles().length == 0) {
			return new ArrayList<MyFileObject>();
		}

		List<MyFileObject> list = new ArrayList<MyFileObject>();
		File[] childDirs = files.listFiles(new MyDirFileFilter());
		if (childDirs != null && childDirs.length > 0) {
			for (File d : childDirs) {
				list.addAll(getFilesByFilter(d.getPath(), mf));
			}
		}
		
//		MyFileFilter mf = new MyFileFilter(new Date(new Date().getTime() - 22 * 3600 * 1000));
		File[] fs = files.listFiles(mf);

		
		for (File f : fs) {
			list.add(new MyFileObject(f));
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		String dir = "D:/projects/offerme/30_Coding/branch/om_web/web/WEB-INF";
		MyFileFilter mf = new MyFileFilter("xml");
		mf.setHidden(true);
		List<MyFileObject> list = getFilesByFilter(dir, mf);
		for (MyFileObject m : list) {
			System.out.println(m.getPath() + "\\" + m.getName());
		}
		System.out.println("---------------------------------------");
		list = findFilesFromFile(dir, "xml", "WwpRL01.jsp");
		for (MyFileObject m : list) {
			System.out.println(m.getPath() + "\\" + m.getName());
		}
		
	}
	
	public static List<MyFileObject> findFilesFromFile(String dir, String baseExt, String findFileName) {
		List<MyFileObject> resultList = new ArrayList<MyFileObject>();
		MyFileFilter mf = new MyFileFilter(baseExt);
		mf.setHidden(true);
		List<MyFileObject> list = getFilesByFilter(dir, mf);
		MyFileObject f = null;
		for (int i = 0; i < list.size(); i++) {
			f = list.get(i);
			if (containStr(f.getBaseFile(), findFileName)) {
				resultList.add(f);
			}
		}
		return resultList;
	}
	
	public static boolean containStr(File file, String str) {
		try {
			FileReader fr = null;
			BufferedReader br = null;
			
			boolean flag = false;

			StringBuffer temp = new StringBuffer();
			String tmp = null;

			if (!file.isFile()) {
				return false;
			}
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			do {
				tmp = br.readLine();
				temp.append(tmp);
				if (temp.toString().contains(str)) {
					flag = true;
					break;
				}
				
			} while (tmp != null);

			br.close();
			fr.close();
			return flag;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
