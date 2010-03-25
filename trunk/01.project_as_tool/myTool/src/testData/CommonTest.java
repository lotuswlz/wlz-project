package testData;

public class CommonTest {
    
    public static void mod(int num, int modnum) {
        System.out.println(num + " % " + modnum + " = " + num % modnum);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            mod(i, 2);
        }
    }
}
