/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-11-26      Cathy Wu        Create
 */

package testFile;

import improsys.ImportContactsFromFileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MergeGBReportCsv {
	
	private static String idFilePath = "D:/development_documents/report/subreport/campaignIds.csv";
	
	private static String nameFilePath = "D:/development_documents/report/subreport/test-aaa.csv";
	
	private static String resultFilePath = "D:/development_documents/report/subreport/";
	
	public static void buildNameMap() {
		List<Integer> idList = buildIdList();
		
		String[][] array = ImportContactsFromFileUtils.readArrayFromCsvFile(nameFilePath);
		HashMap<Integer, List<String>> nameMap = new HashMap<Integer, List<String>>();
		
		List<String> tempList = null;
		int newId = 0;
		int oldId = 0;
		String[] arr = null;
		for (int i = 0; i < array.length; i++) {
			arr = array[i];
			newId = Integer.parseInt(arr[0].trim());
			if (oldId == 0) {
				tempList = new ArrayList<String>();
			}
			if (newId != oldId && oldId != 0) {
				nameMap.put(oldId, tempList);
				tempList = new ArrayList<String>();
			}
			tempList.add(arr[1].trim() + ";\n\r");
			oldId = newId;
			if (i == array.length - 1) {
				nameMap.put(oldId, tempList);
			}
		}
		
		StringBuffer sbf = new StringBuffer();
		int cnt = 0;
		List<String> temp = null;
		for (Integer id : idList) {
			sbf.append(id);
			sbf.append(",\"");
			temp = nameMap.get(id);
			cnt = 0;
			if (temp != null && temp.size() > 0) {
				for (String str : temp) {
					sbf.append(str);
					cnt++;
				}
			}
			sbf.append("\",");
			sbf.append(cnt);
			sbf.append("\n");
		}
		writeToFile(resultFilePath + System.currentTimeMillis() + ".csv", sbf.toString());
	}
	
	private static void writeToFile(String filepath, String content) {
		try {
			File file = new File(filepath);
			OutputStream os = new FileOutputStream(file);
			os.write(content.getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static List<Integer> buildIdList() {
		Integer idx = 0;
		List<Integer> idList = new ArrayList<Integer>();
		String[][] array = ImportContactsFromFileUtils.readArrayFromCsvFile(idFilePath);
		for (String[] str : array) {
			if (str == null || str.length == 0 || str[0] == null || str[0].trim().equals("")) {
				continue;
			}
			idx = Integer.parseInt(str[0].trim());
			idList.add(idx);
		}
		return idList;
	}
	
	public static void main(String[] args) {
		buildNameMap();
	}
}
