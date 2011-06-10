/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package com.cathywu.report.bean;

import java.util.Date;
import java.util.List;

public class ReportTemplateBean {
	private long templateId;
	private String title;
	private String description;
	private Date createTime;
	private boolean avaliable;
	
	private List<ReprotTemplateColumnBean> columnList;
	
	public List<ReprotTemplateColumnBean> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ReprotTemplateColumnBean> columnList) {
		this.columnList = columnList;
	}
	public long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isAvaliable() {
		return avaliable;
	}
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}
}
