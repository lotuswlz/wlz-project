package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadEmailFiles {
	
		
	public void rewriteFile(String filePath) throws IOException{
		File file = new File(filePath);
				
		FileReader fr = null;
		BufferedReader br = null;
				
		List<String> list = new ArrayList<String>();
		
		String temp = null;
		int count = 0;
		
			if(!file.isFile()){
				return;
			}
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			
			do{
				temp = br.readLine();
				if (list.contains(temp)) {
				    System.out.println(temp + " already exist...");
				    continue;
				}
	            count++;
				list.add(temp);
			} while(temp != null && temp.trim().length() > 0);
			
			br.close();
			fr.close();
//		System.out.println(sb.toString());
		System.out.println("\ntotal " + count + " emails");
		
		for (String str : list) {
		    System.out.println("'" + str + "',");
		}
	}
	
	public static void main(String[] args) {
		ReadEmailFiles rf = new ReadEmailFiles();
//		System.out.println(rf.isUTF8("<%@page language=\"java\" contentType=\"text/html; charset=utf-8\"%>  "));
		try {
			rf.rewriteFile("E:\\temp\\temp.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
