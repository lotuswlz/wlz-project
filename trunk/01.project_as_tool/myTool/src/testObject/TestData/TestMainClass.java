/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.TestData;

import java.math.BigDecimal;

import testObject.TestData.StringValues;

public class TestMainClass {
    
    public static void test(int type) {
        SettingBean bean = new SettingBean();
        bean.setSettingId(1L);
        switch (type) {
        case 1:
            bean.setType(new BigDecimalValue(100));
            break;
        case 2:
            bean.setType(new StringValues("sdasdfasdf"));
            break;

        default:
            break;
        }
        System.out.println(bean.getType().getValue());
    }

    public static void main(String[] args) {
        test(1);
        test(2);
    }
}
