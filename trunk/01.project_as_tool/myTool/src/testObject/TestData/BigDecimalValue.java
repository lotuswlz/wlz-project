/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.TestData;

import java.math.BigDecimal;

public class BigDecimalValue implements DataType<BigDecimal> {

    private BigDecimal value;
    
    public BigDecimalValue(BigDecimal val) {
        this.value = val;
    }
    public BigDecimalValue(String val) {
        this.value = new BigDecimal(val);
    }
    public BigDecimalValue(int val) {
        this.value = new BigDecimal(val);
    }
    public BigDecimalValue(float val) {
        this.value = new BigDecimal(val);
    }
    public BigDecimalValue(double val) {
        this.value = new BigDecimal(val);
    }
    
    
    public BigDecimal getValue() {
        return this.value;
    }
}
