/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  Oct 25, 2010      Cathy Wu        Create
 */

package googlesheet_util.bean;

import java.util.Date;

/**
 * Stored Data Definition and Descriptions.
 * @since Oct 25, 2010
 * @author  Cathy Wu
 * @version  1.1.00
 */
public class DataMetaBean {

	private long metaId;
	private String description;
	private String title;
	private DataSourceType source;
	/**
	 * May be sheet link, description wording and so on.
	 */
	private String referencedString;
	private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getMetaId() {
		return metaId;
	}
	public void setMetaId(long metaId) {
		this.metaId = metaId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public DataSourceType getSource() {
		return source;
	}
	public void setSource(DataSourceType source) {
		this.source = source;
	}
	/**
	 * @see referencedString
	 * @return String
	 */
	public String getReferencedString() {
		return referencedString;
	}
	public void setReferencedString(String referencedString) {
		this.referencedString = referencedString;
	}
}
