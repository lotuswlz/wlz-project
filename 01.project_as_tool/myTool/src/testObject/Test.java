/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject;

import java.math.BigDecimal;

public class Test extends ClsMaker<BigDecimal> {
    public void testBaseClass() {
        super.setData(new BigDecimal(100));
    }
    @Override
    public BigDecimal getData() {
        // TODO Auto-generated method stub
        this.testBaseClass();
        return super.getData();
    }
    
    public static void main(String[] args) {
        ClsMaker<BigDecimal> c = new Test();
        System.out.println(c.getData());
    }
}
