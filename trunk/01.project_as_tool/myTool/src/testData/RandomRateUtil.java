/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-3-10      Cathy Wu        Create
 */

package testData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class RandomRateUtil {
    
    private static List<Integer> PRIME_NUM_LIST;
    static {
        PRIME_NUM_LIST = new ArrayList<Integer>();
        PRIME_NUM_LIST.add(2);
    }
    
    /**
     * 获得最大公约数(Greatest common divisor)，如果没有，即返回1
     * @return
     */
    private static int getGCD(int[] numArr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> temp = null;
        int num = 0;
        for (int i = 0; i < numArr.length; i++) {
            num = numArr[i];
            if (num < 2 || isPrimeNumber(num)) {
                return 1;
            }
            temp = getDivisorList(num);
            for (int n : temp) {
                if (map.get(n) == null) {
                    map.put(n, 1);
                } else {
                    map.put(n, map.get(n) + 1);
                }
            }
        }
        int divisor = 1;
        for (Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            Entry<Integer, Integer> e = it.next();
            if (e.getValue() == numArr.length) {
                divisor = divisor * e.getKey();
            }
        }
        return divisor;
    }
    
    /**
     * 获得指定数字的约数列表
     * @param num
     * @return
     */
    private static List<Integer> getDivisorList(int num) {
        List<Integer> temp = getPrimeNumber(num);
        List<Integer> list = new ArrayList<Integer>();
        int n = 0;
        for (int i = 0; i < temp.size(); i++) {
            n = temp.get(i);
            if (n > num / 2 + 1) {
                break;
            }
            if (num % n == 0) {
                list.add(n);
            }
        }        
        return list;
    }
    
    /**
     * <span style="font-size:15px">折半法查找列表内比指定数字小的最大数字</span> <br>
     * 前提条件：list必须是<b>正序排列</b>
     * @param list
     * @param findNum
     * @return
     */
    private static int splitHalfFindMaxNumLessThan(List<Integer> list, int findNum) {
        int index = list.size() / 2 + 1;
        if (list.get(index) == findNum) {
            return index;
        } else if (list.get(index) > findNum) {
            while (list.get(index) > findNum && index > -1) {
                index--;
            }
        } else {
            while (list.get(index) < findNum && index < list.size()) {
                index++;
            }
            if (index < list.size() && list.get(index) > findNum) {
                index--;
            }
        }
        if (index >= list.size()) {
            index = -1;
        }
        return index;
    }
    
    /**
     * 获得指定maxNum以内的质数集合
     * @param maxNum
     * @return
     */
    private static List<Integer> getPrimeNumber(int maxNum) {
        if (PRIME_NUM_LIST.size() > 0 && PRIME_NUM_LIST.get(PRIME_NUM_LIST.size() - 1) > maxNum) {
            int index = splitHalfFindMaxNumLessThan(PRIME_NUM_LIST, maxNum);
            if (index == -1) {
                return new ArrayList<Integer>();
            }
            return PRIME_NUM_LIST.subList(0, index + 1);
        }
        int fromNum = 3;
        if (PRIME_NUM_LIST.contains(2) && PRIME_NUM_LIST.size() > 1) {
            fromNum = PRIME_NUM_LIST.get(PRIME_NUM_LIST.size() - 1) + 2;
        } else if (!PRIME_NUM_LIST.contains(2)) {
            PRIME_NUM_LIST.add(2);
        }
        for (int i = fromNum; i <= maxNum; i = i+2) {
            if (isPrimeNumber(i)) {
                PRIME_NUM_LIST.add(i);
            }
        }
        return PRIME_NUM_LIST;
    }
    
    private static boolean isPrimeNumber(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        for (int i = 2; i < num/2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static int getRandomNumByProbabilityRange(int[] valArr, float[] rateArr) {
        ///TODO It need validation code here
        int[] rateIntArr = new int[rateArr.length + 1];
        BigDecimal bnum = null;
        float f = 0;
        int sum = 0;
        for (int i = 0; i < rateArr.length; i++) {
            f = rateArr[i];
            bnum = new BigDecimal(f);
            bnum = bnum.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            rateIntArr[i] = bnum.intValue();
            sum += bnum.intValue();
        }
        rateIntArr[rateIntArr.length - 1] = sum;
        
        int gcd = getGCD(rateIntArr);
        
        int[] result = new int[sum/gcd];
        int rIdx = 0;
        for (int i = 0; i < valArr.length; i++) {
            for (int j = 0; j < rateIntArr[i]/gcd; j++) {
                result[rIdx + j] = valArr[i];
            }
            rIdx += rateIntArr[i]/gcd;
        }
        Random rd = new Random();
        int index = rd.nextInt(sum/gcd);
        return result[index];
    }
    
    public static int getRandomNum(int from, int to) {
        if (from == to) {
            return from;
        } else if (from > to) {
            return to;
        }
        int sep = to - from;
        Random rd = new Random();
        return Math.abs(rd.nextInt(sep)) + from;
    }
    
    public static void testMethod() {
        long m = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int temp = 0;
        int baseNum = 1;
        /**
         * start: getRandomNumByProbabilityRange
         */
        int[] valArr = {10, 12, 13};
        float[] rateArr = {0.10f,0.25f,0.65f};
        /**
         * end: getRandomNumByProbabilityRange
         */
        for (int i = 0; i < baseNum; i++) {
            temp = getRandomNumByProbabilityRange(valArr, rateArr);
            if (map.get(temp) == null) {
                map.put(temp, 1);
            } else {
                map.put(temp, map.get(temp) + 1);
            }
        }
        for (Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            Entry<Integer, Integer> e = it.next();
            System.out.println(e.getKey() + ":" + e.getValue() + "," + new BigDecimal(e.getValue()).multiply(new BigDecimal(100)).divide(new BigDecimal(baseNum), 1, RoundingMode.HALF_UP) + "%");
        }
        System.out.println(System.currentTimeMillis() - m);
    }
    
    public static void main(String[] args) {
        testMethod();
    }
}
