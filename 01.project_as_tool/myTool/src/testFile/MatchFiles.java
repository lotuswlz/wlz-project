package testFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MatchFiles {

	public static void matchFile(String filePath){
		File dir = new File(filePath);
		if(dir == null || !dir.isDirectory()){return;}
		
		File[] fileList = dir.listFiles();
		if(fileList == null || fileList.length == 0){return;}
		
		List<String> vNames = new ArrayList<String>();
		int count = 0;
		int count1= 0;
		for(int i = 0; i < fileList.length; i++){
			File file = fileList[i];
			if(!file.isFile()){
				continue;
			}
			
			if(file.getName().endsWith(".xml")){
				vNames.add(getMainFileName(file.getName(), "-"));
				//System.out.println(file.getName());
				count++;
				continue;
			}
			
			if(vNames.contains(getMainFileName(file.getName(), "\\."))){
				System.out.println(file.getName());
				vNames.remove(getMainFileName(file.getName(), "\\."));
				count1++;
			}
		}
		System.out.println("xml:" + count + "\tjava:" + count1);
		if(vNames.size() > 0){
			for(String str : vNames){
				System.out.println(str);
			}
		}
	}
	
	private static String getMainFileName(String completedName, String midChar){
		String[] arr = completedName.split(midChar);
		return arr[0];
	}
	
	public static void main(String[] args) {
		matchFile("D:\\projects\\offerme\\30_Coding\\trunk\\src\\au\\com\\iwanttobuy\\offerme\\presentation\\action\\seller");
	}
}
