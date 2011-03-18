/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-7-7      Cathy Wu        Create
 */

package testStr;

public class ForExcelFormula {

	public static String f1 = "=SUMIF(C2:C63,F#,B2:B63)";
	public static String f2 = "=SUMIFS(B2:B63,C2:C63,F#,A2:A63,H3)";
	public static String f3 = "=SUMIFS(B2:B63,C2:C63,F#,A2:A63,I3)";
	public static String f4 = "=SUMIFS(B2:B63,C2:C63,F#,A2:A63,J3)";
	
	public static void changeF1(String formula, int fromNo, int toNo) {
		for (; fromNo <= toNo; fromNo++) {
			System.out.println(formula.replace("#", String.valueOf(fromNo)));
		}
	}
	
	public static void main(String[] args) {
		changeF1(f4, 4, 28);
	}
}
