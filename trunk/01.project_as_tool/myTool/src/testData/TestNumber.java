package testData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestNumber {

    public static long[] getRelativeMaxNumber(long[] nums) {
        long[] arr = new long[2];
        long max = nums[0];
        long min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != max && nums[i] >= 1.5 * min) {
                min = nums[i];
                arr[1] = min;
            }
        }
        arr[0] = max;
        return arr;
    }
    
    public static void calcPercent() {
        BigDecimal num = null;
        BigDecimal temp = null;
        BigDecimal temp1 = null;
        String min = "0.3";
        String max = "0.4";
        for (int i = 1; i <= 10; i++) {
            num = new BigDecimal(i).multiply(new BigDecimal(min)).setScale(0,
                    RoundingMode.CEILING);
            temp = new BigDecimal(i).multiply(new BigDecimal(min)).setScale(
                    3, RoundingMode.CEILING);
            temp1 = new BigDecimal(i).multiply(new BigDecimal(min)).setScale(
                    0, RoundingMode.CEILING);
            System.out.print(i
                    + " * "
                    + min
                    + " = "
                    + num + " is " + num.intValue() + ", " + temp1 + "; ");
            num = new BigDecimal(i).multiply(new BigDecimal(max)).setScale(0,
                    RoundingMode.CEILING);
            temp = new BigDecimal(i).multiply(new BigDecimal(max)).setScale(
                    3, RoundingMode.CEILING);
            temp1 = new BigDecimal(i).multiply(new BigDecimal(max)).setScale(
                    0, RoundingMode.CEILING);
            System.out.println(i
                    + " * "
                    + max
                    + " = "
                    + num + " is " + num.intValue() + ", " + temp1);
        }
    }
    
    public static void calcPercent(BigDecimal num, double p) {
            num = num.multiply(new BigDecimal(p)).setScale(3,
                RoundingMode.HALF_UP).setScale(0, RoundingMode.CEILING);
            System.out.println(num.intValue());
    }
    
    public static void main(String[] args) {
//        long[] nums = getRelativeMaxNumber(new long[]{95,95,95});
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
        calcPercent(new BigDecimal(55), 0.9);
        calcPercent();
    }
}
