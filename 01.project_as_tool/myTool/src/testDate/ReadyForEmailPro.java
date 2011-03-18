/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-1-21      Cathy Wu        Create
 */

package testDate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadyForEmailPro {
	
	private static final String BEFORE_STR = "select  REGEXP_REPLACE(lower(email),\'.*@',\'\') domain_name, email,create_time,last_login_time, s.receive_mail_setting from tb_user u, tb_user_setting s where u.user_id=s.user_id and email in(";
	private static final String AFTER_STR = ") AND s.receive_mail_setting>0 ";
	
	private static final String V_BEFORE_STR = "select  REGEXP_REPLACE(lower(mail),\'.*@\',\'\') domain_name, mail, create_time from tb_visitor where mail in(";
	private static final String V_AFTER_STR = ") and receive_mail_setting<>0 and subscribe_sts=1 ";	

	public static List<String> transferEmailStr(String emailStr) {
		final int maxLength = 2000;
		
		List<String> list = new ArrayList<String>();
		String temp = null;
		int idx = 0;
		int maxSize = 0;
		do {
			maxSize = maxLength > emailStr.length() ? emailStr.length() : maxLength;
			temp = emailStr.substring(idx, maxSize);
			if (temp.charAt(temp.length() - 1) == ',') {
				temp = temp.substring(0, temp.length() - 1);
			} else {
				idx = temp.lastIndexOf(',');
				if (idx == -1) {
					idx = temp.length();
				}
				temp = temp.substring(0, idx);
			}
			list.add(temp);
			idx = temp.length() + 1;
			if (idx > 0 && emailStr.length() > idx) {
				emailStr = emailStr.substring(idx);
			} else {
				emailStr = "";
			}
			idx = 0;
			
		} while (emailStr.length() > 0);
		
		return list;
	}
	
	public static String readFileToEmailStr(String path) throws IOException {
		File file = new File(path);
		
		FileReader fr = null;
		BufferedReader br = null;
				
		List<String> list = new ArrayList<String>();
		
		String temp = null;
		int count = 0;
		
			if(!file.isFile()){
				return null;
			}
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			
			do{
				temp = br.readLine();
				if (temp == null || temp.trim().length() == 0) {
					continue;
				}
				if (list.contains(temp.trim().toLowerCase())) {
				    System.out.println(temp + " already exist...");
				    continue;
				}
	            count++;
				list.add(temp.trim().toLowerCase());
				System.gc();
			} while(temp != null && temp.trim().length() > 0);
			
			br.close();
			fr.close();
		System.out.println("\ntotal " + count + " emails");
		
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append("\'" + str + "\',");
		}
		if (sb.length() >= 1) {
			int lastIndex = sb.lastIndexOf(",");
			return sb.substring(0, lastIndex);
		}
		return null;
	}
	
	public static void writeToFile(String str) throws IOException {
		File file = new File("D:/projects/Maintenance/email_jmrp/epsilon_to_del/to_delete.sql");
		OutputStream os = new FileOutputStream(file, true);
		os.write(str.getBytes("UTF-8"));
		os.close();
	}
	
	public static void main(String[] args) {
		try {
			String path = "D:/projects/Maintenance/email_jmrp/epsilon_to_del/to_delete.txt";
			String str = readFileToEmailStr(path);
			List<String> list = transferEmailStr(str);
			StringBuffer sb = new StringBuffer();
			for (String subStr : list) {
				sb.append(BEFORE_STR + " " + subStr + " " + AFTER_STR + "\n");
				sb.append("Union\n");
			}
			
			sb.delete(sb.length() - 6, sb.length());
			
			sb.insert(0, "select * from (\n");
			sb.append(") order by last_login_time");
			
			sb.append("\n\n -------------- \n\n");
			
			writeToFile(sb.toString());
			
			sb = new StringBuffer();
			
			System.gc();
			
			for (String subStr : list) {
				sb.append(V_BEFORE_STR + " " + subStr + " " + V_AFTER_STR + "\n");
				sb.append("Union\n");
			}
			sb.delete(sb.length() - 6, sb.length());
			
			sb.insert(0, "select * from (\n");
			sb.append(") order by create_time");
			sb.append("\n\n -------------- \n\n");
			
			writeToFile(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
