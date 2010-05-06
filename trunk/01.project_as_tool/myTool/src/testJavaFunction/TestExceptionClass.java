/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-4-28      Cathy Wu        Create
 */

package testJavaFunction;

public class TestExceptionClass {

    public static void test() {
        throw new NoReasonException("No reason.");
    }
    
    public static int testException() {
        try {
            test();
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("finally");
        }
        return 1;
    }
    
    public static void main(String[] args) {
        int i  = testException();
        System.out.println("result is: " + i);
    }
}
