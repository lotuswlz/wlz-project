/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-6-29        Cathy         Create
 */

package testCollection.collectionCompareTest;

import java.util.ArrayList;
import java.util.List;

import testCollection.collectionCompareTest.MyCollection.CCriteria;
import testCollection.collectionCompareTest.MyCollection.CStatus;

public class TestClass {
    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
        List<MyCollection> list = new ArrayList<MyCollection>();
        MyCollection c = null;
        java.util.Random r=new java.util.Random(System.currentTimeMillis());

        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        int userId = 0;
        for (int i = 0; i < 5; i++) {
            userId = r.nextInt();
            c = new MyCollection(userId, arr[Integer.parseInt(String.valueOf(userId).substring(1, 2))], Math.abs(userId / 2000));
            list.add(c);
        }
        System.out.println("get ready for data for " + (System.currentTimeMillis() - currentTime) + " seconds");
        
        currentTime = System.currentTimeMillis();
        MyCollection.sortMyCollection(list, CStatus.USER_ID, CCriteria.ASC);
        for (MyCollection n : list) {
            System.out.println("id:" + n.getUserId() + ";\t name:"
                    + n.getUserName() + ";\t age:" + n.getAge());
        }
        System.out.println("\n\n Total: " + (System.currentTimeMillis() - currentTime));
        
        currentTime = System.currentTimeMillis();
        MyCollection.sortMyCollection(list, CStatus.USER_NAME, CCriteria.ASC);
        for (MyCollection n : list) {
            System.out.println("id:" + n.getUserId() + ";\t name:"
                    + n.getUserName() + ";\t age:" + n.getAge());
        }
        System.out.println("\n\n Total: " + (System.currentTimeMillis() - currentTime));
        
        currentTime = System.currentTimeMillis();
        MyCollection.sortMyCollection(list, CStatus.AGE, CCriteria.DESC);
        for (MyCollection n : list) {
            System.out.println("id:" + n.getUserId() + ";\t name:"
                    + n.getUserName() + ";\t age:" + n.getAge());
        }
        System.out.println("\n\n Total: " + (System.currentTimeMillis() - currentTime));
        System.gc();
    }
}
