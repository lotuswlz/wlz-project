/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-3-24      Cathy Wu        Create
 */

package testCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TestMap {

    public static void testMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("123", 123);
        map.put("456", 456);
        map.put("789", 789);
        map.put("abc", 2123);
        map.put("def", 1231);
        
        for (Iterator<Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            Entry<String, Integer> e = it.next();
            if (e.getKey().equals("456")) {
                it.remove();
            }
        }
        
        System.out.println(map.size());
        for (Iterator<Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            Entry<String, Integer> e = it.next();
            System.out.println(e.getKey() + "=" + e.getValue());
        }
    }
    
    public static void main(String[] args) {
        testMap();
    }
}
