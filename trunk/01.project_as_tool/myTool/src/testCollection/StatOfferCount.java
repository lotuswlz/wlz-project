/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-6-8      Cathy Wu        Create
 */

package testCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatOfferCount {

	public static Map<Long, CatOfferCountBean> map = new HashMap<Long, CatOfferCountBean>();
	
	public static List<CatOfferCountBean> readFromFile(String pathName) {
		List<CatOfferCountBean> list = new ArrayList<CatOfferCountBean>();
		try {
			File file = new File(pathName);
			FileReader fr = null;
			BufferedReader br = null;
			
			String temp = null;
			int count = 0;
			CatOfferCountBean bean = null;
			
				if(!file.isFile()){
					return list;
				}
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				
				do{
					temp = br.readLine();
					if (count++ == 0) {
						continue;
					}
					bean = getBeanFromStr(temp);
					if (bean != null) {
						list.add(bean);
						map.put(bean.getCatSearchId(), bean);
					}
				} while(temp != null && temp.trim().length() > 0);
				
				br.close();
				fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<CatOfferCountBean> sortList(List<CatOfferCountBean> list) {
		List<CatOfferCountBean> l = new ArrayList<CatOfferCountBean>();
		CatOfferCountBean bean = null;
		for (CatOfferCountBean b : list) {
			if (b.getCatSearchId() > 99) {
				String sn = String.valueOf(b.getCatSearchId());
				bean = map.get(Long.parseLong(sn.substring(0, sn.length() - 2)));
				if (bean != null) {
					List<CatOfferCountBean> t = bean.getChildList();
					t.add(b);
					bean.setChildList(t);
					map.put(bean.getCatSearchId(), bean);
				}
			}
		}
		CatOfferCountBean b2 = null;
		for (int i = 0; i < list.size(); i++) {
			b2 = list.get(i);
			if (b2.getEditable() == 0) {
				b2 = map.get(b2.getCatSearchId());
			}
			l.add(b2);
		}
		return l;
	}
	
	private static CatOfferCountBean getBeanFromStr(String temp) {
		String regex = "^([\\d]+),([\"]?)([^\"]*)([\"]?),([\\d]+),([\\d]+),([\\d]+)$";
		if (temp == null || temp.trim().equals("") || !temp.matches(regex)) {
			return null;
		}
		temp = temp.replaceAll(regex, "$1;$2$3$4;$5;$6;$7");
		
		String[] arr = temp.split(";");
		CatOfferCountBean bean = new CatOfferCountBean();
		bean.setCatSearchId(Long.parseLong(arr[0]));
		bean.setCatName(arr[1]);
		bean.setEditable(Integer.parseInt(arr[2]));
		bean.setOfferCount(Integer.parseInt(arr[3]));
		bean.setLiveOfferCount(Integer.parseInt(arr[4]));
		return bean;
	}
	
	public static void writeFile(String filepath, List<CatOfferCountBean> list) {
		try {
			File file = new File(filepath);
			OutputStream os = new FileOutputStream(file);
			StringBuffer sbf = new StringBuffer();
			for (CatOfferCountBean b : list) {
				sbf.append(b.getCatSearchId() + ",");
				sbf.append(b.getCatName() + ",");
				sbf.append(b.getOfferCount() + ",");
				sbf.append(b.getLiveOfferCount() + "\n");
			}
			os.write(sbf.toString().getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String filepath = "D:/development_documents/temp_data/OFFER_COUNT.csv";
		List<CatOfferCountBean> list = readFromFile(filepath);
		List<CatOfferCountBean> l = sortList(list);
		writeFile("D:/development_documents/temp_data/OFFER_COUNT2.csv", l);
	}
}
