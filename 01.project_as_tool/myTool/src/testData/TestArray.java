package testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestArray {

	public static void sort(int[] array){
		int temp = 0;
		for(int i = 0; i < array.length - 1; i++){
			for(int j = i + 1; j < array.length; j++){
				if(array[i] < array[j]){
					temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}				
			}
		}
		
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + ",");
		}
	}
	
	public static void advancedSort(int[] array){
		int temp = 0;
		int[] arr = new int[array.length];
		for(int i = 0; i < array.length; i++){
			if(i == 0){
				arr[i] = array[i];
			}else{
				temp = array[i];
				for(int j = 0; j < arr.length; j++){
					if(arr[j] < temp){
						
					}else{
						break;
					}
				}
			}
		}
	}
	
	public static void getDistinctBean(){
		List<String> list = new ArrayList<String>();
		String[] strs = {"abc","abc","erw","asdf","fjdkl","asdf","asdf","aaa","aaa","aaa","erwerw"};
		for(String temp : strs){
			list.add(temp);
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		String temp = null;
		for(int i = 0; i < list.size(); i++){
			temp = list.get(i);
			if(map.get(temp) == null){
				map.put(temp, temp);
			}else{
				list.remove(i);
				i--;
			}
		}
		
		System.out.println(list);
	}
	
	public static void main(String[] args) {
//		int[] array = {1,15,9,-1,20,100};
//		sort(array);
//		getDistinctBean();
		HashMap<Long, String> map = new HashMap<Long, String>();
		map.put(0L, "asdfasdf");
		map.put(2L, "asdfasdf a  dafsdf");
		if(map.containsKey(new Long(0L))){
			System.out.println(map.get(0L));
		}
	}
}
