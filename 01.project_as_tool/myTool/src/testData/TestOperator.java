/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-1-5      Cathy Wu        Create
 */

package testData;

public class TestOperator {

    public static void main(String[] args) {
//        long baseNum = 15L;
//        long c = 1L;
//        System.out.println(baseNum&(~c));
        
        long rgtsts = 0L;
        long rgt = 13L;
        if (rgtsts == 1L) {
            rgtsts = rgt | rgtsts;
        } else {
            rgtsts = rgt & (~1L);
        }
        System.out.println(rgtsts);
    }
}
