package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.OutputStream;

public class RewriteFiles {
	
	public static final String headStr = "<%@page language=\"java\" contentType=\"text/html; charset=utf-8\"%>";
	
	public void rewriteFile(String filePath, String fileType) throws IOException{
		File dir = new File(filePath);
		if(dir == null || !dir.isDirectory()){return;}
		
		File[] fileList = dir.listFiles();
		if(fileList == null || fileList.length == 0){return;}
		
		FileReader fr = null;
		BufferedReader br = null;
		
		OutputStream os = null;
		StringBuffer[] sb = null;
		
		
		
		String temp = null;
		int count = 0;
		
		for(int i = 0; i < fileList.length; i++){
			File file = fileList[i];
			if(!file.isFile() || !file.getName().endsWith(fileType) || file.getName().length() != 11){//!file.getName().equals("autosuggest_search.jsp")
				continue;
			}
//			File file2 = new File(filePath + "\\" + file.getName().substring(0, 7) + ".jsp");
//			file.renameTo(file2);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			if(fr == null || br == null){continue;}
			
			sb = new StringBuffer[1000];
			sb[0] = new StringBuffer();
			do{
				temp = br.readLine();
				sb[0].append("\r\n" + temp);
			}while(temp != null && temp.trim().length() == 0);
			
			if(temp == null || temp.trim().length() == 0){br.close(); fr.close();continue;}
			
			if(isUTF8(temp.trim())){br.close(); fr.close();continue;}
			

			System.out.println(file.getName() + " hasn't these words.");
//			System.out.println("\t" + temp.trim());
			System.out.println();
			
			//start to rewrite
			count++;
//			os = new FileOutputStream(file);
//
//			sb[0].insert(0, headStr);
//			
//			os.write(sb[0].toString().getBytes());
//			int num = 0;
//			int idx = 0;
//			while((temp = br.readLine()) != null){
//				sb[idx].append("\r\n" + temp);
//				num++;
//				if(num >= 40){
//					idx++;
//					sb[idx] = new StringBuffer();
//				}
//			}
//			
//			for(int j = 0; j < idx; j++){
//				os.write(sb[j].toString().getBytes());
//			}
//			
//			System.out.println("---------use " + idx + " StringBuffer");
//
////			os.write(sb.toString().getBytes());
//			os.close();
			br.close();
			fr.close();
		}
//		System.out.println(sb.toString());
		System.out.println("\ntotal " + count + " files");
	}
	
	private boolean isUTF8(String str){
		
		String regex = "<%@[\\s]*page[\\s]*language=(\"|\')java(\"|\')[\\s]*contentType=(\"|\')text/html[\\s]*;[\\s]*charset=((?i)utf-8)(\"|\')[\\s]*.*";
		if(str.matches(regex)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		RewriteFiles rf = new RewriteFiles();
//		System.out.println(rf.isUTF8("<%@page language=\"java\" contentType=\"text/html; charset=utf-8\"%>  "));
		try {
			rf.rewriteFile("D:\\projects\\offerme\\30_Coding\\trunk\\web", "jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
