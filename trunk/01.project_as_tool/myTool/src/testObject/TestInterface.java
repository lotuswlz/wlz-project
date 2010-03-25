/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject;

import java.math.BigDecimal;

public class TestInterface implements TestIt<BigDecimal>{

    private BigDecimal val;
    
    public TestInterface(BigDecimal val) {
        this.val = val;
    }
    
    public BigDecimal getValue() {
        return val;
    }

    public void setValue(BigDecimal val) {
        this.val = val;        
    }
    
    public static void main(String[] args) {
        TestIt ti = new TestInterface(new BigDecimal(100));
        System.out.println(ti.getValue().getClass().getName());
    }
}
