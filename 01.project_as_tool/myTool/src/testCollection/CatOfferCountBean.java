/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-6-8      Cathy Wu        Create
 */

package testCollection;

import java.util.ArrayList;
import java.util.List;

public class CatOfferCountBean {

	private long catSearchId;
	private String catName;
	private int editable;
	private int offerCount;
	private int liveOfferCount;
	private List<CatOfferCountBean> childList = null;
	
	public CatOfferCountBean() {
		this.childList = new ArrayList<CatOfferCountBean>();
	}
	
	public List<CatOfferCountBean> getChildList() {
		return childList;
	}
	public void setChildList(List<CatOfferCountBean> childList) {
		this.childList = childList;
	}
	public long getCatSearchId() {
		return catSearchId;
	}
	public void setCatSearchId(long catSearchId) {
		this.catSearchId = catSearchId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getEditable() {
		return editable;
	}
	public void setEditable(int editable) {
		this.editable = editable;
	}
	public int getOfferCount() {
		if (this.editable == 1) {
			return this.offerCount;
		} else {
			return calcChildOfferCount();
		}
	}
	public void setOfferCount(int offerCount) {
		this.offerCount = offerCount;
	}
	public int getLiveOfferCount() {
		if (this.editable == 1) {
			return this.liveOfferCount;
		} else {
			return calcChildLiveOfferCount();
		}
	}
	public void setLiveOfferCount(int liveOfferCount) {
		this.liveOfferCount = liveOfferCount;
	}
	
	public int calcChildOfferCount() {
		if (this.childList == null) {
			return 0;
		}
		int count = 0;
		for (CatOfferCountBean bean : this.childList) {
			count += bean.getOfferCount();
		}
		return count;
	}
	
	public int calcChildLiveOfferCount() {
		if (this.childList == null) {
			return 0;
		}
		int count = 0;
		for (CatOfferCountBean bean : this.childList) {
			count += bean.getLiveOfferCount();
		}
		return count;
	}
	
	@Override
	public String toString() {
		
		return this.catSearchId + "; " + this.catName + "; " + this.editable
				+ "; offer count: " + this.getOfferCount()
				+ "; live offer count: " + this.getLiveOfferCount();
	}
}
