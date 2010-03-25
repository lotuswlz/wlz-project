package testData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TextFormatter {

    public void testFormatData() {
        final int endPathLength = 4;
        
        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(endPathLength);
        
        String imageIdStr = df.format(100L);
        System.out.println(imageIdStr.substring(imageIdStr.length() - endPathLength));
    }
    
    public static String getImagePath(long imageId) {
        final int prePathLength = 12;
        final int eachPathLength = 4;
        StringBuffer path = new StringBuffer();
        String itemIdStr = imageId + "";
        for (int i = 0; i < (16 - itemIdStr.length()); i++) {
            path.append("0");
        }
        path.append(itemIdStr);

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < path.length(); i++) {
            if (i >= prePathLength) {
                break;
            }
            if ((i + 1) % eachPathLength != 0) {
                result.append(path.charAt(i));
            } else {
                result.append(path.charAt(i));
                result.append("/");
            }
        }
        return result.toString();
    }
    
    public static String getPath(long imageId) {
        final int prePathLength = 12;
        final int eachPathLength = 4;
        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(16);
        String fullPath = df.format(imageId);
        fullPath = fullPath.substring(0, prePathLength);
        fullPath = fullPath.replaceAll("(\\d{" + eachPathLength + "})", "$1/");
        return fullPath;
    }
    
    public static void main(String[] args) {
        TextFormatter tf = new TextFormatter();
        tf.testFormatData();
        System.out.println(getImagePath(23423423L));
        System.out.println(getPath(23423423L));
        System.out.println(getPercentageStr(new BigDecimal("0.35"), 0));
        System.out.println(getPercentageStr2(new BigDecimal("0.355"), 2));
        System.out.println(calcPercentageValue(new BigDecimal("30")));
        System.out.println(formatNumber(new BigDecimal(12)));
    }
    
    public static String getPercentageStr(BigDecimal num, int scale) {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(scale);
        format.setMinimumFractionDigits(0);
        String temp = format.format(num);
        return temp.substring(0, temp.length() - 1);
    }
    
    public static String getPercentageStr2(BigDecimal num, int scale) {
        NumberFormat aaa= NumberFormat.getInstance();
        aaa.setMaximumFractionDigits(scale);
        aaa.setMinimumFractionDigits(0);
        return aaa.format(num);
    }
    
    public static String calcPercentageValue(BigDecimal num) {
        String temp = null;
        num = num.divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        temp = format.format(num);
        return temp;
    }
    
    public static String formatNumber(BigDecimal num) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format.format(num);
    }
}
