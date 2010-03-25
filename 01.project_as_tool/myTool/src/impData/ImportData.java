package impData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportData {

	private String fullFileName;
	private static final String preSql_countryList = "insert into tb_country_list t (country_alias, country_name, country_dialing_code, area_credit) values(";
	private static final String preSql_postcodeList = "insert into tb_australia_postcode t (postcode, suburb, state, parcel_zone, category) values(";
	
	public ImportData(String fileName){
		this.fullFileName = fileName;
	}
	
	public void saveListFromFile(List<String> sqls){
		DataBaseBasic db = new DataBaseBasic();
		try {
			db.groupExecute(sqls);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] changeStr(String str, int num){
		String[] arr = new String[num];
		int n = 0;
		for(int i = num - 1; i > 0; i--){
			n = str.lastIndexOf(',');
			arr[i] = str.substring(n + 1);
			str = str.substring(0, n);
		}
		arr[0] = str;
		
		return arr;
	}
	
	public static void main(String[] args) {
//		String fileName = "D:\\projects\\offerme\\10_PrimaryDesign\\90_OutsideRef\\Country List\\countries.csv";
		String fileName = "D:\\projects\\offerme\\10_PrimaryDesign\\90_OutsideRef\\AU Postcode\\pc-full_20080131.csv";
		ImportData imp = new ImportData(fileName);
		try{
			imp.importDataFromFile(fileName, preSql_postcodeList, 1);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void importDataFromFile(String fileName, String preSql, int afterNum)
			throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(fileName);   //创建一个FileReader对象   从磁盘读
		BufferedReader br = new BufferedReader(fr);    //创建一个BufferedReader对象
     
		String line = null;
		String[] dataArr;
		List<String> sqls = new ArrayList<String>();
		int count = 0;
		while(br.ready() && afterNum-- > 0){
			line = br.readLine();
		}
		while(br.ready()){
			line = br.readLine();
			dataArr = changeStr(line, 10);//tb_country_list:3; tb_australia_postcode: 10
			// '
			String[] temp = dataArr[1].split("'");
			if(temp.length > 1){
				String str = temp[0];
				for(int i = 1; i < temp.length; i++){
					str += "''" + temp[i];
				}
				dataArr[1] = str;
			}
//			// '
//		    temp = dataArr[0].split("\"");
//			if(temp.length > 1){
//				String str = temp[0];
//				for(int i = 1; i < temp.length; i++){
//					str += temp[i];
//				}
//				dataArr[0] = str;
//			}
//			System.out.print(++count + ":");
			//String sql = preSql + "'" + dataArr[1] + "','" + dataArr[0] + "','" + dataArr[2] + "'," + "0)";
			int c = 0;
			if(dataArr[9].startsWith("Delivery Area")){
				c = 0;
			}else if(dataArr[9].startsWith("Post Office Boxes")){
				c = 1;
			}else if(dataArr[9].startsWith("LVR")){
				c = 2;
			}
			String sql = preSql + dataArr[0].trim() + ",'" + dataArr[1].trim() + "','" + dataArr[2].trim() + "','" + dataArr[6].trim() + "'," + c + ")";
			System.out.println(sql);
			sqls.add(sql);
			count++;
			if(count >= 1000){
				saveListFromFile(sqls);
				count = 0;
				sqls.clear();
				System.out.println(sqls.size());
			}
		}
		if(count > 0){
			saveListFromFile(sqls);
		}
	}
}
