/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-19      Cathy Wu        Create
 */

package testData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestCalc {

    public static void testCalc(BigDecimal mainNum, BigDecimal starthNum, BigDecimal stepNum) {
        // how many
        int count = 0;
        while (mainNum.compareTo(BigDecimal.ZERO) > 0) {
            count++;
            mainNum = mainNum.subtract(starthNum.add(
                            stepNum.multiply(new BigDecimal(count - 1))));
            System.out.println(count + ": " + mainNum.setScale(2, RoundingMode.HALF_UP));
            if (count >= 50) {
                break;
            }
        }
        System.out.println("Total " + count + ".");
        System.out.println("Left " + (count < 50 ? 0 : mainNum.setScale(2, RoundingMode.HALF_UP)));
    }
    
    public static void main(String[] args) {
        testCalc(new BigDecimal(100000000), new BigDecimal(100000), new BigDecimal(60000));
    }
}
