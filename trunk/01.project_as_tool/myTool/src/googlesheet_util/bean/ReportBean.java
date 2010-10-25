/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package googlesheet_util.bean;

import java.util.Date;
import java.util.List;

public class ReportBean {

	private long reportId;
	private long templateId;
	private Date fillDate;
	private RangeType rangeType;
	
	private List<DataBean> dataList;

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public RangeType getRangeType() {
		return rangeType;
	}

	public void setRangeType(RangeType rangeType) {
		this.rangeType = rangeType;
	}

	public List<DataBean> getDataList() {
		return dataList;
	}

	public void setDataList(List<DataBean> dataList) {
		this.dataList = dataList;
	}
}
