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

public class MyCollection implements Comparable {
    
    private long userId;
    
    private String userName;
    
    private int age;
    
    public MyCollection() {
        
    }
    
    public MyCollection(long userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public int compareTo(MyCollection o, CStatus s, CCriteria c) {
        int v = 0;
        if (s.equals(CStatus.USER_ID)) {
            if (o.userId > this.userId) {
                v = -1;
            } else if(o.userId == this.userId) {
                v = 0;
            } else {
                v = 1;
            }
        } else if (s.equals(CStatus.USER_NAME)) {
            v = this.userName.compareTo(o.getUserName());
        } else if (s.equals(CStatus.AGE)) {
            if (o.age > this.age) {
                v = -1;
            } else if (o.age == this.age) {
                v = 0;
            } else if (o.age < this.age) {
                v = 1;
            }
        }
        if (c.equals(CCriteria.ASC)) {
            return -1 * v;
        }
        return v;
    }

    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    protected enum CStatus {
        
        USER_ID(0), USER_NAME(1), AGE(2);
        
        private int status;
        
        private CStatus(int status) {
            this.status = status;
        }
        
        public int getValue() {
            return this.status;
        }
    }
    
    protected enum CCriteria {
        ASC(0), DESC(1);
        
        private int value;
        
        private CCriteria(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    public static void sortMyCollection(List<MyCollection> list, CStatus s, CCriteria ca) {
        MyCollection c = null;
        MyCollection y = null;
        for (int i = 0; i < list.size() - 1; i++) {
            c = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                y = list.get(j);
                if (y.compareTo(c, s, ca) > 0) {
                    list.set(i, y);
                    list.set(j, c);
                }
            }
        }
    }
}
