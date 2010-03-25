package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindFiles {
	
//	public static final String headStr = "<%@page language=\"java\" contentType=\"text/html; charset=utf-8\"%>";
		
	public void rewriteFile(String filePath, String fileType) throws IOException{
		File dir = new File(filePath);
		if(dir == null || !dir.isDirectory()){return;}
		
		File[] fileList = dir.listFiles();
		if(fileList == null || fileList.length == 0){return;}
		
		FileReader fr = null;
		BufferedReader br = null;
				
		
		String temp = null;
		
		int num = 0;
		
		for(int i = 0; i < fileList.length; i++){
			File file = fileList[i];
			if(!file.isFile() || !file.getName().endsWith(fileType)/* || file.getName().length() != 11*/){//!file.getName().equals("autosuggest_search.jsp")
				continue;
			}

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			if(fr == null || br == null){continue;}
			
			int count = 0;
			boolean flag = false;
			do{
				temp = br.readLine();
				if(temp == null) break;
				if(count == 0){
					System.out.println(file.getName() + "\t" + file.length() + "\t" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").format(new Date(file.lastModified())));
				}
				count++;
//				if(isCTag(temp.trim())){
//					flag = true;
//					System.out.println(file.getName() + " is found");
//					System.out.println("\tline" + count + " : " + temp.trim());
//					num++;
//					break;
//				}
			}while(count <= 1000);
			
			if(flag || temp == null){
				br.close();
				fr.close();
				continue;
			}
			
//			do{
//				if(hasCout(temp)){
//					System.out.println(file.getName() + " need c tag");
//					System.out.println("\tline" + count + ": " + temp.trim());
//					break;
//				}
//				count++;
//			}while((temp = br.readLine()) != null);
			
			br.close();
			fr.close();
		}
		System.out.println("\n total " + num + " files");
	}
	
	private boolean isCTag(String str){
		
//		String regex = ".*<%@[\\s]*taglib[\\s]*uri=\"http://java.sun.com/jsp/jstl/core\"[\\s]*prefix=\"c\"[\\s]*.*";
//		String regex = "(.*<%@[\\s]*taglib[\\s]*prefix=\"s\"[\\s]*uri=\"/struts-tags\"[\\s]*.*|.*<%@[\\s]*taglib[\\s]*uri=\"/struts-tags\"[\\s]*prefix=\"s\".*)";
//		String regex = ".*<a href=.{0,2}/home.*";
		String regex = ".*<a href=\"WwpRI01.jsp\">Home.*";
//		String regex = ".*<c:set[\\s]+var=\"path\".*";
//		String regex = ".*\"/contact.*";
//		String regex = ".*account.forgot.password.submit.*";
//		String regex = ".*account.forgot.user.id.submit.*";
//		String regex = ".*my.account.info.*";
//		String regex = ".*name=\"my\"*";
		
		if(str.matches(regex)){
			return true;
		}
		return false;
	}
	
	private boolean hasCout(String str){
		
		String regex = ".*<c:.*";
		if(str.matches(regex)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		FindFiles rf = new FindFiles();
//		System.out.println(rf.isUTF8("<%@page language=\"java\" contentType=\"text/html; charset=utf-8\"%>  "));
		try {
			rf.rewriteFile("D:\\projects\\offerme\\30_Coding\\trunk\\web", "jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
