/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test.entry;

public class RssBean {

    private String typeName;
    private String link;
    private String catId;
    private String baseLink;
	private boolean displayDetail;
	private long useTime;

    public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	public boolean isDisplayDetail() {
		return displayDetail;
	}

	public void setDisplayDetail(boolean displayDetail) {
		this.displayDetail = displayDetail;
	}

	public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public RssBean() {

    }

    public RssBean(String typeName, String link, String catId) {
        this.typeName = typeName;
        this.link = link;
        this.catId = catId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public String getBaseLink() {
    	if (this.baseLink != null) {
    		return this.baseLink;
    	}
    	if (this.link == null || !this.link.contains("/") || this.link.trim().equals("")) {
    		this.baseLink = this.link;
    	}
    	this.baseLink = this.link.substring(0, this.link.lastIndexOf("/"));
    	this.baseLink = this.baseLink.substring(0, this.baseLink.lastIndexOf("/"));
    	return this.link;
    }
    
    @Override
    public String toString() {
        return "name: " + this.typeName + "; catId: " + this.catId
                + "; link: " + this.link;
    }
}
