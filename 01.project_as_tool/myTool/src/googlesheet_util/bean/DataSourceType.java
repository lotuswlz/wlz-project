/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package googlesheet_util.bean;

public enum DataSourceType {

	GOOGLE_ANALYTICS(1), LEADS(2), ADMIN(3), ONLINE(4), DB(5);
	
	private int type;
	
	private DataSourceType(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.type;
	}
}
