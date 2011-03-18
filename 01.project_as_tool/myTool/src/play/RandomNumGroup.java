/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-11-30      Cathy Wu        Create
 */

package play;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class RandomNumGroup {

	public static void test() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Integer cnt = 0;
		String num = null;
		for (int i = 0; i < 100000; i++) {
			num = getRandomNumber();
			cnt = map.get(num);
			if (cnt != null) {
				map.put(num, cnt+1);
			} else {
				map.put(num, 1);
			}
		}
		
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < 10; i++) {
			result.put(String.valueOf(i), map.get(String.valueOf(i)));
		}
		
		for (Iterator<Map.Entry<String, Integer>> it = result.entrySet()
				.iterator(); it.hasNext();) {
			Entry<String, Integer> e = it.next();
			System.out.println(e.getKey()
					+ ": "
					+ e.getValue()
					+ " ("
					+ (new BigDecimal(String.valueOf(e.getValue())).multiply(
							new BigDecimal(100)).divide(new BigDecimal(100000))
							.setScale(2, RoundingMode.HALF_UP)) + "%)");
		}
	}
	
	public static String getRandomNumber() {

		long[] random = new long[1];
		for (int i = 0; i < 1; i++) {
			random[i] = Math.round(Math.floor((Math.random() * 10)));
		}
		//System.out.println(Math.random());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < random.length; i++) {
			String temp = Long.toString(random[i]);
			sb.append(temp);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		test();
	}
}
