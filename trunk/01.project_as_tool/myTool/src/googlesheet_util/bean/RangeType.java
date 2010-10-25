/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package googlesheet_util.bean;

public enum RangeType {

	WEEKLY(0), MONTHLY(1);
	
	private int type;
	
	private RangeType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}
