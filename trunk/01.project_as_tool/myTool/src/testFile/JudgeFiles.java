package testFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class JudgeFiles {
	
	

	public static void test(String filePath) throws IOException{
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		if(fr == null || br == null){return;}
		
		List<String> listBuyer = getBuyerPowerList();
		List<String> listSeller= getSellerPowerList();
		List<String> listVistor= getNoPowerList();
		
		renameList(listBuyer, listSeller, listVistor);
		System.out.println(listBuyer.size());
		System.out.println(listSeller.size());
		System.out.println(listVistor.size());
		
		List<String> notInList = new ArrayList<String>();
		List<String> extraList = new ArrayList<String>();
		
		String temp = null;
		String[] arr = null;
		String tempStr = null;
		int count = 0;
		while((temp = br.readLine()) != null){
			count++;
			if(temp.trim().length() == 0){
				continue;
			}
			if(temp.trim().startsWith("#")){
				continue;
			}
			tempStr = temp.substring(0, temp.indexOf("="));
			int sts = Integer.parseInt(temp.split("=")[1].trim());
			arr = tempStr.split("\\.");
			tempStr = arr[arr.length - 1];
			//1 buyer
			if(listBuyer.contains(tempStr)){
				if(sts != 1){
					System.out.println("error in " + tempStr + "(not " + sts + " should be 1)");
				}
//				System.out.println(tempStr + " has been already in properties");
				listBuyer.remove(tempStr);
				continue;
			}else if(listSeller.contains(tempStr)){
				if(sts != 2){
					System.out.println("error in " + tempStr + "(not " + sts + " should be 2)");
				}
//				System.out.println(tempStr + " has been already in properties");
				listSeller.remove(tempStr);
				continue;
			}else if(listVistor.contains(tempStr)){
				if(sts != 0){
					System.out.println("error in " + tempStr + "(not " + sts + " should be 0)");
				}
				extraList.add(tempStr);
				listVistor.remove(tempStr);
				continue;
			}else{
				notInList.add(tempStr);
				System.out.println("line" + count + " " + tempStr);
				
			}
		}
		System.out.println("needn't been added:");
		for(int i = 0; i < extraList.size(); i++){
			System.out.println("\t" + extraList.get(i));
		}
		
		System.out.println("\nnot in list");
		for(int i = 0; i < notInList.size(); i++){
			System.out.println("\t" + notInList.get(i));
		}
		
		System.out.println("not in \n");
		System.out.println("\n visitor: " + listVistor.size());
		for(int i = 0; i < listVistor.size(); i++){
			System.out.println("\t" + listVistor.get(i));
		}
		System.out.println("\n buyer: " + listBuyer.size());
		for(int i = 0; i < listBuyer.size(); i++){
			System.out.println("\t" + listBuyer.get(i));
		}
		System.out.println("\n seller: " + listSeller.size());
		for(int i = 0; i < listSeller.size(); i++){
			System.out.println("\t" + listSeller.get(i));
		}
		br.close();
		fr.close();
		System.gc();
	}
	
	public static List<String> getBuyerPowerList(){
		List<String> list = new ArrayList<String>();
		list.add("CreateItemSelectCategoryAction");
		list.add("CreateItemSearchCategoryByKeyAction");
		list.add("CreateItemBrowseCategoryAjaxSuggest");
		list.add("UserLogoutAction");
		list.add("UserAddressAction");
		list.add("MarkPrimaryAddressAction");
		list.add("EditUserPreferenceInputAction");
		list.add("EditUserPreferenceAction");
		list.add("UserDisputeSummaryAction");
		list.add("CreateItemInputAction");
		list.add("CreateItemUploadPictureAction");
		list.add("CreateItemCheckDuplicationAction");
		list.add("CreateItemSaveAction");
		list.add("UserDisputeDetailAction");
		list.add("UserDisputeResponseAction");
		list.add("BuyerDisputeListAction");
		list.add("BuyerLodgeDisputeSelectAction");
		list.add("BuyerLodgeDisputeInputAction");
		list.add("BuyerLodgeDisputeSaveAction");
		list.add("ShowCreditActivityListAction");
		list.add("CreateRequestInputAction");
		list.add("CreateRequestConfirmAction");
		list.add("CreateRequestSaveAction");
		list.add("CreateRequestReinputAction");
		list.add("CurrentRequestListAction");
		list.add("RequestDetailAction");
		list.add("ReviseRequestInputAction");
		list.add("ReviseRequestConfirmAction");
		list.add("ReviseRequestSaveAction");
		list.add("ReviseRequestReinputAction");
		list.add("EndRequestConfirmAction");
		list.add("EndRequestSaveAction");
		list.add("RequestSimilarAction");
		list.add("ExtendRequestInputAction");
		list.add("ExtendRequestSaveAction");
		list.add("SetupSellerAccountInputAction");
		list.add("SetupSellerAccountCreditCardInputActn");
		list.add("SetupSellerAccountSaveAction");
		list.add("CurrentOfferListAction");
		list.add("OfferDetailAction");
		list.add("CreateNegoSaveQuestionAction");
		list.add("RejectOfferAction");
		list.add("BuyerNegotiationListAction");
		list.add("BuyerNegotiationDetailAction");
		list.add("BuyerAskingQuestionAction");
		list.add("BuyerChangePostalAddressInputAction");
		list.add("BuyerChangePostalAddressSaveAction");
		list.add("CreateTransactionConfirmAction");
		list.add("CreateTransactionSaveAction");
		list.add("EditCreditAmountAction");
		list.add("BuyerTransactionListAction");
		list.add("BuyerTransactionDetailAction");
		list.add("TransMarkPaymentSentAction");
		list.add("ResetPasswordStep1Action");
		list.add("ResetPasswordStep2Action");
		list.add("UserFeedbackAction");
		list.add("MyOffermeSummaryAction");
		list.add("UserAccountSummaryAction");
		list.add("MyMessageListAction");
		list.add("MyMessageAlertDetailAction");
		list.add("DeleteMyMessageAction");
		list.add("UserFavouriteItemsAction");
		list.add("AddFavouriteItemAction");
		list.add("UserAccountDetailAction");
		list.add("EditUserAccountInputAction");
		list.add("EditUserAccountAction");
		list.add("MobileValidateInputAction");
		list.add("MobileValidateAction");
		list.add("SendMobileValidateCodeAction");
		list.add("EditUserPasswordAction");
		list.add("EditUserEmailAction");
		list.add("EditUserSecretQuestionAction");
		list.add("AddUserAddressInputAction");
		list.add("AddUserAddressAction");
		list.add("EditUserAddressInputAction");
		list.add("EditUserAddressAction");
		list.add("DeleteUserAddressConfirmAction");
		list.add("DeleteUserAddressAction");
		list.add("PostcodeCheckAction");
		list.add("InviteFriendsInputAction");
		list.add("InviteFriendsAction");
		list.add("ImportFriendsByEmailAction");
		list.add("ImportFriendsByFileAction");
		list.add("SelectFriendsAction");
		list.add("ImportFriendsFromNetworkAction");
		list.add("InviteFriendsInNetWorkAction");
		list.add("CheckLinkAction");
		list.add("BuyerLeaveFeedbackInputAction");
		list.add("BuyerLeaveFeedbackConfirmAction");
		list.add("BuyerLeaveFeedbackSaveAction");
		list.add("BuyerLeaveFeedbackReinputAction");
		return list;
	}
	
	public static List<String> getSellerPowerList(){
		List<String> list = new ArrayList<String>();
		list.add("CreateOfferInputAction");
		list.add("CreateOfferEstimateAction");
		list.add("CreateOfferDescriptionAction");
		list.add("CreateOfferConfirmAction");
		list.add("CreateOfferSaveAction");
		list.add("ConfirmPostageInputAction");
		list.add("ConfirmPostageSaveAction");
		list.add("CurrentOfferListAction");
		list.add("OfferDetailAction");
		list.add("ReviseOfferInputAction");
		list.add("ReviseOfferConfirmAction");
		list.add("ReviseOfferDescriptionAction");
		list.add("ReviseOfferSaveAction");
		list.add("EndOfferConfirmAction");
		list.add("EndOfferSaveAction");
		list.add("ExtendOfferInputAction");
		list.add("ExtendOfferSaveAction");
		list.add("SellerNegotiationListAction");
		list.add("OfferSimilarAction");
		list.add("SellerNegotiationDetailAction");
		list.add("SellerNegotiationAskAction");
		list.add("ReviseNegotiationInputAction");
		list.add("ReviseNegotiationConfirmAction");
		list.add("ReviseNegotiationSaveAction");
		list.add("SellerNegoConfirmPostageAction");
		list.add("SellerNegoConfirmPostageSaveAction");
		list.add("SellerNegoDeleteConfirmAction");
		list.add("SellerNegoDeleteSaveAction");
		list.add("SellerTransactionListAction");
		list.add("SellerTransactionDetailAction");
		list.add("SellerMarkPaymentReceivedAction");
		list.add("SellerMarkPostedAction");
		list.add("SellerDisputeListAction");
		list.add("SellerLodgeDisputeSelectAction");
		list.add("SellerLodgeDisputeInputAction");
		list.add("SellerLodgeDisputeSaveAction");
		list.add("EditSellerAccountInputAction");
		list.add("EditSellerAccountSaveAction");
		list.add("SellerBillingSummaryAction");
		list.add("SellerInvoiceDetailAction");
		list.add("SetupAutoPaymentSelectAction");
		list.add("SetupAutoPaymentSaveAction");
		list.add("SellerReceiptDetailAction");
		list.add("SellerFeeListAction");
		list.add("FinancialInformationAction");
		list.add("AddCreditCardInputAction");
		list.add("AddCreditCardSaveAction");
		list.add("EditCreditCardInputAction");
		list.add("EditCreditCardSaveAction");
		list.add("RemoveCreditCardConfirmAction");
		list.add("RemoveCreditCardSaveAction");
		list.add("SellerSinglePaymentInputAction");
		list.add("SellerSinglePaymentSaveAction");
		list.add("SellerLeaveFeedbackGroupAction");
		list.add("SellerLeaveFeedbackConfirmAction");
		list.add("SellerLeaveFeedbackSaveAction");
		list.add("SellerLeaveFeedbackReinputAction");
		return list;
	}
	
	public static List<String> getNoPowerList(){
		List<String> list = new ArrayList<String>();
		list.add("HomepageStartAction");
		list.add("SearchItemAction");
		list.add("MatchingCategoryAction");
		list.add("BrowseCategoryAction");
		list.add("AdvancedSearchInputAction");
		list.add("UserLoginInputAction");
		list.add("UserLoginAction");
		list.add("RegisterInputAction");
		list.add("UserRegisterAction");
		list.add("UserLoginNameCheckAction");
		list.add("ItemDetailAction");
		list.add("ItemDetailTransactionHistoryAction");
		list.add("SearchSuggestionAction");
		list.add("CreateOfferAutoSuggestValueAction");
		list.add("CreateOfferAutoSuggestLabelAction");
		list.add("CreateOfferUploadPictureAction");
		list.add("ConfirmPostageAjaxAction");
		list.add("BuyerNegotiationRejectConfirmAction");
		list.add("BuyerNegotiationRejectSaveAction");
		list.add("ShowHotItemsAction");
		list.add("ShowFastMoversAction");
		list.add("ShowMostWantedItemsAction");
		list.add("SystemAnnouncementListAction");
		list.add("SystemAnnouncementDetailAction");
		list.add("HelpListAction");
		list.add("HelpDetailAction");
		list.add("ContactUsAction");
		list.add("ContactUsSendMailAction");
		list.add("RetrieveUserNameInputAction");
		list.add("RetrieveUserNameAction");
		list.add("RetrievePasswordStep1InputAction");
		list.add("RetrievePasswordStep1Action");
		list.add("RetrievePasswordStep2Action");
		list.add("ResetPasswordStep1InputAction");
		list.add("UserFavouriteItemsDeleteAction");
		list.add("EditUserPasswordInputAction");
		list.add("EditUserEmailInputAction");
		list.add("EditUserSecretQuestionInputAction");
		list.add("InviteFriendsFromMailAction");
		list.add("InviteFriendsFromNetworkAction");
		return list;
	}
	
	public static void renameList(List<String> listBuyer,List<String> listSeller,List<String> listVistor) throws IOException{		
		File file1 = new File("D:\\projects\\offerme\\test\\newaction.txt");
		File file2 = new File("D:\\projects\\offerme\\test\\oldaction.txt");
		FileReader fr1 = new FileReader(file1);
		BufferedReader br1 = new BufferedReader(fr1);
		FileReader fr2 = new FileReader(file2);
		BufferedReader br2 = new BufferedReader(fr2);
		
		String temp1 = null;
		String temp2 = null;
		while((temp1 = br1.readLine()) != null){
			temp2 = br2.readLine();
			if(temp1.trim().length() == 0){
				continue;
			}
			if(listBuyer.contains(temp2)){
				listBuyer.remove(temp2);
				listBuyer.add(temp1);
			}else if(listSeller.contains(temp2)){
				listSeller.remove(temp2);
				listSeller.add(temp1);
			}else if(listVistor.contains(temp2)){
				listVistor.remove(temp2);
				listVistor.add(temp1);
			}
		}
		
		br1.close();
		br2.close();
		fr1.close();
		fr2.close();
	}
	
	public static void expFile() throws IOException{
		File file1 = new File("D:\\projects\\offerme\\test\\newaction.txt");
		File file2 = new File("D:\\projects\\offerme\\test\\oldaction.txt");
		FileReader fr1 = new FileReader(file1);
		BufferedReader br1 = new BufferedReader(fr1);
		FileReader fr2 = new FileReader(file2);
		BufferedReader br2 = new BufferedReader(fr2);
		
		File file3 = new File("D:\\projects\\offerme\\test\\allaction.txt");
		OutputStream os = new FileOutputStream(file3);
		
		String temp1 = null;
		String temp2 = null;
		while((temp1 = br1.readLine()) != null){
			temp2 = br2.readLine();
			os.write((temp2 + "               " + temp1 + "\r\n").getBytes());
		}
		
		os.close();
		br1.close();
		br2.close();
		fr1.close();
		fr2.close();
	}
	
	public static void main(String[] args) {
		try {
			test("D:\\projects\\offerme\\30_Coding\\trunk\\src\\resources\\accesspower.properties");
//			expFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
