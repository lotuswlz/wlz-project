package com.cathywu.test.entry;

import java.util.Date;

public class RssItemBean {

	private String title;
	private String href;
	private String description;
	private Date publishDate;
	private String author;
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
			description = description.replaceAll("src=\"\\.\\.", "src=\"http://www.xinhuanet.com/english2010/");
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
}
