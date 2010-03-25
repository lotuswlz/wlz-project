/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-12-1      Cathy Wu        Create
 */

package testCollection.collectionConvert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class TestMapToList {

    public void testMapToList() {
        LinkedHashMap<String, TestBean> map = new LinkedHashMap<String, TestBean>();
        TestBean t = new TestBean();
        t.setKey("a");
        t.setValue("213132132");
        map.put(t.getKey(), t);
        t = new TestBean();
        t.setKey("b");
        t.setValue("23158746156");
        map.put(t.getKey(), t);
        
        TestBean[] arr = map.values().toArray(new TestBean[]{});
        for (TestBean b : arr) {
            System.out.println(b.toString());
        }
    }
    
    public static void main(String[] args) {
        TestMapToList t = new TestMapToList();
        t.testMapToList();
    }
}
