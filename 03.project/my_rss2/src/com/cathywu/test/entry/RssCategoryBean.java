package com.cathywu.test.entry;

import java.util.List;

public class RssCategoryBean implements Comparable<RssCategoryBean> {

	private String category;
	private String catName;
	private String id;
	
	private List<RssBean> childTypeList;
	
	public List<RssBean> getChildTypeList() {
		return childTypeList;
	}
	public void setChildTypeList(List<RssBean> childTypeList) {
		this.childTypeList = childTypeList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int compareTo(RssCategoryBean o) {
		return Integer.parseInt(this.id) - Integer.parseInt(o.getId());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
