/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-2-1      Cathy Wu        Create
 */

package testData;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class TestPriceCalc {
    
    private static NumberFormat snf = NumberFormat.getNumberInstance();
    static {
        snf.setMaximumFractionDigits(2);
        snf.setMinimumFractionDigits(0);
    }

    public BigDecimal getRoughPriceVersion1(BigDecimal p) {
        final BigDecimal extra = new BigDecimal("0.0001");
        return p.add(extra).setScale(-1, BigDecimal.ROUND_UP).setScale(2);
    }
    
    public BigDecimal getRoughPrice(BigDecimal p) {
        if (p == null || p.equals(BigDecimal.ZERO)) {
            return p;
        }
        final String[] level = getPriceLevel(p);
        if (level == null) {
            return p;
        }
        
        final int roundNum = -1 * String.valueOf(Integer.parseInt(level[2]) - 1).length();
        final BigDecimal extra = new BigDecimal(level[2]);
        BigDecimal temp = p.setScale(roundNum, BigDecimal.ROUND_DOWN);
        BigDecimal temp2 = p.subtract(temp);
        if (temp2.compareTo(extra) < 0) {
            temp2 = temp2.add(extra).add(new BigDecimal(0.001)).setScale(roundNum, BigDecimal.ROUND_UP);    
            temp2 = temp2.subtract(extra);
        } else {
            temp2 = temp2.add(extra).setScale(roundNum, BigDecimal.ROUND_DOWN);
        }
        return temp.add(temp2);
    }
    
    private String[] getPriceLevel(BigDecimal p) {
        final String[][] level = {{"0", "50", "5"},{"50", "200", "10"},{"200", "1000", "50"},{"1000", "10000", "100"}};
        for (String[] str : level) {
            if (p.compareTo(new BigDecimal(str[0])) >= 0
                    && p.compareTo(new BigDecimal(str[1])) < 0) {
                return str;
            }
        }
        return level[level.length - 1];
    }
    
    public static void main(String[] args) {
        TestPriceCalc tc = new TestPriceCalc();
        showPrice(tc.getRoughPrice(new BigDecimal("1")));
//        for (int i = 1000; i < 10005; i++) {
//            double num = 1;
////            int x = 10;
//            // 1: 
//            num = i;
//            System.out.print(snf.format(num) + "\t = ");
//            showPrice(tc.getRoughPrice(new BigDecimal(num)));
//            // 2: 
////            num = i + x * 0.1;
////            System.out.print(snf.format(num) + "\t = ");
////            showPrice(tc.getRoughPrice(new BigDecimal(num)));
////            // 3: 
////            num = i + x * 0.4;
////            System.out.print(snf.format(num) + "\t = ");
////            showPrice(tc.getRoughPrice(new BigDecimal(num)));
////            // 4: 
////            num = i + x * 0.5;
////            System.out.print(snf.format(num) + "\t = ");
////            showPrice(tc.getRoughPrice(new BigDecimal(num)));
////            // 5: 
////            num = i + x * 0.9;
////            System.out.print(snf.format(num) + "\t = ");
////            showPrice(tc.getRoughPrice(new BigDecimal(num)));
//        }
        //showPrice(tc.getRoughPrice(new BigDecimal(4.3)));
    }
    
    public static void showPrice(BigDecimal p) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        System.out.println("Under " + nf.format(p));
    }
}
