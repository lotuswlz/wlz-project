package testData;

import java.util.HashMap;

public class ConvertMusic {

	private static HashMap<String, String> map = new HashMap<String, String>();
	static{
		map.put("-1","A");
		map.put("-2","B");
		map.put("-3","C");
		map.put("-4","D");
		map.put("-5","E");
		map.put("-6","F");
		map.put("-7","G");
		map.put("1","H");
		map.put("2","I");
		map.put("3","J");
		map.put("4","K");
		map.put("5","L");
		map.put("6","M");
		map.put("7","N");
		map.put("+1","O");
		map.put("+2","P");
		map.put("+3","Q");
		map.put("+4","R");
		map.put("+5","S");
		map.put("+6","T");
		map.put("+7","U");
		map.put("++1","V");
		map.put("++2","W");
		map.put("++3","X");
		map.put("++4","Y");
		map.put("++5","Z");
	}
	public void changeMusic(String str){
		String[] arr = str.split(" ");
		StringBuffer sbf = new StringBuffer();
		for(int i = 0; i < arr.length; i++){
			if(arr[i].equals("_") || arr[i].equals("*")){
				sbf.append(" -");
			}else if(arr[i].equals("$")){
				sbf.append("$");
			}else{
				sbf.append(" " + map.get(arr[i]));
			}
		}
		System.out.println(sbf.toString());
	}
	
	public static void main(String[] args) {
		ConvertMusic cm = new ConvertMusic();
		cm.changeMusic("5 6 +1 _ 3 5 6 _ 5 6 3 5 1 _ 2 3 3 5 6 5 6 +1 +2 _ 6 +1 3 5 3 2 -6 1");
		cm.changeMusic("3 3 4 5 5 4 3 2 _ 1 1 2 3 3 2 2");
		cm.changeMusic("6 6 $ 6 +1 +2 $ 6 _ 5 $ 5 6 5 5 6 $ +1 $ +2 _ 3 _ 3 2 $ 3 5 3 $ 5 _ 6 +1 +2 +3 +1 +2 +3 +2 +1 6 5 6 +1 +2 6 +1 5 2 3 _ 3 6 +1 5 6 5 3 2 _ 3 5 -6 -5 -6 1 _ 2 3 2 1 2 3 1 _ 2 2 +3 +2");
		cm.changeMusic("6 +3 +2 +3 +1 +2 7 +1 6 6 5 5 _ 6 +3 +2 +1 _ 7 +1 7 6 5 6 _ 6 +3 +2 +3 _ +1 +2 7 +1 _ 6 6 5 5 _ 6 +3 +2 +3 _ 7 +1 7 6 5 6 _ 3");
		cm.changeMusic("6 6 _ 6 7 +1 * 7 6 5 * 5 6 7 * 6 3 * 3 2 3 * 5 5 * 5 3 2 3 * 5 6 7 * +1 7 6 * 7 5 5 * 5 6 7 6");
	}
}
