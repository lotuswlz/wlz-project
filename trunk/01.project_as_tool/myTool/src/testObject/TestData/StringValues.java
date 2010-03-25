/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.TestData;

public class StringValues implements DataType<String> {

    private String value;
    public StringValues(String val) {
        this.value = val;
    }
    public StringValues() {
        
    }
    public String getValue() {
        return this.value;
    }
    
}