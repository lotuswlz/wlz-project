/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package com.cathywu.report.bean;

import java.math.BigDecimal;
import java.util.Date;

public class DataBean {
	private long dataId;
	private long dataMetaId;
	private Date beginDate;
	private Date endDate;
	private Date fillDate;
	private BigDecimal value;
	public long getDataId() {
		return dataId;
	}
	public void setDataId(long dataId) {
		this.dataId = dataId;
	}
	public long getDataMetaId() {
		return dataMetaId;
	}
	public void setDataMetaId(long dataMetaId) {
		this.dataMetaId = dataMetaId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getFillDate() {
		return fillDate;
	}
	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
