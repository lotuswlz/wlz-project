package testStr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestRegex {
    
    public static void testEmail(String email) {
        String regex = "^(?i).*@offerme.com.au$";
        if (email.matches(regex)) {
            System.out.println("match");
        } else {
            System.out.println("not match");
        }
    }
	
	public static void test(){
		String regex = "^[\\x00-\\xff]{0,80}$";
		String msg = "aa asd 234";
		if(msg.matches(regex)){
			System.out.println("match");
		}
	}
	
	public static void testSymbol(String str, String symbol) {
	    String[] arr = str.split(symbol);
	    System.out.println(arr.length);
	    for (String temp : arr) {
	        System.out.println(temp);
	    }
	}
	
	public static String cutPhoneNumber(String number) {
	    return number.replaceAll("[^0-9]+","").replaceAll("^0+([0-9]+)", "$1");
	}
	
	public List<TestClass> getReadyData(){
		List<TestClass> list = new ArrayList<TestClass>();
		list.add(new TestClass(1L, "computer"));
		list.add(new TestClass(2L, "notebook/computer"));
		list.add(new TestClass(3L, "computer associate"));
		list.add(new TestClass(4L, "basketball"));
		list.add(new TestClass(5L, "ball"));
		list.add(new TestClass(6L, "football"));
		list.add(new TestClass(7L, "book"));
		list.add(new TestClass(8L, "hp computer"));
		list.add(new TestClass(9L, "hp notebook"));
		list.add(new TestClass(10L, "hp printer"));		
		list.add(new TestClass(9L, "ibm notebook"));
		list.add(new TestClass(9L, "bb notebook"));
		list.add(new TestClass(9L, "ibm computer"));
		list.add(new TestClass(9L, "ab notebook"));
		list.add(new TestClass(9L, "ba notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		list.add(new TestClass(9L, "a notebook"));
		return list;
	}
	
	public List<TestClass> test(List<TestClass> list){
		String regex = "(\\bhp\\b)|(\bibm\\b)|(\\bcomputer\\b)";//"(computer+)|(hp+)|(ibm+)|(notebook+)";
		Pattern ptn = Pattern.compile(regex);
		Matcher mc = null;
		List<TestClass> resultList = new ArrayList<TestClass>();
		int count = 0;
		for(TestClass tc : list){
			count = 0;
			mc = ptn.matcher(tc.getCatName());
			while(mc.find()){
				count++;
			}
			tc.setPower(count);
			if(count > 0){
				resultList.add(tc);
			}
		}
		Collections.sort(resultList);
		if(resultList.size() > 10){
			return resultList.subList(0, 10);
		}
		return resultList;
	}
	
	private class TestClass implements Comparable<TestClass>{
		private String catName;
		private int power;
		private long sn;
		
		public String getCatName() {
			return catName;
		}
		public void setCatName(String catName) {
			this.catName = catName;
		}
		public int getPower() {
			return power;
		}
		public void setPower(int power) {
			this.power = power;
		}
		public long getSn() {
			return sn;
		}
		public void setSn(long sn) {
			this.sn = sn;
		}
		
		public TestClass(){
			
		}
		
		public TestClass(long sn, String name){
			this.sn = sn;
			this.catName = name;
		}
		
		public TestClass(long sn, String name, int power){
			this.sn = sn;
			this.catName = name;
			this.power = power;
		}
		public int compareTo(TestClass o) {
			if(o.getPower() > this.power){
				return 1;
			}else if(o.getPower() < this.power){
				return -1;
			}else{
				return 0;
			}
		}
	}
	
	public static void checkCharLength(String data, int minLength, int maxLength){
		String regex = "^[\\x00-\\xff]{" + minLength + "," + maxLength + "}$";
		if(!data.matches(regex)){
			System.out.println("not match");
		}
	}
	
	public static String formatMobileCode(String mobile){
		String[] temp = mobile.split("[\\+|\\-]+");
		String str = "";
		for(int i = 0; i < temp.length; i++){
			str += temp[i];
		}
		temp = str.split("^0+");
		str = "";
		for(int i = 0; i < temp.length; i++){
			str += temp[i];
		}
		return str;
	}
	
	public static void testMatch(){
		String regex1 = "^[A-Z|a-z][a-z|A-Z|0-9|\\-|\\.|_]{1,18}[A-Z|a-z|0-9]$";
		String userName = "asdf2";
		if(userName.matches(regex1)){
			System.out.println("match");
		}else{
			System.out.println("not match");
		}
	}
	
	public static void getChineseChar(){
		String regex = ".*[\\u4e00-\\u9fa5].*";
		String str = "222";
		if(str.matches(regex)){
			System.out.println("match");
		}else{
			System.out.println("not match");
		}
	}
	
	public static String[] getSpecificValue(String str){
		List<String> list = new ArrayList<String>();
		String regex = "\\([^\\(\\)]+\\)";
		Pattern ptn = Pattern.compile(regex);
		Matcher mc = ptn.matcher(str);
		String temp = null;
		while(mc.find()){
			temp = mc.group();
			temp = temp.replaceAll("[\\(|\\)]", "");
			list.add(temp);
		}
		return list.toArray(new String[]{});
	}
	
	public static void testMobile(String mobile){
		String regex = "^([0-9\\+\\-\\s\\(\\)\\[\\]\\{\\}]*[0-9][0-9\\+\\-\\s\\(\\)\\[\\]\\{\\}]*|)$";
		System.out.println(regex);
		if(mobile.matches(regex)){
			System.out.println("match");
		}else{
			System.out.println("not match");
		}
	}
	
	public static void testPassword(String pwd){
		String regex = "^[a-zA-Z0-9\\+\\._!@#\\$%\\^&\\s\\-]{6,20}$";
		if(pwd.matches(regex)){
			System.out.println("match");
		}else{
			System.out.println("not match");
		}
	}
	
	public static String changeSymbol(String title, String startTag, String endTag, String prefix, String endfix){
		String regex = changeRegex(startTag) + ".*" + changeRegex(endTag);
		Pattern ptn = Pattern.compile(regex);
		Matcher mc = ptn.matcher(title);
		String str = null;
		String temp= null;
		StringBuffer sb = new StringBuffer();
		int start = 0;
		int end = 0;
		while(mc.find()){
			start = mc.start();
			sb.append(title.substring(end, start));
			end = mc.end();
			temp = mc.group();
			str = temp.substring(startTag.length());
			str = str.substring(0, str.length() - endTag.length());
			sb.append(prefix + str + endfix);
		}
		sb.append(title.substring(end));
		return sb.toString();
	}
	
	private static String changeRegex(String tag){
		return tag.replaceAll("(\\[|\\]|\\+|\\-|\\(|\\)|\\*|\\\\|\\{|\\}|\\^|\\$|\\|)", "\\\\$1");
	}
	
	public static void matchEmail(){
		String el="^[_A-Za-z0-9-](\\.[_A-Za-z0-9-]|[_A-Za-z0-9-])*@[A-Za-z0-9-]+((\\.com)|(\\.net)|(\\.org)|(\\.info)|(\\.edu)|(\\.mil)|(\\.gov)|(\\.biz)|(\\.ws)|(\\.us)|(\\.tv)|(\\.cc)|(\\.aero)|(\\.arpa)|(\\.coop)|(\\.int)|(\\.jobs)|(\\.museum)|(\\.name)|(\\.pro)|(\\.travel)|(\\.nato)|(\\..{2,3})|(\\..{2,3}\\..{2,3}))$";
		String s="aizhi2006@gmail.com";
		System.out.println(s + ".matches(\"" + el + "\")" + "\n=" + s.matches(el));
	}
	
	public static void testNumber(){
	    String regex = "^[0]*?\\d{1,10}(\\.\\d{1,2})?$";//^[0]*?\\d{1,10}(\\.\\d{1,2})?$
	    //String regex1= "^[-\\+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";
	    String regex1 = "^(([1-9]\\d*)(\\.\\d{1,})|(0\\.[1-9]\\d*)|(0\\.\\d*[1-9]))$";
	    String[] rightArray = {"10000000000","10000000000.00","00000.15","0000923423.23","923423.23","0.00"};
	    String[] wrongArray = {"0","0.00","10000000000.01","adfasdfs"};
	    for(int i = 0; i < rightArray.length; i++) {
	        if(!rightArray[i].matches(regex1)){
	            System.out.println(rightArray[i] + " not matches.");
	        }
	    }
	}
	
	public static String[] testSplit(String key) {
	    String[] arr = key.split("[^a-zA-Z0-9]+");
        List<String> list = new ArrayList<String>();
        for (String str : arr) {
            if (!str.trim().equals("")){
                list.add(str);
            }
        }
        return list.toArray(new String[]{});
	}
	
	public static void testNumber2(){
        //String regex = "^[0]*?\\d{1,10}(\\.\\d{1,2})?$";//^[0]*?\\d{1,10}(\\.\\d{1,2})?$
        String regex = "^(1[0]+(\\.0{1,2})?|[1-9][0-9]{0,9}(\\.[0-9]{0,2})?|[0]\\.[0][1-9]|[0]\\.[1-9][0-9])$";//|[1-9][0-9]{0,9}(\\.[0-9]{0,2})?|[0]\\.[0][1-9]|[0]\\.[1-9][0-9])
        String[] rightArray = {"10000000000","10000000000.00","00000.15","0000923423.23","923423.23","0.00","0.11"};
        String[] wrongArray = {"0","0.00","10000000000.01","adfasdfs"};
        for(int i = 0; i < rightArray.length; i++) {
            if(!rightArray[i].matches(regex)){
                System.out.println(rightArray[i] + " not matches.");
            }
        }
    }
	
	/**
     * cut parameter in array cutStrs
     * @param path
     * @param cutStrs
     * @return
     */
    public static String makeParam(String path, String[] cutStrs){
        if(cutStrs == null || cutStrs.length == 0) return "";
        String regex = null;
        String[] temp = null;
        StringBuffer strb = null;
        for(int  i = 0; i < cutStrs.length ; i++){
            strb = new StringBuffer();
            regex = "[&]?" + cutStrs[i] + "=[^&]*";
            temp = path.split(regex);
            for(int j = 0; j < temp.length; j++){
                strb.append(temp[j]);
            }
            path = strb.toString();
        }
        if(strb.toString().startsWith("&")){
            strb.deleteCharAt(0);
        }
        return strb.toString();
    }
    
    public static void replaceTest() {
        String test = "true=true&sf.only['accountType']=2&sf.only['status']=2";
        String t1 = "&sf.only\\['accountType'\\]=2";
        System.out.println(test.replaceAll(t1, ""));
    }
    
    public static int getUnsubscribedMailType (String content, String str) {
        str = str.replaceAll("(\\.|\\\\\\|\\\\d|\\]|\\[|\\{|\\}|\\^|\\$|\\-|\\+|\\*|\\(|\\)|\\?)", "\\\\$1");
        String regex = "(" + str + "[\\d]+)";
        Pattern ptn = Pattern.compile(regex);
        Matcher mtc = ptn.matcher(content);
        String temp = null;
        while (mtc.find()) {
            temp = mtc.group();
        }
        temp = temp.replaceAll(".*&mailType=([\\d]+)", "$1");
        return Integer.parseInt(temp);
    }
    
    public static boolean testRegexMatched(String str, String regex) {
        return str.matches(regex);        
    }
    
    public static String testGroup(String oldString) {
        String regex = "^fmth([\\d]+)_(.*)$";
        String oldKey = oldString.replaceAll(regex, "$2");
        String maxLenStr = oldString.replaceAll(regex, "$1");
        return oldKey + " : " + maxLenStr;
    }

	public static void main(String[] args) {
//		String regex = "^[^\u4E00-\u9FA5]{0,10}$";//^[chr(0xa1)-chr(0xff)]+$/
//		String regex = "^[\\x00-\\xff]{0,10}$";
//		String str = "ÄãºÃsss";
//		if(str.matches(regex)){
//			System.out.println("ok");
//		}
//		TestRegex tr = new TestRegex();
//		List<TestClass> list = tr.getReadyData();
//		list = tr.test(list);
//		for(TestClass tc : list){
//			System.out.println("name:\t" + tc.getCatName() + "    " + tc.getPower());
//		}
		
		//checkCharLength("adfasdfasdfsdaf", 0, 10);
//		test();
//		String regex = "(^(a-z|A-Z|0-9|[^\\(\\)\\@\\&]){3,20}$)";
//		String str = "aaaa|aa";
//		System.out.println(str.matches(regex));
//		formatMobileCode("0000+0000086-21-6500125789");
//		testMatch();
//		getChineseChar();
//		testMobile("+86-4592");
//		String[] strs = getSpecificValue("((asdfasd)fasd)asdfasd");
//		for(int i = 0; i < strs.length; i++){
//			System.out.println(strs[i]);
//		}
//		testPassword("433&fsd.fsd");
		//System.out.println(changeSymbol("18 Speed Mountain [B+]Bike[+B] with Shimano Gears - Orange", "[B+]", "[+B]", "<a>", "</a>"));
//		matchEmail();
	    
//	    testNumber2();
//	    System.out.println(cutPhoneNumber("0000(+0086)02112343(0)"));
//	    testSymbol("lotuswlz@163.com", "\\@");
	    
//	    String[] strs = testSplit(",add   aa134aaa,   abc  ,,1");
//	    for (int i = 0; i < strs.length; i++) {
//	        System.out.println(i + ":" + strs[i]);
//	    }
	    
//	    replaceTest();
//	    testEmail("CATHYWU@OFFERME.com.au.CN");
	    
//	    int a = getUnsubscribedMailType(
//                "<a href=\"http://www.offerme.com.au/unsubscribe?mail=nikki_stone_model@hotmail.com&mailType=1\">unsubscribe</a>",
//                "www.offerme.com.au/unsubscribe?mail=nikki_stone_model@hotmail.com&mailType=");
//	    System.out.println(a);
	    
//	    System.out.println(testRegexMatched("asdf,,,,asd)", "^[a-z0-9'\\-_]+$"));
	    
	    System.out.println(testGroup("fmth400_content"));
	}
}
