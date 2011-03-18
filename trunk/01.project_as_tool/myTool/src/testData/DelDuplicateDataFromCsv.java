/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-11-18      Cathy Wu        Create
 */

package testData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import improsys.ImportContactsFromFileUtils;

public class DelDuplicateDataFromCsv {

	public static String path = "D:/development_documents/report/BA-MONITOR/BA-281-seller-list.csv";
	public static String path2= "D:/development_documents/report/BA-MONITOR/unique_seller_id.csv";
	public static String rstPath = "D:/development_documents/report/BA-MONITOR/seller_list.csv";
	
	public static List<String[]> resortData() {
		String[][] array = readArrayFromCsvFile(path);
		String[][] sortArray = readArrayFromCsvFile(path2);
		
		List<String[]> result = new ArrayList<String[]>();
		List<String[]> convertArray = new ArrayList<String[]>();
		
		for (String[] arr : array) {
			convertArray.add(arr);
		}
		
		String sId = null;
		String tempId = null;
		for (int i = 0; i < sortArray.length; i++) {
			sId = sortArray[i][0];
			for (int j = 0; j < convertArray.size(); j++) {
				tempId = convertArray.get(j)[0];
				if (sId.equals(tempId)) {
					result.add(convertArray.get(j));
					convertArray.remove(j);
					break;
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		try {
			List<String[]> list = resortData();
			File file = new File(rstPath);
			OutputStream os = new FileOutputStream(file);
			for (int i = 0; i < list.size(); i++) {
				os.write((list.get(i)[list.get(i).length - 1] + "\n").getBytes());
			}
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[][] readArrayFromCsvFile(String filepath) {
		try {
			File file = new File(filepath);
			if (!file.isFile()) {
				return null;
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			List<String[]> list = new ArrayList<String[]>();
			String temp = null;
			String[] result = null;
			while ((temp = br.readLine()) != null) {
				if (!"".equals(temp.trim())) {
					result = getArrayFromCsvString(temp.trim());
					list.add(result);
				}
			}
			br.close();
			fr.close();
			if (list.size() > 0) {
				int len = list.size();
				String[][] array = new String[len][];
				for (int i = 0; i < len; i++) {
					array[i] = list.get(i);
				}
				return array;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String[] getArrayFromCsvString(String lineStr) {
		if (lineStr == null || "".equals(lineStr.trim())) {
			return null;
		}
		if (lineStr.trim().startsWith(",")) {
			lineStr = "," + lineStr.trim();
		}
		String originalStr = new String(lineStr);
		lineStr = lineStr + ",";
		lineStr = lineStr.replace(",,", ",\"\",").replace(",,", ",\"\",");
		String regex = "(\"([\"]{2}|[^\"])*\"|[^\",]+)";
		Pattern ptn = Pattern.compile(regex);
		Matcher mc = ptn.matcher(lineStr);
		List<String> arr = new ArrayList<String>();
		String temp = null;
		while (mc.find()) {
			temp = mc.group().trim();
			if (temp.indexOf("\"") == 0
					&& temp.lastIndexOf("\"") == temp.length() - 1) {
				temp = temp.substring(1, temp.length() - 1);
			}
			temp = temp.replaceAll("\"\"", "\"");
			arr.add(temp);
			arr.add(originalStr);
		}
		return arr.toArray(new String[] {});
	}
}
