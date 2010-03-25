/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject.completeTest;

import java.util.List;

public class StudentTeam extends Team<Student> {

    public StudentTeam() {
        Student s = new Student();
        s.setAge(15);
        s.setName("RRR");
        s.setParentName("adfasdf");
        addMembers(s);
        
        Student s2 = new Student();
        s2.setAge(14);
        s2.setName("a");
        s2.setParentName("adfadf");
        addMembers(s2);
    }
    
    public static void main(String[] args) {
        TeamMember<Student> t = new StudentTeam();
        List<Student> list = t.getMembers();
        for (Student s : list) {
            System.out.println(s.getName() + ";" + s.getAge() + ";" + s.getParentName());
        }
        System.out.println(t.getMemberBean("a"));
    }
}
