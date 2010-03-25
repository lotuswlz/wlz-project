package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackupFiles {
	
	private static final String mapFile = "D:/development_documents/bug 5 documents/action.txt";
	private static final String actionFile = "D:/development_documents/bug 5 documents/action description.txt";

	private static Map<String, String> powerMap = new HashMap<String, String>();
	
	private static void settingMap() throws IOException{
		File mapf = new File(mapFile);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		String temp = null;
		String[] tempArr = null;
		int[] count = {0,0};
		
		while((temp = br.readLine()) != null){
			count[0] ++;
			if(temp.trim().length() == 0){
				continue;
			}
			if(temp.trim().startsWith("#")){
				continue;
			}
			
			tempArr = temp.split("\t");
			powerMap.put(tempArr[0].trim(), tempArr[1].trim());
			count[1]++;
		}
		br.close();
		fr.close();
		
		System.out.println("line count: " + count[0] + "; action count: " + count[1]);
	}
	
	public static String changeFile() throws IOException{
		File mapf = new File(actionFile);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		StringBuffer sbf = new StringBuffer("");
		
		String temp = null;
		int[] count = {0,0};
		
		while((temp = br.readLine()) != null){
			count[0] ++;
			if(temp.trim().length() == 0){
				continue;
			}
			if(temp.trim().startsWith("#")){
				continue;
			}
			sbf.append(temp);
			sbf.append("\n");
		}
		String str = sbf.toString();
		str = str.replace("AdvancedSearchInputAction","AdvancedSearchAction");
		str = str.replace("UserLoginAction","UserLoginSubmitAction");
		str = str.replace("UserLoginInputAction","UserLoginAction");
		str = str.replace("UserRegisterAction","RegisterSubmitAction");
		str = str.replace("RegisterInputAction","RegisterAction");
		str = str.replace("CreateItemInputAction","CreateItemAction");
		str = str.replace("CreateOfferInputAction","CreateOfferAction");
		str = str.replace("ConfirmPostageInputAction","ConfirmPostageAction");
		str = str.replace("ReviseOfferInputAction","ReviseOfferAction");
		str = str.replace("ExtendOfferInputAction","ExtendOfferAction");
		str = str.replace("ReviseNegotiationInputAction","ReviseNegotiationAction");
		str = str.replace("CreateRequestInputAction","CreateRequestAction");
		str = str.replace("ReviseRequestInputAction","ReviseRequestAction");
		str = str.replace("ExtendRequestInputAction","ExtendRequestAction");
		str = str.replace("SetupSellerAccountInputAction","SetupSellerAccountAction");
		str = str.replace("SetupSellerAccountCreditCardInputAction","SetupSellerAccountCreditCardAction");
		str = str.replace("EditSellerAccountInputAction","EditSellerAccountAction");
		str = str.replace("AddCreditCardInputAction","AddCreditCardAction");
		str = str.replace("EditCreditCardInputAction","EditCreditCardAction");
		str = str.replace("SellerSinglePaymentInputAction","SellerSinglePaymentAction");
		str = str.replace("BuyerChangePostalAddressInputAction","BuyerChangePostalAddressAction");
		str = str.replace("RetrieveUserNameAction","RetreiveUserNameSubmitAction");
		str = str.replace("RetrieveUserNameInputAction","RetrieveUserNameAction");
		str = str.replace("RetrievePasswordStep2Action","RetrievePasswordSubmitAction");
		str = str.replace("RetrievePasswordStep1Action","RetrievePasswordStep2Action");
		str = str.replace("RetrievePasswordStep1InputAction","RetrievePasswordStep1Action");
		str = str.replace("ResetPasswordStep2Action","ResetPasswordSubmitAction");
		str = str.replace("ResetPasswordStep1Action","ResetPasswordStep2Action");
		str = str.replace("ResetPasswordStep1InputAction","ResetPasswordStep1Action");
		str = str.replace("EditUserAccountAction","EditUserAccountSaveAction");
		str = str.replace("EditUserAccountInputAction","EditUserAccountAction");
		str = str.replace("MobileValidateAction","MobileValidateSaveAction");
		str = str.replace("MobileValidateInputAction","MobileValidateAction");
		str = str.replace("EditUserPasswordAction","EditUserPasswordSaveAction");
		str = str.replace("EditUserPasswordInputAction","EditUserPasswordAction");
		str = str.replace("EditUserEmailAction","EditUserEmailSaveAction");
		str = str.replace("EditUserEmailInputAction","EditUserEmailAction");
		str = str.replace("AddUserAddressAction","AddUserAddressSaveAction");
		str = str.replace("AddUserAddressInputAction","AddUserAddressAction");
		str = str.replace("EditUserAddressAction","EditUserAddressSaveAction");
		str = str.replace("EditUserAddressInputAction","EditUserAddressAction");
		str = str.replace("DeleteUserAddressAction","DeleteUserAddressSubmitAction");
		str = str.replace("DeleteUserAddressConfirmAction","DeleteUserAddressAction");
		str = str.replace("InviteFriendsAction","InviteFriendsSubmitAction");
		str = str.replace("InviteFriendsInputAction","InviteFriendsAction");
		str = str.replace("InviteFriendsInNetWorkAction","InviteFriendsFromNetWorkSubmitAction");
		str = str.replace("BuyerLeaveFeedbackInputAction","BuyerLeaveFeedbackAction");
		br.close();
		fr.close();
		
		OutputStream os = new FileOutputStream(mapf);
		os.write(str.getBytes());
		os.close();
		System.out.println("line count: " + count[0] + "; action count: " + count[1]);
		return str;
	}
	
	public static void writeFile(String fileName) throws IOException{
		if(powerMap.entrySet().size() == 0){
			settingMap();
		}
		File mapf = new File(actionFile);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		StringBuffer sbf = new StringBuffer("");
		
		String temp = null;
		String[] tempArr = null;
		String tempNum = null;
		int[] count = {0,0};
		
		while((temp = br.readLine()) != null){
			count[0] ++;
			if(temp.trim().length() == 0){
				continue;
			}
			if(temp.trim().startsWith("#")){
				continue;
			}
			
			tempArr = temp.split("\t");
			if(tempArr.length < 2){
				continue;
			}
			
			tempNum = powerMap.get(tempArr[1].trim());
			if(tempNum != null){
				sbf.append(tempArr[0].trim() + "\t" + tempArr[1].trim() + "\t" + (tempArr.length >= 3 ? tempArr[2]:"") + "\t" + tempNum + "\n");
				count[1]++;
			}else{
				System.out.println(tempArr[0] + "  " + tempArr[1] + "  " + (tempArr.length >= 3 ? tempArr[2]:""));
			}
		}
		br.close();
		fr.close();
		
		File f = new File(fileName);
		OutputStream os = new FileOutputStream(f);
		os.write(sbf.toString().getBytes());
		os.close();
		System.out.println("line count: " + count[0] + "; action count: " + count[1]);
	}
	
	public static HashMap<String, String> setNemMap() throws IOException{
		String fileName = "D:/development_documents/bug 5 documents/new action.txt";
		HashMap<String, String> map = new HashMap<String, String>();
		File mapf = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		String temp = null;
		String[] tempArr = null;
		
		while((temp = br.readLine()) != null){
			if(temp.trim().length() == 0){
				continue;
			}
			tempArr = temp.split("=");
			map.put(tempArr[0], tempArr[1]);
		}		
		br.close();
		fr.close();
		return map;
	}
	
	public static void replaceFiles() throws IOException{
		String oldFileName = "D:/development_documents/bug 5 documents/accesspower.properties";
		Map<String, String> map = setNemMap();
		File mapf = new File(oldFileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		StringBuffer sbf = new StringBuffer("");
		
		String temp = null;
		String[] tempArr = null;
		String str = null;
		
		while((temp = br.readLine()) != null){
			if(temp.trim().length() == 0 || temp.trim().startsWith("#")){
				sbf.append(temp);
			}else{
				tempArr = temp.split("=");
				str = tempArr[0].replaceAll(".*\\.([^\\.]+)", "$1");
				if(map.get(str) != null){
					sbf.append(tempArr[0]);
					sbf.append("=");
					sbf.append(map.get(str));
				}else{
					sbf.append(temp);
					System.out.println(temp);
				}
			}
			sbf.append("\n");
		}
		br.close();
		fr.close();
		
		File f = new File("D:/development_documents/bug 5 documents/new properties.properties");
		OutputStream os = new FileOutputStream(f);
		os.write(sbf.toString().getBytes());
		os.close();
		System.out.println(f.getCanonicalFile());
	}
	
	public static List<String> setListOldProperties() throws IOException{
		String fileName = "D:/projects/offerme/30_Coding/my_trunk/src/resources/accesspower.properties";
		File mapf = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		List<String> list = new ArrayList<String>();
		
		String temp = null;
		
		while((temp = br.readLine()) != null){
			if("".equals(temp.trim()) || temp.startsWith("#")){
				continue;
			}
			temp = temp.replaceAll(".*\\.([^\\.]+=.*)", "$1");
			//System.out.println(temp);
			list.add(temp);
		}
		br.close();
		fr.close();
		return list;
	}
	
	public static void changeNotMatch() throws IOException{
		String fileName = "D:/development_documents/bug 5 documents/new action list.txt";
		File mapf = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		List<String> list = setListOldProperties();
		
		String temp = null;
		
		System.out.println("ÓëProperties²»·ûµÄ£º");
		while((temp = br.readLine()) != null){
			if(!list.contains(temp)){
				System.out.println(temp);
			}
		}
		br.close();
		fr.close();
	}
	
	public static void changeRepeatActions() throws IOException{
		String fileName = "D:/projects/offerme/30_Coding/my_trunk/src/resources/accesspower.properties";
		File mapf = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(mapf);
		br = new BufferedReader(fr);
		
		List<String> list = new ArrayList<String>();
		
		String temp = null;
		String[] tempArr = null;
		int[] count = {0,0};
		
		Class cls = null;
		
		while((temp = br.readLine()) != null){
			count[0] ++;
			if("".equals(temp.trim()) || temp.startsWith("#")){
				continue;
			}
			tempArr = temp.split("=");
			//try {
				ClassLoader.getSystemResources(tempArr[0]);
			//} catch (ClassNotFoundException e) {
			//	System.out.println("Line " + count[0] + " not exist Class \"" + tempArr[0] + "\"");
			//	count[1]++;
			//}
			list.add(temp);
		}
		br.close();
		fr.close();
		System.out.println("There are " + count[1] + " classes not exist");
	}
	
	
	
	public static void main(String[] args) {
		try {
			//writeFile("D:/development_documents/bug 5 documents/new_action_list.txt");
			//replaceFiles();
			//changeFile();
			changeNotMatch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
