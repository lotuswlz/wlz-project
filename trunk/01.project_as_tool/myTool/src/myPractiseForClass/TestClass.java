/*
 * History
 *   Version     Update Date ¡¡¡¡        Updater¡¡¡¡¡¡¡¡       Details
 *   1.0.00  2011-4-18      Cathy Wu        Create
 */

package myPractiseForClass;

public class TestClass {
    
    public static void test(String name, String familyName, boolean isChinese) {
        Member m = null;
        if (isChinese) {
            m = new ChineseMember();
        } else {
            m = new ForeignMember();
        }
        m.setAge(15);
        m.setGender(Gender.MAIL);
        m.setMemberId(1);
        m.setFamilyName(familyName);
        m.setName(name);
        System.out.println(m.getFullName());
    }

    public static void main(String[] args) {
        test("Áé×Ó", "Îâ", true);
        test("Cathy", "Wu", false);
    }
}
