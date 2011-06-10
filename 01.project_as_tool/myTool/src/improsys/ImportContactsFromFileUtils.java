/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-9-22      Cathy Wu        Create
 */

package improsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import improsys.contactimport.common.Contact;

public class ImportContactsFromFileUtils {

	private static String[] outlook_name_columns = { "E-mail Address", "Name",
			"First Name", "Middle Name", "Last Name", "Nickname" };
	private static int[][] outlook_name_sorts = { { 1 }, { 2, 3, 4 }, { 5 } };
	private static int thunderbird_email_index = 4;
	private static int[][] thunderbird_name_sorts = { { 2 }, { 0, 1 }, { 3 } };
	private String filepath;
	private byte type; // 1: outlook; 2: thunder bird

	public ImportContactsFromFileUtils(String filepath, byte type) {
		this.filepath = filepath;
		this.type = type;
	}

	private List<Contact> importContactsFromOutlookFile() {
		String[][] csvArray = readArrayFromCsvFile(this.filepath);
		if (csvArray == null || csvArray.length <= 1) {
			return null;
		}
		String[] columns = csvArray[0];
		int[] colIndexes = new int[outlook_name_columns.length];
		for (int j = 0; j < colIndexes.length; j++) {
			colIndexes[j] = -1;
		}

		String cmn = null;
		for (int i = 0; i < columns.length; i++) {
			cmn = columns[i].trim();
			for (int j = 0; j < outlook_name_columns.length; j++) {
				if (cmn.equals(outlook_name_columns[j])) {
					colIndexes[j] = i;
					break;
				}
			}
		}
		List<Contact> list = new ArrayList<Contact>();
		Contact c = null;
		String[] values = null;
		String name = "";
		String email = null;
		
		for (int i = 1; i < csvArray.length; i++) {
			values = csvArray[i];
			if (values.length < columns.length) {
				return null;
			}
			if (colIndexes[0] == -1) {
				// no email
				return null;
			}
			email = values[colIndexes[0]];
			if (!checkEmail(email)) {
				// /TODO add throw email validation exception
				continue;
			}
			// get name
			name = getContactName(values, colIndexes);
			c = new Contact(name, email);
			list.add(c);
			values = null;
		}
		return list;
	}
	
	private List<Contact> importContactsFromThunderbirdFile() {
		String[][] csvArray = readArrayFromCsvFile(this.filepath);
		if (csvArray == null || csvArray.length <= 1) {
			return null;
		}
		String[] columns = csvArray[0];
		
		String cmn = null;
		
		List<Contact> list = new ArrayList<Contact>();
		Contact c = null;
		String[] values = null;
		String name = "";
		String email = null;
		
		for (int i = 1; i < csvArray.length; i++) {
			values = csvArray[i];
			if (values.length < columns.length) {
				return null;
			}
			email = values[thunderbird_email_index];
			if (!checkEmail(email)) {
				// /TODO add throw email validation exception
				continue;
			}
			// get name
			name = getContactName(values, new int[] {0, 1, 2, 3});
			c = new Contact(name, email);
			list.add(c);
			values = null;
		}
		return list;
	}
	
	private String getContactName(String[] values, int[] colIndexes) {
		String name = null;
		int[][] name_sorts = null;
		if (this.type == (byte) 1) {
			name_sorts = outlook_name_sorts;
		} else if (this.type == (byte) 2) {
			name_sorts = thunderbird_name_sorts;
		}
		int[] childCols = null;
		for (int i = 0; i < name_sorts.length; i++) {
			childCols = name_sorts[i];
			name = "";
			for (int j = 0; j < childCols.length; j++) {
				if (colIndexes[childCols[j]] > -1) {
					name += " " + values[colIndexes[childCols[j]]];
				}
			}
			if (!"".equals(name.trim())) {
				break;
			}
		}
		// if there is no any name, we use the "username" of email address.
		if (name == null || "".equals(name.trim())) {
			name = values[colIndexes[0]].substring(0, values[colIndexes[0]]
					.indexOf("@"));
		}

		return name.trim();
	}

	public List<Contact> importContactsFromFile() {
		if (this.type == (byte) 1) {
			return importContactsFromOutlookFile();
		} else if (this.type == (byte) 2) {
			return importContactsFromThunderbirdFile();
		}
		return null;
	}

	public static boolean checkEmail(String email) {
		String regex = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+"
				+ "(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\."
				+ "[A-Za-z0-9]{2,}))$)\\b";
		if (email == null || "".equals(email.trim())
				|| !email.trim().matches(regex)) {
			return false;
		}
		return true;
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
		}
		return arr.toArray(new String[] {});
	}

	public static void main(String[] args) {
		String filepath = "D:/development_documents/BA/BA-192/test/OfferMe vs SNS.vsd";
		ImportContactsFromFileUtils util = new ImportContactsFromFileUtils(
				filepath, (byte) 2);
		List<Contact> list = util.importContactsFromFile();
		for (Contact c : list) {
			System.out.println(c.getName() + ":\t" + c.getEmailAddress());
		}
	}
}
