package testData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class TestRandom {

	public static String getRandomNumber() {

		long[] random = new long[6];
		for (int i = 0; i < 6; i++) {
			random[i] = Math.round(Math.floor((Math.random() * 10)));
		}
		System.out.println(Math.random());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < random.length; i++) {
			String temp = Long.toString(random[i]);
			sb.append(temp);
		}
		return sb.toString();
	}

	public static String getRand() {

		Random random = new Random();
		Long randNumber = new Random().nextLong();
		System.out.println(randNumber);
		randNumber=Math.abs(new Random().nextLong());
		String result=Long.toString(randNumber);//Long.toString(randNumber).substring(0, 6);
		System.out.println(result);
		return result;
	}
	
	
	public static void main(String[] args) {
//		for(int i = 0; i < 10; i++){
//			System.out.println(getRandomNumber());
//			System.out.println("---------------------");
//			getRand();
//		}
	    testGetAnyRandInt(15, 100000);
	    /*System.out.println("-------------------");
	    int minCount = 0;
	    int maxCount = 0;
	    int count = 0;
	    int averageCount = 0;
	    for (int i = 0; i < 100; i++) {
	        count = testMyRandInt(6);
	        if (i == 0 || minCount > count) {
	            minCount = count;
	        }
	        if (i == 0 || maxCount < count) {
	            maxCount = count;
	        }
	        averageCount += count;
	        System.out.println("No." + i + "\t" + count + "\ttimes");
	    }
	    System.out.println("max times: " + maxCount);
	    System.out.println("min times: " + minCount);
	    System.out.println("average times: " + (averageCount / 100));*/
	    
	    System.out.println("----------");
	    System.out.println(getAnyRandInt(5));
	    
	    System.out.println(getRand());
	}

    public static Map<Integer, Integer> testGetAnyRandInt(int maxNum, int testCount) {
		int num = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < testCount; i++) {
		    num = getAnyRandInt(maxNum);
		    map.put(num, map.get(num) == null ? 1 : map.get(num) + 1);
		}
		
		BigDecimal rate = null;
		String temp = null;
		BigDecimal rate1 = BigDecimal.ZERO;
		for (int i = 0; i < maxNum; i++) {
		    if (map.get(i) == null) {
		        temp = "0";
		    } else {
		        temp = String.valueOf(map.get(i));
		    }
            rate = new BigDecimal(temp);
            rate = rate.divide(new BigDecimal(testCount).divide(new BigDecimal(
                    100), 5, RoundingMode.HALF_UP), 2, RoundingMode.HALF_UP);
            System.out.println(i + " : " + temp + " (" + rate + "%) " + rate.subtract(rate1) + "%");
            rate1 = rate;
        }
		return map;
    }
    
    public static int testMyRandInt(int level) {
        int count = 6;
        int cnt = 0;
        int totalCnt = 0;
        while (cnt < count) {
            if (getAnyRandInt(level) == level - 1) {
                cnt++;
            }
            totalCnt++;
        }
        return totalCnt;
    }
	
	public static int getAnyRandInt(int n) {
        int max = n + 1;
        int bigend = ((1 + max) * max) / 2;
        Random rd = new Random();
        int x = Math.abs(rd.nextInt() % bigend);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (max - i);
            if (sum > x) {
                return i;
            }
        }
        return -1;
    }

}
