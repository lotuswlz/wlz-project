package testClass.testContains;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

    public static void main(String[] args) {
//        testContainsObject();
        testConvertFromSuperClassToChild();
    }
    
    public static void testConvertFromSuperClassToChild() {
        List<ChildClass> list = new ArrayList<ChildClass>();
        ChildClass bean = new ChildClass();
        bean.setMail("abc");
        bean.setName("aaa");
        bean.setDesc("ccc");
        list.add(bean);
        bean = new ChildClass();
        bean.setMail("ccc");
        bean.setName("aaa");
        bean.setDesc("ddd");
        
        list.add(bean);
        
    }

    public static void testContainsObject() {
        List<BasicClass> list = new ArrayList<BasicClass>();
        BasicClass bean = new BasicClass();
        bean.setMail("abc");
        bean.setName("aaa");
        list.add(bean);
        bean = new BasicClass();
        bean.setMail("ccc");
        bean.setName("aaa");
        list.add(bean);
        
        List<BasicClass> list2 = new ArrayList<BasicClass>();
        bean = new BasicClass();
        bean.setMail("abcd");
        bean.setName("ccc");
        list2.add(bean);
        
        bean = new BasicClass();
        bean.setMail("abc");
        bean.setName("ccc");
        list2.add(bean);
        
        for (BasicClass temp : list2) {
            if(!list.contains(temp)) {
                list.add(temp);
            }
        }
        
        System.out.println(list.size());
    }
}
