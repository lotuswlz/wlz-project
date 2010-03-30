package com.cathywu.test.entry;

import java.util.Date;

public class RssItemBean {

	private String title;
	private String href;
	private String description;
	private Date publishDate;
	private String author;
	
	private RssBean parentRssBean;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getDescription() {
		if (description != null) {			
			description = description.replaceAll("src=\"\\.\\.", "src=\"" + this.parentRssBean.getBaseLink() + "/");
		}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public RssBean getParentRssBean() {
		return parentRssBean;
	}
	public void setParentRssBean(RssBean parentRssBean) {
		this.parentRssBean = parentRssBean;
	}
}
