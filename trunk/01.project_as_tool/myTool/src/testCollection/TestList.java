package testCollection;

import java.util.ArrayList;
import java.util.List;

public class TestList {

    public static void testList() {
        List<String> list = new ArrayList<String>(4);
        list.add("abc");
        list.add(1, "abc2");
        String str = null;
        for (int i = 0; i < list.size(); i++) {
            str = list.get(i);
            if (str == null) {
                continue;
            }
            System.out.println(i + ":" + str);
        }
    }
    
    public static void testRemoveElement(List<String> arr) {
        System.out.println("=========testRemoveElement:");
        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("werwewrq");
        list.add("ddd");
        list.add("asfdsda");
        list.add("abc1");
        list.add("abc");
        List<String> sameList = new ArrayList<String>();
        String str = null;
        for (int i = 0; i < list.size(); i++) {
//            System.out.println("i=" + i);
            str = list.get(i);
            System.out.println("current str: " + str);
            String temp = null;
            for (int j = 0; j < arr.size(); j++) {
                System.out.println("j=" + j);
                temp = arr.get(j);
                if (!str.equals(temp)) {
                    continue;
                }
                sameList.add(str);
                arr.remove(j--);
            }
        }
        System.out.println("complete change");
        
        for (String s : sameList) {
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        testList();
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("ddd");
        testRemoveElement(list);
    }
}
