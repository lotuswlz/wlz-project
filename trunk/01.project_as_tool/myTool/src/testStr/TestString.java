package testStr;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {
	
	public String makeParam(String path, String[] cutStrs){
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
	
	public void testList(List<String> list){
		System.out.println(list.get(0));
		for(int i = 1; i < list.size() - 1; i++){
			System.out.println((i + 1) + ":" + list.get(i));
		}
		System.out.println(list.get(list.size() - 1));
	}
	
	public void test(){
		byte[] b = "\u4e00".getBytes();
		for(int i = 0; i < b.length; i ++){
			System.out.println(b[i]);
		}
	}
	
	public static void main(String[] args) {
		TestString ts = new TestString();
//		System.out.println(ts.makeParam("key=123&c=123&x=6&d=90", new String[]{"d"}));
//		java.util.List<Object> list = new java.util.ArrayList<Object>();
//		list.add("a");
//		list.add(5);
//		System.out.println(list.toString());
//		ts.test();
		
//		List<String> list = new ArrayList<String>();
//		list.add("1");
//		list.add("2");
//		list.add("3");
//		
//		ts.testList(list);
		
//		String[] strs = ts.getSqlParams("hp compaq \"62 /50\"");
//		System.out.println(strs.length);
//		for(String str: strs){
//			System.out.println(str);
//		}
//		List<Map<String, String>> list = ts.getPropertyList("c=0&status=2");
//		for(Map<String, String> map : list){
//			for(Iterator it = map.entrySet().iterator(); it.hasNext();){
//				Map.Entry e = (Entry) it.next();
//				System.out.println(e.getKey() + ":" + e.getValue());
//			}
//		}
		
//		System.out.println(ts.getSqlParams("apple     ipod-1-32", false));
//		System.out.println(formatStrToHtml2("adljk     askd lka    sd  kasdjiewls  \ndslkjf"));
		
		
		
		
//		testTrim();
//		try {
//		    System.out.println("testChineseChars");
//            testChineseChars();
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
		
		
		
		String regex = "[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}";
		if ("2009-01-31 20:59:59".matches(regex)) {
		    System.out.println("true");
		}
	}
	
	public String getSqlParams(String keyword, boolean completedMatched){
		if(keyword == null || "".equals(keyword.trim())){return "";}
		
		String regex = "[\\-|\\|/|\\||\\(|\\)|'|\"]+";
		keyword = keyword.replaceAll(regex, " ").trim();
		keyword = keyword.replaceAll("\\s+", " ");
		
		if("".equals(keyword.trim())){return "";}
		
		String[] s = keyword.split("\\s");
		StringBuffer temp = new StringBuffer("");
		for(String str: s){
			if(!"".equals(str.trim())){
				if(completedMatched){
					temp.append(" {");
					temp.append(str.trim());
					temp.append("}");
				}else{
					temp.append(",${");
					temp.append(str.trim());
					temp.append("}");
				}
			}
		}
		if(temp.toString().trim().length() > 0){
			temp = temp.deleteCharAt(0);
		}
		return temp.toString();
	}
	
	public List<Map<String, String>> getPropertyList(String queryStr){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		String[] strs = queryStr.split("&");
		String[] arr = null;
		Map<String, String> map = null;
		for(int i = 0; i < strs.length; i++){
			arr = strs[i].split("=");
			map = new LinkedHashMap<String, String>();
			map.put(arr[0], arr.length >= 2 ? arr[1] : "");
		    list.add(map);
		}
		return list;
	}
	
	/**
	 * 能写这么麻烦的函数，太失败了。。。----Wu 2008-10-17
	 * @param str
	 * @return
	 */
	public static String formatStrToHtml(String str){
		if(str==null){
			return null;
		}
		
		Pattern ptn = Pattern.compile(" +");
		Matcher mc = ptn.matcher(str);
		String temp = null;
		StringBuffer sb = new StringBuffer();
		int end = 0;
		while(mc.find()){
			temp = mc.group().replaceAll(" {2}", " &nbsp;");
			sb.append(str.substring(end, mc.start()));
			sb.append(temp);
			end = mc.end();
		}
		sb.append(str.substring(end));
		str = sb.toString();
		str = str.replace("\n", "<br />");
		return str;
	}
	
	public static String formatStrToHtml2(String str){
		if(str==null){
			return null;
		}
		return str.replaceAll(" {2}", " &nbsp;").replace("\n", "<br />");
	}
	
	public static void testTrim() {
	    String str = "    \n    ";
	    System.out.println("a" + str.trim() + "b");
	}
	
	public static void testChineseChars() throws UnsupportedEncodingException {
	    String str = "你好abc";
	    System.out.println(str.trim().getBytes("UTF-8").length);
	}
}
