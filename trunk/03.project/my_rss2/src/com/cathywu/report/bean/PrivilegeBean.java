/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package com.cathywu.report.bean;

public class PrivilegeBean {

	private long privilegeId;
	private String title;
	private String description;
	public long getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
