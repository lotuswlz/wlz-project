/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-12-1      Cathy Wu        Create
 */

package testCollection.collectionConvert;

public class TestBean {

    private String key;
    private String value;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "{key: " + this.key + ", value: " + this.value + "}";
    }
}
